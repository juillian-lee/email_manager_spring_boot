package br.com.livetouch.email_manager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.livetouch.email_manager.entity.Projeto;
import br.com.livetouch.email_manager.service.ProjetoService;

@RestController
@RequestMapping(value = "/api/projeto")
@Secured("ROLE_ROOT")
public class ProjetoController {

	@Autowired
	ProjetoService projetoService;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody Projeto projeto) {
		projetoService.saveOrUpdate(projeto);
		return ResponseEntity.ok(projeto);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Projeto projetoRequest) {
		Projeto projeto = projetoService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado " + id));
		projeto.setNome(projetoRequest.getNome());
		projeto.setAwsAccessKey(projetoRequest.getAwsAccessKey());
		projeto.setAwsSecretKey(projetoRequest.getAwsSecretKey());
		projeto.setActive(projetoRequest.isActive());
		projeto.setDominio(projetoRequest.getDominio());
		projetoService.saveOrUpdate(projeto);

		return ResponseEntity.ok(projeto);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/resetAccessToken/{id}")
	public ResponseEntity<?> resetAccessToken(@PathVariable Long id) {
		Projeto projeto = projetoService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado " + id));
		projetoService.resetAccessToken(projeto);
		return ResponseEntity.ok(projeto);
	}

}
