package br.com.livetouch.email_manager.queue.dead;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

@Component
public class EmailSaveDeadLetter {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) throws Exception {
        System.out.println("Dead letter <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
