package uz.asadbek.AdminPanel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.service.UserDetailsService;
@Component
public class Uservalidator implements Validator {

	private final UserDetailsService userDetailsService;
	@Autowired
	public Uservalidator(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			User user = (User) target;
			try {
				userDetailsService.loadUserByUsername(user.getEmail());
			}catch (UsernameNotFoundException e){
				return;
			}
			errors.rejectValue("email", "","Bunday email avval ro'yhatdan o'tgaan");

	}
}
