package uz.asadbek.AdminPanel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.service.UserDetailsService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserDetailsService service;

	@Autowired
	public AdminController(UserDetailsService service) {
		this.service = service;
	}
	@GetMapping()
	public String adminPage(){
		return "adminPanel/index";
	}

	@GetMapping("/users")
	public String getAllUsers(Model model){
		model.addAttribute("users", service.getAllUsers());
		return "adminPanel/users";
	}

	@GetMapping("/users/{id}")
	public String getOneUser(@PathVariable("id")Long id, Model model){
		String role = service.getOneUser(id).getRole();
		String substring = role.substring(5);
		service.getOneUser(id).setRole(substring);
		model.addAttribute("userInfo", service.getOneUser(id));
		return "adminPanel/userInfo";
	}
}
