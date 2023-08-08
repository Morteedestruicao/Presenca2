package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Usuario;
import com.MundoSenai.Presenca.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario) {
        this.r_usuario = r_usuario;
    }
    public static M_Usuario getPessoaLogin(String usuario, String senha) {
        usuario = limparNumeros.limpaNumero(usuario);
        if (usuario.equals("")){
            return r_usuario.findByUsuarioESenha(Long.valueOf(null), senha);
        }else{
            return r_usuario.findByUsuarioESenha(Long.valueOf(usuario), senha);
        }
    }

    public static String cadastrarPessoa(String nome, String email, String CPF, String telefone, String datanasc, String senha, String senhaConf) {
        boolean cadastroValido = true;
        String mensagemRetorno = "";
        telefone = limparNumeros.limpaNumero(telefone);
        if (telefone.equals("")){
            telefone = null;
        }
        if (!senha.equals(senhaConf)) {
            mensagemRetorno += "A senha e a confirmação devem ser iguais";
            cadastroValido = false;
        } if (!ValidacaoCPF.validarCPF(CPF)) {
            mensagemRetorno += "CPF invalido!";
            cadastroValido = false;
        } if (nome == null || nome.trim() == "") {
            mensagemRetorno += "deve ser informado um nome";
            cadastroValido = false;
        } if ((email == null || email.trim() == "") && (telefone == null)) {
            mensagemRetorno += "e-mail ou Telefone devem ser informados";
            cadastroValido = false;
        } if (cadastroValido){
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setEmail(email);
            m_usuario.setCpf(Long.valueOf(limparNumeros.limpaNumero(CPF)));
            if (telefone != null){
                m_usuario.setTelefone(Long.valueOf(telefone));
            }
            else{
                m_usuario.setTelefone(null);
            }
            m_usuario.setSenha(senha);
            m_usuario.setData_nasc(LocalDate.parse(datanasc));
            try {
                r_usuario.save(m_usuario);
                mensagemRetorno += "cadastro efetuado com sucesso";
            } catch(DataIntegrityViolationException e) {
                if (e.getMessage().contains("u_cpf")){
                    mensagemRetorno += "O Cpf já foi cadastrado!";
                }else{
                    mensagemRetorno += "Erro ao cadastrar";
                }

            }
        }
        return mensagemRetorno;
    }
}
