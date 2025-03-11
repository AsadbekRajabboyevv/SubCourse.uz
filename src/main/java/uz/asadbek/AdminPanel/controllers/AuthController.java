package uz.asadbek.AdminPanel.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.asadbek.AdminPanel.models.Employee;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.UserRepo;
import uz.asadbek.AdminPanel.service.RegisterService;
import uz.asadbek.AdminPanel.util.EmployeeValidator;
import uz.asadbek.AdminPanel.util.Uservalidator;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthController {
	private final Uservalidator uservalidator;
	private final EmployeeValidator employeeValidator;
	private final RegisterService service;
	private final UserRepo userRepo;
	@Autowired
	public AuthController(Uservalidator uservalidator, EmployeeValidator employeeValidator, RegisterService service, UserRepo userRepo) {
		this.uservalidator = uservalidator;
        this.employeeValidator = employeeValidator;
        this.service = service;
        this.userRepo = userRepo;
    }

	@GetMapping("/login")
	public String loginPage(){
		return "auth/login";
	}

	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user")User user, @ModelAttribute Employee employee, Model model){
		model.addAttribute("user", user);
		model.addAttribute("employee", employee);
		return "auth/register";
	}


	@PostMapping("/register")
	public String registerPerson(@ModelAttribute("user") User user,@ModelAttribute Employee employee) {

		user.setCreatedAt(LocalDateTime.now());
		String prefixedRole = "ROLE_" + user.getRole();
		user.setRole(prefixedRole);
		service.registerUser(user,employee);
		return "redirect:/auth/login";
	}


}
