package uz.asadbek.AdminPanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.AdminPanel.models.Employee;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.EmployeeRepo;
import uz.asadbek.AdminPanel.repository.UserRepo;

import java.time.LocalDateTime;


@Service
@Transactional(readOnly = true)
public class RegisterService {
	private final UserRepo userRepo;
	private final EmployeeRepo employeeRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public RegisterService(UserRepo userRepo, EmployeeRepo employeeRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
}
	@Transactional
	public void registerUser(User user, Employee employee) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepo.save(user);
		user.setUpdatedAt(LocalDateTime.now());
		employee.setUser(savedUser);
		employeeRepo.save(employee);
	}

}
