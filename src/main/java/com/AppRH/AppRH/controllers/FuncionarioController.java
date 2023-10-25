package com.AppRH.AppRH.controllers;

import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository fr;

    @Autowired
    private DependentesRepository dr;

}
