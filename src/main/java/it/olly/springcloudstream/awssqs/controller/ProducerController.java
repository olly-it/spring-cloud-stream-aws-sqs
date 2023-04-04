package it.olly.springcloudstream.awssqs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.olly.springcloudstream.awssqs.model.AMessage;
import it.olly.springcloudstream.awssqs.util.SQSUtils;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private SQSUtils sqs;

    @GetMapping()
    public Mono<String> index() {
        return Mono.just("HELLO - " + new Date());
    }

    @GetMapping("/{id}")
    public Mono<String> sendMessage(@PathVariable Long id) throws JsonProcessingException {
        String text = "This is a message - " + new Date();

        AMessage msg = new AMessage();
        msg.setText(text);
        msg.setId(id);

        sqs.sendFifoMessage("aQ.fifo", msg);

        return Mono.just("Message: " + msg + " -> sent");
    }
}
