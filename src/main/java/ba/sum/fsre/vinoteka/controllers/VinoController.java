package ba.sum.fsre.vinoteka.controllers;

import ba.sum.fsre.vinoteka.models.User;
import ba.sum.fsre.vinoteka.models.Vino;
import ba.sum.fsre.vinoteka.repositories.UserRepository;
import ba.sum.fsre.vinoteka.repositories.VinoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> dc7c5b8 (homepage)
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class VinoController {

    @Autowired
    VinoRepository vinoRepo;

    @Autowired
    UserRepository userRepository;


    private static String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
    @GetMapping("/store")
    public String gallery(Model model){
        List<Vino> vina = vinoRepo.findAll();
        model.addAttribute("vina", vina);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username);
            model.addAttribute("loggedInUser", user);
        }
        return "gallery";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal){
        List<Vino> vina = vinoRepo.findAll();
        model.addAttribute("vina", vina);
        return "edit";
    }
    @GetMapping("/edit/add")
    public String add(Model model){
        model.addAttribute("vino", new Vino());
        return "add";
    }

    @GetMapping("/showcase")
    public String showcase(Model model){
        List<Vino> vina = vinoRepo.findAll();
        model.addAttribute("vina", vina);
        return "showcase";
    }

 /*   @PostMapping("/edit/delete/{}")
     @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteVino(@PathVariable Long ) {
        vinoRepo.deleteById();
        return "redirect:/edit";
    }
 */


    @PostMapping("/upload")
    public String addVino(@Valid Vino vino, @RequestParam("file") MultipartFile file, BindingResult result, Model model){
        boolean errors = result.hasErrors();
        if(errors){;
            model.addAttribute("vino", vino);
            return "/edit/add";
        } else {
            if (file != null && !file.isEmpty()) {
                try {
                    String originalFilename = file.getOriginalFilename();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFilename;
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + uniqueFileName);
                    Files.write(path, bytes);
                    vino.setLokacijaSlike(uniqueFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            vinoRepo.save(vino);
            return "redirect:/edit";
        }
    }
}
