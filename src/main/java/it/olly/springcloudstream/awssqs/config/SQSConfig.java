package it.olly.springcloudstream.awssqs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

@Configuration
public class SQSConfig {

    @Bean
    AmazonSQSAsync buildAmazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .build();
        /*return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();*/
    }

    @Bean
    QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
}
