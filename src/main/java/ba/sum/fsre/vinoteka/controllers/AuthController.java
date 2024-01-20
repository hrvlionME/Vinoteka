package ba.sum.fsre.vinoteka.controllers;

import ba.sum.fsre.vinoteka.models.Role;
import ba.sum.fsre.vinoteka.models.User;
import ba.sum.fsre.vinoteka.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/auth/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/auth/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "homepage";
    }


    @PostMapping("/auth/register")
    public String addUser(@Valid User user, BindingResult result, Model model){
        boolean errors = result.hasErrors();
        if(errors){
            model.addAttribute("user", user);
            return "register";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passwordEncoded = encoder.encode(user.getLozinka());
            user.setLozinka(passwordEncoded);
            user.setPotvrdaLozinke(passwordEncoded);
            user.setRole(Role.USER);
            userRepo.save(user);
            return "redirect:/auth/login";
        }
    }
}
