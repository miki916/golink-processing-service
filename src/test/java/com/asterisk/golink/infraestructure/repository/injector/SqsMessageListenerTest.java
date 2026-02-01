package com.asterisk.golink.infraestructure.repository.injector;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import com.asterisk.golink.domain.exception.ValidationException;
import com.asterisk.golink.domain.service.AircraftInjectorService;
import com.asterisk.golink.infraestructure.repository.injector.mapper.AircraftSqsMapper;
import com.asterisk.golink.infraestructure.repository.injector.properties.SqsQueueProperties;
import com.asterisk.golink.infraestructure.repository.injector.response.AircraftSqsResponse;

class SqsMessageListenerTest {

  private final AircraftInjectorService service =
      Mockito.mock(AircraftInjectorService.class);

  private final AircraftSqsMapper aircraftSqsMapper =
      Mappers.getMapper(AircraftSqsMapper.class);

  private final SqsQueueProperties sqsQueueProperties =
      Mockito.mock(SqsQueueProperties.class);

  private SqsMessageListener sqsMessageListener;

  @BeforeEach
  void setUp() {

    this.sqsMessageListener = new SqsMessageListener(this.service,
        this.aircraftSqsMapper,
        this.sqsQueueProperties);
  }

  @Test
  void receiveMessage() {

    String content = """
         {
                      "vin": "8c1bc557-d1fe-4bdb-82de-173c074ea0eb",
                      "position": {
                        "x": -118.4085,
                        "y": 33.9416
                      },
                      "orientation": "N",
                      "speed": 1500,
                      "altitude": 0
                    }
        """;

    Message<?> message = getMessage(content);

    GeometryFactory geometryFactory = new GeometryFactory();
    Point position =
        geometryFactory.createPoint(new Coordinate(-118.4085, 33.9416));

    AircraftSqsResponse dto = new AircraftSqsResponse();
    dto.setVin(UUID.fromString("8c1bc557-d1fe-4bdb-82de-173c074ea0eb"));
    dto.setPosition(position);
    dto.setOrientation("N");
    dto.setSpeed(1500L);
    dto.setAltitude(0L);

    Mockito.when(sqsQueueProperties.getAircraftData())
        .thenReturn("aircraft_data.fifo");

    this.sqsMessageListener.receiveMessage(message);

    Mockito.verify(this.service, Mockito.times(1))
        .receiveAircraftData(Mockito.argThat(r -> {
          return r.getVin().equals(dto.getVin())
              && r.getPosition().equals(dto.getPosition())
              && r.getOrientation().equals(dto.getOrientation())
              && r.getSpeed().equals(dto.getSpeed())
              && r.getAltitude().equals(dto.getAltitude());
        }));
  }

  @Test
  void receiveMessageShouldThrowException() {

    String content = """
         {
                      "test" : "invalid_payload"
                    }
        """;

    Message<?> message = getMessage(content);

    Mockito.when(sqsQueueProperties.getAircraftData())
        .thenReturn("aircraft_data.fifo");

    Assertions.assertThrows(ValidationException.class,
        () -> this.sqsMessageListener.receiveMessage(message));
  }

  private Message<?> getMessage(String content) {

    return new Message<>() {
      @Override
      public Object getPayload() {

        return content;
      }

      @Override
      public MessageHeaders getHeaders() {

        return null;
      }
    };
  }
}