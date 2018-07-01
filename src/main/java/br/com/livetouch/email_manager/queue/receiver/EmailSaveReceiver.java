package br.com.livetouch.email_manager.queue.receiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

@Component
public class EmailSaveReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message)  throws Exception {
    	throw new AmqpRejectAndDontRequeueException("to dead-letter");
//        System.out.println("Received <" + message + ">");
//        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}