package com.asterisk.golink.infraestructure.repository.injector.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws.sqs")
public class SqsProperties {

  private String accessKeyId;
  private String secretAccessKey;
  private String region;

}
