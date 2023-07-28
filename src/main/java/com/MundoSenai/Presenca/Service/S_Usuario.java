package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Usuario;
import com.MundoSenai.Presenca.Repository.R_Usuario;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario){
        this.r_usuario = r_usuario;
    }

    public static M_Usuario getPessoaLogin(String usuario, String senha){
        return r_usuario.findByUsuarioESenha(Long.valueOf(usuario),senha);
    }

}
