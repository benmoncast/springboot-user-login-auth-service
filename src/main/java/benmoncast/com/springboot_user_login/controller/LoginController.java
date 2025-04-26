package benmoncast.com.springboot_user_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import benmoncast.com.springboot_user_login.model.User;
import benmoncast.com.springboot_user_login.service.UserService;

@Controller
public class LoginController {

	    @Autowired
	    private UserService userService;

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute("user") User user) {
	        userService.save(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	    }

	    @GetMapping("/dashboard")
	    public String dashboard() {
	        return "dashboard";
	    }
	}
