package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Service.S_Usuario;
import com.MundoSenai.Presenca.Model.M_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("usuario")
public class C_Usuario {
    @GetMapping("/")
    public String helloWorld() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha, HttpSession session) {
        session.setAttribute("usuario", S_Usuario.getPessoaLogin(usuario, senha));
        if (session.getAttribute("usuario") == null) {
            return "Login/login";
        } else {
            return "redirect:/home";
        }
    }

    @ModelAttribute("usuario")
    public M_Usuario getUsuario(HttpSession session) {
        return (M_Usuario) session.getAttribute("usuario");
    }

    @GetMapping("/home")
    public String getHome(@ModelAttribute("usuario") String usuario){
        if(usuario != null){
            return "Home/home";
        }
        else {
            return "redirect:/";
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