package com.example.oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private BiletterRepository rep;


    @PostMapping("/lagre")
    public void lagreBiletter (Biletter nyBilett){
        rep.lagreBiletter(nyBilett);
    }

    @GetMapping("/hent")
    public List<Biletter> hentAlle(){
        return rep.hentAlle();
    }

    @GetMapping("/slett")
    public void slettBiletter(){
        rep.slettAlle();
    }



}


