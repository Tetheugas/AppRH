package com.AppRH.AppRH.controllers;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository fr;

    @Autowired
    private DependentesRepository dr;

    // chama o forms de cadastrar os funcionarios
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form() {
        return "funcionario/formFuncionario";
    }

    // cadastrar os funcionarios
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarFuncionario";
        }

        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
        return "redirect:/cadastrarFuncionario";

    }

    // list funcionario
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionarios() {
        ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
        Iterable<Funcionario> funcionarios = fr.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    // list dependentes
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView dependentes(@PathVariable("id") long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/dependentes");
        mv.addObject("funcionario", funcionario);

        // lista de dependentes baseada no funcionario
        Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
        mv.addObject("dependentes", dependentes);

        return mv;
    }

    // Adicionar dependentes
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result,
                                  RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/dependentes/{id}";
        }

        if(dr.findByCpf(dependentes.getCpf()) != null) {
            attributes.addFlashAttribute("mensagem_erro", "CPF duplicado!");
            return "redirect:/dependentes/{id}";
        }

        Funcionario funcionario = fr.findById(id);
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso!");
        return "redirect:/dependentes/{id}";
    }

    // delete employee
    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id) {
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);
        return "redirect:/funcionarios";
    }

    // metodos que atualizam funcionarios
    //form
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.GET)
    public ModelAndView editarFuncionarios(long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/update-funcinario");
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    // update funcionario
    @RequestMapping(value = "editar-funcionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

        fr.save(funcionario);
        attributes.addFlashAttribute("success", "Funcionário alterado com sucesso!");

        long idLong = funcionario.getId();
        String id = "" + idLong;
        return "redirect:/dependentes/" + id;
    }

    //Delete dependentes
    @RequestMapping("/deletarDependente")
    public String deletarDependente(String cpf) {
        Dependentes dependente = dr.findByCpf(cpf);

        Funcionario funcionario = dependente.getFuncionario();
        String codigo = "" + funcionario.getId();

        dr.delete(dependente);
        return "redirect:dependentes/" + codigo;
    }

}
