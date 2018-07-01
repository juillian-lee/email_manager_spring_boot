package br.com.livetouch.email_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.livetouch.email_manager.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("SELECT l FROM Login l where l.email = ?1 AND l.password = ?2")
	public Optional<Login> login(String email, String password);
	
	@Query("SELECT l FROM Login l where l.email = ?1")
	public Optional<Login> findByEmail(String email);
	
	@Query("SELECT l FROM Login l where l.id = ?1")
	public Optional<Login> findById(Long id);
	
}
