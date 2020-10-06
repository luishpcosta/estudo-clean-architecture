package br.com.escola.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelefoneTest {

    @Test
    void naoDeveriaDeixarCriarTelefoneInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Telefone(null, null));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("131", "12457845"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("41", "9124578459"));
    }

    @Test
    void deveriaDeixarCriarTelefoneValido() {

        Telefone telefone1 = new Telefone("41", "45128956");
        Telefone telefone2 = new Telefone("11", "945659878");

        assertEquals("41", telefone1.getDDD());
        assertEquals("45128956", telefone1.getNumero());

        assertEquals("11", telefone2.getDDD());
        assertEquals("945659878", telefone2.getNumero());
    }
}
