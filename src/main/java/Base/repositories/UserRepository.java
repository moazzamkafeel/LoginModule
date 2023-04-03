package Base.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Base.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

	
	
	
	
	
}
