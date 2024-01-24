package ba.sum.fsre.vinoteka.controllers;
import ba.sum.fsre.vinoteka.models.Korpa;
import ba.sum.fsre.vinoteka.models.User;
import ba.sum.fsre.vinoteka.models.Vino;
import ba.sum.fsre.vinoteka.repositories.KorpaRepository;
import ba.sum.fsre.vinoteka.repositories.UserRepository;
import ba.sum.fsre.vinoteka.repositories.VinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
public class KorpaController {
@Autowired
    KorpaRepository korpaRepository;

@Autowired
    VinoRepository vinoRepository;

@Autowired
    UserRepository userRepository;

@PostMapping("/korpa")
    public String addKorpa(@RequestParam("vinoIdsInput") String vinoIdsInput,
                           @RequestParam("userIdInput") Long userId){

    User user = userRepository.findById(userId).orElseThrow();
    String[] vinoIdsArray = vinoIdsInput.split(",");
    Long[] vinoIds = Arrays.stream(vinoIdsArray)
            .map(Long::valueOf)
            .toArray(Long[]::new);

    List<Vino> vina = vinoRepository.findAllById(Arrays.asList(vinoIds));

    for (Vino vino : vina) {
        Korpa korpa = new Korpa();
        korpa.setUser(user);
        korpa.setVino(vino);
        korpaRepository.save(korpa);
    }

    return "redirect:/store";
}
}
