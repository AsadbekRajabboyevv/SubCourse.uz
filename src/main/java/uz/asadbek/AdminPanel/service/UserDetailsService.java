package uz.asadbek.AdminPanel.service;

import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.UserRepo;
import uz.asadbek.AdminPanel.util.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	private final UserRepo userRepo;

	@Autowired
	public UserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;




	}
	/*****************************************************************************************************************/
	//db dagi barcha userlarni List ko'rinishida olib keladi
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	/*****************************************************************************************************************/





	/*****************************************************************************************************************/
	//ID bo'yicha 1ta userni bizga olib keladi agar topilmasa UserNotFound exceptionini tashlaydi
	public User getOneUser(int id) {
		return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
	}
	/*****************************************************************************************************************/







	/*****************************************************************************************************************/
	//Email bo'yicha db ni qidiradi agar topoplmasa "user not found" exception tashlaydi
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> byUsername = userRepo.findByEmail(email);
		if (byUsername.isEmpty()){
			throw new UsernameNotFoundException("User not Found");
		}
		System.out.println("UserDetails: " + byUsername.get());

		return new uz.asadbek.AdminPanel.security.UserDetails(byUsername.get());
	}
	/*****************************************************************************************************************

	/*****************************************************************************************************************/
	@Transactional
	public void getDeleteById(int id){
		System.out.println(id+" li user o'chirib tashlandi");
		userRepo.deleteById(id);
	}
	/*****************************************************************************************************************/



}
