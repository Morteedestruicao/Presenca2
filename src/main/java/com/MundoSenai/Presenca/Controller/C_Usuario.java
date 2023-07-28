package com.MundoSenai.Presenca.Controller;

import com.MundoSenai.Presenca.Service.S_Usuario;
import org.springframework.stereotype.Controller;
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
        if(S_Usuario.getPessoaLogin(usuario, senha)== null){
            return"Login/login";
        }else{
            return "teste";
        }
    }
}
