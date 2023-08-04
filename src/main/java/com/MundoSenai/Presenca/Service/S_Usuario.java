package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Usuario;
import com.MundoSenai.Presenca.Repository.R_Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario) {
        this.r_usuario = r_usuario;
    }

    public static M_Usuario getPessoaLogin(String usuario, String senha) {
        return r_usuario.findByUsuarioESenha(Long.valueOf(usuario), senha);
    }

    public static String cadastrarPessoa(String nome, String email, String CPF, String telefone, String datanasc, String senha, String senhaConf) {
        String mensagemRetorno = "";
        if (!senha.equals(senhaConf)) {
            mensagemRetorno += "A senha e a confirmação devem ser iguais";
        } else if (!ValidacaoCPF.validarCPF(CPF)) {
            mensagemRetorno += "CPF invalido!";
        } else if (nome == null || nome.trim() == "") {
            mensagemRetorno += "deve ser informado um nome";
        } else if (email == null || email.trim() == "" && limparNumeros.limpaNumero(telefone) == null || limparNumeros.limpaNumero(telefone).trim() == "") {
            mensagemRetorno += "e-mail ou Telefone devem ser informados";
        } else {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setEmail(email);
            m_usuario.setCpf(Long.valueOf(limparNumeros.limpaNumero(CPF)));
            m_usuario.setSenha(senha);
            m_usuario.setTelefone(Long.valueOf(telefone));
            m_usuario.setData_nasc(LocalDate.parse(datanasc));
            r_usuario.save(m_usuario);
        }
        return mensagemRetorno;
    }
}
