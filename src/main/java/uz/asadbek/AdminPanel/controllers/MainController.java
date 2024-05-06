package uz.asadbek.AdminPanel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.asadbek.AdminPanel.models.Employee;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.UserRepo;
import uz.asadbek.AdminPanel.service.EmployeeService;
import uz.asadbek.AdminPanel.service.UserDetailsService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
	private final UserDetailsService userDetailsService;
	private final EmployeeService employeeService;
	private final UserRepo userRepo;
	@Autowired
    public MainController(UserDetailsService userDetailsService, EmployeeService employeeService, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.employeeService = employeeService;
        this.userRepo = userRepo;
    }
	@ModelAttribute
	public void  models(@AuthenticationPrincipal UserDetails userDetails,Model model){
		if (userDetails != null){
		Optional<User> user =userRepo.findByUsername(userDetails.getUsername());
			model.addAttribute("userRole",user.get().getRole());
			model.addAttribute("user", user);
		}
	}

	@GetMapping("/")
	public String mainPage(@ModelAttribute("user")User user, Model model) {
		String authenticatedUsername = userDetailsService.getAuthenticatedUsername();
		model.addAttribute("user", user);
		model.addAttribute("username", authenticatedUsername);
		return "main/index";
	}


}
