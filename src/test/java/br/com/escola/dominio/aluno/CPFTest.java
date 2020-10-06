package br.com.escola.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CPFTest {

    @Test
    void naoDeveriaDeixarCriarCPFInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new CPF(null));
        assertThrows(IllegalArgumentException.class, () -> new CPF("131"));
    }

    @Test
    void deveriaDeixarCriarCPFValido() {
        CPF cpf = new CPF("30015135004");
        assertEquals("30015135004", cpf.getNumero());
    }
}
