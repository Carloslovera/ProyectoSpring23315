package ar.com.codoacodo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo2.domain.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	User findByUsername(String username);

}
