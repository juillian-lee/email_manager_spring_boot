package br.com.livetouch.email_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.livetouch.email_manager.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u where u.email = ?1")
	Optional<Usuario> findByEmail(String email);

}
