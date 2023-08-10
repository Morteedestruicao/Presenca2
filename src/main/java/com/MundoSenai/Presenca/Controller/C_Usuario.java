package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Model.M_Usuario;
import com.MundoSenai.Presenca.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("usuario")
public class C_Usuario {
    @GetMapping("/")
    public String helloWorld() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha, HttpSession session,
                            RedirectAttributes redirectAttributes) {
        M_Usuario pessoa = S_Usuario.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", pessoa);
        if (session.getAttribute("usuario") == null) {
            return "Login/login";
        } else {
            redirectAttributes.addFlashAttribute("nome", pessoa.getNome());
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String getHome(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            return "Home/home";
        } else {
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
                               RedirectAttributes redirectAttributes
    ) {
        M_Resposta resposta = S_Usuario.cadastrarPessoa(nome, email, CPF, telefone, datanasc, senha, senhaConf);
        redirectAttributes.addFlashAttribute("mensagem", resposta.getMensagem());
        if (resposta.getSucesso()) {
            redirectAttributes.addFlashAttribute("mensagem", resposta.getMensagem());
            return "Login/login";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", resposta.getMensagem());
            redirectAttributes.addFlashAttribute("nome", nome);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("CPF", CPF);
            redirectAttributes.addFlashAttribute("telefone", telefone);
            return "Pessoa/Cadastro";
        }

    }

    }