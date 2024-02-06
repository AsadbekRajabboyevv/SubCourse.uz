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
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.service.RegisterService;
import uz.asadbek.AdminPanel.util.Uservalidator;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthController {
	private final Uservalidator uservalidator;
	private final RegisterService service;
	@Autowired
	public AuthController(Uservalidator uservalidator, RegisterService service) {
		this.uservalidator = uservalidator;
		this.service = service;
	}

	@GetMapping("/login")
	public String loginPage(){
		return "auth/login";
	}

	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user")User user, Model model){
		model.addAttribute("user", user);
		return "auth/register";
	}


	@PostMapping("/register")
	public String registerPerson(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		uservalidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "auth/register";
		}
		user.setCreatedAt(LocalDateTime.now());
		service.registerPerson(user);
		return "redirect:/auth/login";
	}


}
