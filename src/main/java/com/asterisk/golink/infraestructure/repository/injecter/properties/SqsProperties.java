package com.asterisk.golink.infraestructure.repository.injecter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws.sqs")
public class SqsProperties {

    private String accessKeyId;
    private String secretAccessKey;
    private String region;

}
