package uz.asadbek.AdminPanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.UserRepo;


@Service
public class RegisterService {
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public RegisterService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
}
	public void registerPerson(User user){
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setRole("ROLE_USER");
		userRepo.save(user);
	}

}
