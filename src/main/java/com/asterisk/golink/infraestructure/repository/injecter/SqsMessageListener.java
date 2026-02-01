package com.asterisk.golink.infraestructure.repository.injecter;

import com.asterisk.golink.domain.service.AircraftKafkaService;
import com.asterisk.golink.infraestructure.repository.injecter.mapper.AircraftSqsMapper;
import com.asterisk.golink.infraestructure.repository.injecter.response.AircraftSqsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsMessageListener {

    private final AircraftKafkaService aircraftKafkaService;
    private final AircraftSqsMapper aircraftSqsMapper;

    @SqsListener("aircraft_data.fifo")
    public void receiveMessage(Message<?> message) {

        String messageBody = (String) message.getPayload();

        ObjectMapper mapper = new ObjectMapper();
        try {

            AircraftSqsResponse aircraftData = mapper.readValue(messageBody, AircraftSqsResponse.class);

            this.aircraftKafkaService.receiveAircraftData(aircraftSqsMapper.toDomain(aircraftData));

        } catch (Exception e) {
            log.error("Error processing SQS message: {}", e.getMessage());
        }

    }

}
