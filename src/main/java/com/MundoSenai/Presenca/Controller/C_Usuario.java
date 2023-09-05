package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Model.M_Usuario;
import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C_Usuario {


    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        M_Usuario pessoa = S_Usuario.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", usuario);
        if (session.getAttribute("usuario") == null) {
            return "Login/login";
        } else {
            redirectAttributes.addFlashAttribute("nome",pessoa.getNome());
            return "redirect:/Home";
        }
    }

    @ModelAttribute("usuario")
    public M_Usuario getUsuario(HttpSession session) {
        return (M_Usuario) session.getAttribute("usuario");
    }

    @GetMapping("/Home")
    public String getHome(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            return "Home/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/cadastro")
    public String GetCadastro() {
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public M_Resposta postcadastro(@RequestParam("nome") String nome,
                                   @RequestParam("cpf") String cpf,
                                   @RequestParam("email") String email,
                                   @RequestParam("telefone") String telefone,
                                   @RequestParam("datanasc") String datanasc,
                                   @RequestParam("senha") String senha,
                                   @RequestParam("confsenha") String confsenha,
                                   RedirectAttributes redirectAttributes) {

        return S_Usuario.cadastrarPessoa(nome, cpf, email, telefone, datanasc, senha, confsenha);
    }
}