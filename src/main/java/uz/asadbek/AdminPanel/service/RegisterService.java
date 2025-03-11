package uz.asadbek.AdminPanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.AdminPanel.domain.EmployeeRegisterDTO;
import uz.asadbek.AdminPanel.domain.UserDTO;
import uz.asadbek.AdminPanel.domain.UserRegisterDTO;
import uz.asadbek.AdminPanel.models.Employee;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.EmployeeRepo;
import uz.asadbek.AdminPanel.repository.UserRepo;
import uz.asadbek.AdminPanel.util.exceptions.UserNotFoundException;

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
	public void registerDTOuser(UserRegisterDTO userRegisterDTO,
								EmployeeRegisterDTO employeeRegisterDTO)
			throws ChangeSetPersister.NotFoundException {
		final User user = new User();
		final Employee employee = new Employee();
		convertToEntity(userRegisterDTO,user);
		convertToEntity(employeeRegisterDTO,employee);
	}


	private User convertToEntity(final UserRegisterDTO userRegisterDTO, final User user) throws ChangeSetPersister.NotFoundException {
		user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
		user.setRole(userRegisterDTO.getRole());
		user.setUsername(userRegisterDTO.getUsername());
		final Employee employee = userRegisterDTO.getEmployee() == null ? null : employeeRepo.findById(userRegisterDTO.getEmployee())
				.orElseThrow(UserNotFoundException::new);
		user.setEmployee(employee);
		return user;
	}
	private Employee convertToEntity(final EmployeeRegisterDTO employeeRegisterDTO,
									 final Employee employee)
			throws ChangeSetPersister.NotFoundException {
		employee.setId(employeeRegisterDTO.getId());
		employee.setName(employeeRegisterDTO.getName());
		employee.setSurname(employeeRegisterDTO.getSurname());
		employee.setCoordinates(employeeRegisterDTO.getCoordinates());
		employee.setPhoneNumber(employeeRegisterDTO.getPhoneNumber());
		employee.setDateOfBirth(employeeRegisterDTO.getDateOfBirth());
		employee.setSpeciality(employeeRegisterDTO.getSpeciality());
		employee.setUser(employeeRegisterDTO.getUser() == null ?
				null : userRepo.findById(employeeRegisterDTO.getUser())
				.orElseThrow(ChangeSetPersister.NotFoundException::new));
		return employee;
	}

	private UserRegisterDTO convertToDTO(final User user, final UserRegisterDTO userRegisterDTO){
		userRegisterDTO.setId(user.getId());
		userRegisterDTO.setUsername(user.getUsername());
		userRegisterDTO.setRole(user.getRole());
		userRegisterDTO.setEmployee(user.getEmployee() ==
				null ? null : user.getEmployee().getId());
		return userRegisterDTO;
	}
}
