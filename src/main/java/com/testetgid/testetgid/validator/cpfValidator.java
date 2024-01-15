package com.testetgid.testetgid.validator;

import com.testetgid.testetgid.exeptions.CPFInvalidException;

public class cpfValidator {

    public static void validateCPF(String cpf) {

        if(!isValidCPF(cpf)){
            throw new CPFInvalidException("CPF inválido");
            }
    }

    public static boolean isValidCPF(String cpf) {


        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);

        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);

        digito2 = (digito2 > 9) ? 0 : digito2;

        return (Character.getNumericValue(cpf.charAt(9)) == digito1) && (Character.getNumericValue(cpf.charAt(10)) == digito2);
    }
}
