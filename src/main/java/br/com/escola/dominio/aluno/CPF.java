package br.com.escola.dominio.aluno;

import br.com.caelum.stella.validation.CPFValidator;

public class CPF {
    private static final CPFValidator validador = new CPFValidator();

    private String numero;

    public CPF(String cpf) {
        if (!validador.isEligible(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.numero = cpf;
    }

    public String getNumero() {
        return numero;
    }
}
