package springSecurity.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springSecurity.model.Users;

public interface Repositroy extends JpaRepository<Users, Integer>{

	Optional<Users> findByName(String userName);
	
	
}
