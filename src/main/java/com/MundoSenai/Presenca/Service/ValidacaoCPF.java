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
            String CPFfinal = CPF;
            if (CPF == null || CPF.length() != 11 || CPF.chars().allMatch(c -> c == CPFfinal.charAt(0))) {
                return false;
            }

            int d1 = calcularDigito(CPF.substring(0, 9), new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2});
            int d2 = calcularDigito(CPF.substring(0, 9) + d1, new int[] {11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

            return CPF.equals(CPF.substring(0, 9) + d1 + d2);
        }

    private static int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Integer.parseInt(str.substring(i, i + 1)) * pesos[i];
        }
        int resultado = 11 - (soma % 11);
        return (resultado > 9) ? 0 : resultado;
        }
}
