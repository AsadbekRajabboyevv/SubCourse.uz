package uz.asadbek.AdminPanel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.asadbek.AdminPanel.models.User;
import uz.asadbek.AdminPanel.repository.UserRepo;

import java.util.Optional;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvices {

    private final UserRepo userRepo;

    @ModelAttribute
    public void addAtributes(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if (userDetails != null){
            Optional<User> user = userRepo.findByUsername(userDetails.getUsername());
            model.addAttribute("userFullName", user.get().getEmployee().getName()+" "+user.get().getEmployee().getSurname());
            model.addAttribute("userRole", user.get().getRole());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Nimadur xato ketti! Iltmos qaytadan urinib ko'ring ");
    }

}
