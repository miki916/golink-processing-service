package com.asterisk.golink.infraestructure.repository.injector.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws.sqs.queue")
public class SqsQueueProperties {

    private String aircraftData;

}
