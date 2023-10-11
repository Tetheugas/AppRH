package com.AppRH.AppRH.controllers;


import com.AppRH.AppRH.repository.CandidateRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VagaController {

    private VagaRepository vr;
    private CandidateRepository cr;

    // Cadastra vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }

}
