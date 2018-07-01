package br.com.livetouch.email_manager.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livetouch.email_manager.entity.Projeto;
import br.com.livetouch.email_manager.repository.ProjetoRepository;
import br.com.livetouch.email_manager.util.TokenGenerator;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository rep;

	public void saveOrUpdate(Projeto projeto) {
		if (projeto.getId() == null) {
			String accessToken = TokenGenerator.generateToken();
			projeto.setAccessToken(accessToken);
			projeto.setDataCreated(new Date());
		}
		projeto.setDataUpdated(new Date());
		rep.save(projeto);
	}

	public Optional<Projeto> findById(Long id) {
		return rep.findById(id);
	}

	public void resetAccessToken(Projeto projeto) {
		String accessToken = TokenGenerator.generateToken();
		projeto.setAccessToken(accessToken);
		projeto.setDataUpdated(new Date());
		rep.save(projeto);
	}

}
