package br.com.livetouch.email_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livetouch.email_manager.entity.EmailUsuario;

public interface EmailUsuarioRepository extends JpaRepository<EmailUsuario, Long>{

}
