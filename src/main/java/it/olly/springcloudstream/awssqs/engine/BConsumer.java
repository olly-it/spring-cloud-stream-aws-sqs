package it.olly.springcloudstream.awssqs.engine;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.olly.springcloudstream.awssqs.model.BMessage;

@Component("BConsumer")
public class BConsumer implements Consumer<BMessage> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void accept(BMessage object) {
        logger.debug("BConsumer - got message from bQ: " + object);
    }

}
