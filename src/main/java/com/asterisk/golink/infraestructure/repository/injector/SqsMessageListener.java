package com.asterisk.golink.infraestructure.repository.injector;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.asterisk.golink.domain.exception.ValidationException;
import com.asterisk.golink.domain.service.AircraftInjectorService;
import com.asterisk.golink.infraestructure.repository.injector.mapper.AircraftSqsMapper;
import com.asterisk.golink.infraestructure.repository.injector.properties.SqsQueueProperties;
import com.asterisk.golink.infraestructure.repository.injector.response.AircraftSqsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsMessageListener {

  public static final String RECEIVED_SQS_MESSAGE_FROM =
      "[SQS_LISTENER] Received SQS message from {} queue: {}";

  public static final String PROCESSING_SQS_MESSAGE_BODY =
      "[SQS_LISTENER] Processing SQS message body from {} queue: {}";

  public static final String MAPPED_SQS_MESSAGE_TO_FROM_QUEUE =
      "[SQS_LISTENER] Mapped SQS message to {} from {} queue";

  public static final String SQS_LISTENER_SUCCESSFULLY_PROCESSED_SQS_MESSAGE_FROM_QUEUE =
      "[SQS_LISTENER] Successfully processed SQS message from {} queue";

  private final AircraftInjectorService service;

  private final AircraftSqsMapper aircraftSqsMapper;

  private final SqsQueueProperties sqsQueueProperties;

  @SqsListener("aircraft_data.fifo")
  public void receiveMessage(Message<?> message) {

    log.info(RECEIVED_SQS_MESSAGE_FROM,
        sqsQueueProperties.getAircraftData(),
        message.getPayload());

    String messageBody = (String) message.getPayload();

    ObjectMapper mapper = new ObjectMapper();
    try {
      log.debug(PROCESSING_SQS_MESSAGE_BODY,
          sqsQueueProperties.getAircraftData(),
          messageBody);

      AircraftSqsResponse aircraftData =
          mapper.readValue(messageBody, AircraftSqsResponse.class);

      log.debug(MAPPED_SQS_MESSAGE_TO_FROM_QUEUE,
          "AircraftSqsResponse",
          sqsQueueProperties.getAircraftData());

      this.service
          .receiveAircraftData(aircraftSqsMapper.toDomain(aircraftData));

      log.info(SQS_LISTENER_SUCCESSFULLY_PROCESSED_SQS_MESSAGE_FROM_QUEUE,
          sqsQueueProperties.getAircraftData());
    } catch (JsonProcessingException e) {
      throw new ValidationException("Error processing SQS message");
    }
  }

}
