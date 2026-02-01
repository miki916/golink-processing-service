package com.asterisk.golink.infraestructure.repository.injector.configuration;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.asterisk.golink.infraestructure.repository.injector.properties.SqsProperties;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementResultCallback;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SqsReceiver {

  private final SqsProperties sqsProperties;

  @Bean
  SqsAsyncClient sqsAsyncClient() {

    return SqsAsyncClient.builder()
        .region(Region.of(sqsProperties.getRegion()))
        .credentialsProvider(StaticCredentialsProvider
            .create(AwsBasicCredentials.create(sqsProperties.getAccessKeyId(),
                sqsProperties.getSecretAccessKey())))
        .build();
  }

  @Bean
  SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(
      SqsAsyncClient sqsAsyncClient) {

    return SqsMessageListenerContainerFactory.builder()
        .configure(
            options -> options.acknowledgementMode(AcknowledgementMode.ALWAYS)
                .acknowledgementInterval(Duration.ofSeconds(3))
                .acknowledgementThreshold(0))
        .acknowledgementResultCallback(new AckResultCallback())
        .sqsAsyncClient(sqsAsyncClient)
        .build();
  }

  static class AckResultCallback
      implements AcknowledgementResultCallback<Object> {

    @Override
    public void onSuccess(Collection<Message<Object>> messages) {

      log.info("Ack with success at {}", OffsetDateTime.now());
    }

    @Override
    public void onFailure(Collection<Message<Object>> messages, Throwable t) {

      log.error("Ack with fail", t);
    }
  }

}
