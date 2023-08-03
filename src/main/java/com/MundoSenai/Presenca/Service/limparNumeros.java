package com.MundoSenai.Presenca.Service;

public class limparNumeros {
    public static String limpaNumero(String number) {
        if (number == null) {
            return null;
        }
        String numeroLimpo = number.replaceAll("[^\\d]", "");

        return numeroLimpo;
    }
}
