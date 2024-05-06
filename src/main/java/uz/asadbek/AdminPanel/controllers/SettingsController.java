package uz.asadbek.AdminPanel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.asadbek.AdminPanel.models.User;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @GetMapping()
    public String settingsPage(){
        return "settings/index";
    }

    @GetMapping("/profile")
    public String profile(){
        return "settings/profile";
    }

}
