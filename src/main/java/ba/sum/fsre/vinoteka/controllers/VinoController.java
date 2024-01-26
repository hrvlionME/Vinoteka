package ba.sum.fsre.vinoteka.controllers;

import ba.sum.fsre.vinoteka.models.User;
import ba.sum.fsre.vinoteka.models.Vino;
import ba.sum.fsre.vinoteka.repositories.KorpaRepository;
import ba.sum.fsre.vinoteka.repositories.UserRepository;
import ba.sum.fsre.vinoteka.repositories.VinoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    KorpaRepository korpaRepository;

    private static String UPLOADED_FOLDER = "/app/uploads/";
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
    @Transactional
    @PostMapping("/edit/delete/{vinoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteVino(@PathVariable Long vinoId) {
        Vino vino = vinoRepo.findById(vinoId).orElse(null);
        korpaRepository.deleteByVinoId(vinoId);
        try {
            Path filePath = Paths.get(UPLOADED_FOLDER + vino.getLokacijaSlike());
            Files.deleteIfExists(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        vinoRepo.deleteById(vinoId);
        return "redirect:/edit";
    }



    @PostMapping("/upload")
    public String addVino(@Valid Vino vino, @RequestParam("file") MultipartFile file, BindingResult result, Model model,
                          @RequestParam("cijena") String cijenaInput){
        boolean errors = result.hasErrors();
        if(errors){;
            model.addAttribute("vino", vino);
            return "/edit/add";
        } else {
            if (file != null && !file.isEmpty()) {
                try {
                    double cijena = Double.parseDouble(cijenaInput);
                    vino.setCijena(cijena);

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
