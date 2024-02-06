package uz.asadbek.AdminPanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.asadbek.AdminPanel.models.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	@Query(value = "select * from users where email = ?1", nativeQuery = true)
	Optional<User> fingByEmail(String email);

	Optional<User> findByEmail(String email);
}
