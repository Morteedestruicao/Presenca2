package com.MundoSenai.Presenca.Service;

public class ValidacaoCPF {
        public static boolean validarCPF(String CPF) {
            CPF = limparNumeros.limpaNumero(CPF);

            if (CPF.length() != 11){
                return false;
            }
            if (CPF.matches("(\\d)\\1{10}")){
                return false;
            }

            else
            return true;
        }
}
