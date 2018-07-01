package br.com.livetouch.email_manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.livetouch.email_manager.entity.Login;
import br.com.livetouch.email_manager.enums.Role;
import br.com.livetouch.email_manager.repository.LoginRepository;

@Component
public class DatabaseInitilizr implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<Login> users = loginRepository.findAll();
		if(users.isEmpty()) {
			Login login = new Login();
			login.setEmail("admin@livetouch.com.br");
			login.setNome("Admin Livetouch");
			login.setPassword(passwordEncoder.encode("admin"));
			login.setRole(Role.ROOT);
			loginRepository.save(login);
		}
	}
}
