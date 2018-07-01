package br.com.livetouch.email_manager.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.livetouch.email_manager.queue.dead.EmailSaveDeadLetter;
import br.com.livetouch.email_manager.queue.receiver.EmailSaveReceiver;

@Configuration
public class MQConfig {

	public static String DEFAULT_EXCHANGE = "email-exchange";
	public static String DEAD_LETTER_QUEUE = "email-save-dead-letter-queue";
	public static String WORKORDER_QUEUE = "email-save-queue";

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(DEFAULT_EXCHANGE);
	}

	@Bean(name="DEAD_LETTER_QUEUE")
	Queue deadLetterQueue() {
		return new Queue(DEAD_LETTER_QUEUE, true);
	}

	@Bean(name="WORKORDER_QUEUE")
	Queue queue() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", DEFAULT_EXCHANGE);
		args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE);
		return new Queue(WORKORDER_QUEUE, true, false, false, args);
	}

	@Bean
	Binding binding(@Qualifier("WORKORDER_QUEUE") Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(WORKORDER_QUEUE);
	}

	@Bean
	Binding bindingDeadLetter(@Qualifier("DEAD_LETTER_QUEUE") Queue deadLetterQueue, TopicExchange exchange) {
		return BindingBuilder.bind(deadLetterQueue).to(exchange).with(DEAD_LETTER_QUEUE);
	}
	
	@Bean
    SimpleMessageListenerContainer containerSaveEmail(ConnectionFactory connectionFactory,
            @Qualifier("listenerSaveEmailAdapter") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(WORKORDER_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean(name="listenerSaveEmailAdapter")
    MessageListenerAdapter listenerSaveEmailAdapter(EmailSaveReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
    @Bean
    SimpleMessageListenerContainer containerDeadLetterSaveEmail(ConnectionFactory connectionFactory,
            @Qualifier("listenerDeadLetterAdapter") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(WORKORDER_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean(name="listenerDeadLetterAdapter")
    MessageListenerAdapter listenerDeadLetterAdapter(EmailSaveDeadLetter receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
    

}
