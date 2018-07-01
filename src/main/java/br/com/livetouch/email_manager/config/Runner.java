package br.com.livetouch.email_manager.config;

import java.util.concurrent.TimeUnit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.livetouch.email_manager.queue.receiver.EmailSaveReceiver;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final EmailSaveReceiver receiver;

    public Runner(EmailSaveReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(MQConfig.DEFAULT_EXCHANGE, MQConfig.WORKORDER_QUEUE, "Hello from RabbitMQ!");
//        receiver.getLatch();
    }

}
