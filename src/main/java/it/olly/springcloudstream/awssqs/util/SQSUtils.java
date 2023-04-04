package it.olly.springcloudstream.awssqs.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.idealo.spring.stream.binder.sqs.SqsHeaders;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.core.SqsMessageHeaders;

@Service
public class SQSUtils {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @Value("${spring.application.name}")
    private String appName;

    public <T> void sendFifoMessage(String queue, T message) {
        // Message for FIFO Queue
        Map<String, Object> headers = new HashMap<>();
        // Message Group ID being set
        headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, appName);
        // Below is optional, since Content based de-duplication is enabled
        headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID()
                .toString());
        try {
            messagingTemplate.send(queue, MessageBuilder.withPayload(om.writeValueAsString(message))
                    .copyHeaders(headers)
                    .build());
        } catch (MessagingException | JsonProcessingException e) {
            logger.error("Got errors on encoding an object?!", e);
        }

    }

    public <T> Message<T> composeFifoSQSMessage(T message) {
        return MessageBuilder.withPayload(message)
                .setHeader(SqsHeaders.GROUP_ID, appName)
                .setHeader(SqsHeaders.DEDUPLICATION_ID, UUID.randomUUID())
                .build();

    }

}
