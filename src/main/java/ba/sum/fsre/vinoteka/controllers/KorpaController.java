package ba.sum.fsre.vinoteka.controllers;
import ba.sum.fsre.vinoteka.models.Korpa;
import ba.sum.fsre.vinoteka.models.User;
import ba.sum.fsre.vinoteka.models.Vino;
import ba.sum.fsre.vinoteka.repositories.KorpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KorpaController {
@Autowired
    KorpaRepository korpaRepository;

@PostMapping("/korpa")
    public String addKorpa(@RequestParam User user, @RequestParam List<Vino> vino){

 for(Vino vina:vino) {
     Korpa korpa = new Korpa();
     korpa.setUser(user);
     korpa.setVina(vino);
     korpaRepository.save(korpa);
 }

    return "?????????";
}
}
