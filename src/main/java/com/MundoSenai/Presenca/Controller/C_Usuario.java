package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Service.S_Usuario;
import com.MundoSenai.Presenca.Model.M_Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class C_Usuario {
    @GetMapping("/")
    public String helloWorld() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha) {
        if (S_Usuario.getPessoaLogin(usuario, senha) == null) {
            return "Login/login";
        } else {
            return "templates/Home/home";
        }
    }

    @GetMapping("/cadastro")
    public String getCadastro() {
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@RequestParam("nome") String nome,
                               @RequestParam("email") String email,
                               @RequestParam("CPF") String CPF,
                               @RequestParam("telefone") String telefone,
                               @RequestParam("senha") String senha,
                               @RequestParam("data_nasc") String datanasc,
                               @RequestParam("senhaConf") String senhaConf,
                               Model model) {
        String mensagem = S_Usuario.cadastrarPessoa(nome, email, CPF, telefone, datanasc, senha, senhaConf);
        model.addAttribute("mensagem",mensagem);
        model.addAttribute("nome", nome);
        model.addAttribute("email", email);
        model.addAttribute("CPF", CPF);
        model.addAttribute("telefone", telefone);
        return "Pessoa/Cadastro";
    }
}