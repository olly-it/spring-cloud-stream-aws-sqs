package it.olly.springcloudstream.awssqs.engine;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import it.olly.springcloudstream.awssqs.model.AMessage;
import it.olly.springcloudstream.awssqs.model.BMessage;
import it.olly.springcloudstream.awssqs.util.SQSUtils;

/**
 * consumes messages from aQ and re-send them to bQ
 * 
 * @author alessio olivieri
 *
 */
@Component("AProcessor")
public class AProcessor implements Function<AMessage, Message<BMessage>> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SQSUtils sqs;

    @Override
    public Message<BMessage> apply(AMessage msg) {
        logger.debug("AProcessor - got message from aQ: " + msg);
        BMessage ret = new BMessage();
        ret.setId(msg.getId());
        ret.setMessage(msg.getText());
        ret.setSex(Math.random() > 0.5 ? "M" : "F");

        return sqs.composeFifoSQSMessage(ret);
    }
}