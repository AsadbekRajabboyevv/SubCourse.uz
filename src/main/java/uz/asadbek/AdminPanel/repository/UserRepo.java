package uz.asadbek.AdminPanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.asadbek.AdminPanel.models.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

	@Query(value = "select * from users where username = ?1", nativeQuery = true)
	Optional<User> fingByUsername(String username);

	Optional<User> findByUsername(String username);
}
