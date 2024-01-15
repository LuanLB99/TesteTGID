package com.testetgid.testetgid.validator;

import com.testetgid.testetgid.exeptions.CNPJInvalidException;


public class CnpjValidator {

    public static void validateCNPJ(String cnpj){
     if(!isValidCNPJ(cnpj)){
            throw new CNPJInvalidException("CNPJ inválido");
            }
    }

    public static boolean isValidCNPJ(String cnpj) {

        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int digito1 = 11 - (soma % 11);

        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int digito2 = 11 - (soma % 11);

        digito2 = (digito2 > 9) ? 0 : digito2;

        return (Character.getNumericValue(cnpj.charAt(12)) == digito1) && (Character.getNumericValue(cnpj.charAt(13)) == digito2);
    }
}
