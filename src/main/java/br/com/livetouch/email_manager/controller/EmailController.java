package br.com.livetouch.email_manager.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.livetouch.email_manager.payload.ApiResponse;
import br.com.livetouch.email_manager.payload.EmailSend;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

	@Autowired
	ObjectMapper mapper;
	
	@Value("${topic.exchnage.email.name}")
	private String topicEmailSaveExchangeName;

	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public ResponseEntity<?> sendMsg(@Valid @RequestBody EmailSend email) throws InterruptedException, JsonProcessingException {
		
		String identifier = UUID.randomUUID().toString();
		email.setIdentifier(identifier);
		
//		String json = mapper.writeValueAsString(email);
		
//		rabbitTemplate.convertAndSend(topicEmailSaveExchangeName, "email.save", json);
		
		ApiResponse response = new ApiResponse(true, "E-mail adicionado a fila para ser enviado em breve");
		return ResponseEntity.ok(response);
	}
}
