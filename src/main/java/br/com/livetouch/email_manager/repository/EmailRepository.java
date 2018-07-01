package br.com.livetouch.email_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.livetouch.email_manager.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

	
	@Query("SELECT e FROM Email e where e.identifier = ?1")
	Optional<Email> findByIdentifier(String identifier);
}
