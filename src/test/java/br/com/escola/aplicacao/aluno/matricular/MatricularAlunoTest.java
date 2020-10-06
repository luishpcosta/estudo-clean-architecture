package br.com.escola.aplicacao.aluno.matricular;
import br.com.escola.aplicacao.MatricularAluno;
import br.com.escola.aplicacao.MatricularAlunoDto;
import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.CPF;
import br.com.escola.dominio.aluno.RepositorioDeAluno;
import br.com.escola.infraestrutura.aluno.RepositorioDeAlunosEmMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatricularAlunoTest {

    @Test
    void alunoDeveriaSerPersistido() {
        RepositorioDeAluno repositorioDeAluno = new RepositorioDeAlunosEmMemoria();
        MatricularAluno matricula = new MatricularAluno(repositorioDeAluno);

        MatricularAlunoDto dto = new MatricularAlunoDto("23171123865","Fulano da Silva", "fulano@email.com");
        matricula.executa(dto);

        Aluno aluno = repositorioDeAluno.buscarPorCPF(new CPF("23171123865"));

        assertNotNull(aluno);
        assertEquals("23171123865", aluno.getCpf());
        assertEquals("Fulano da Silva", aluno.getNome());
        assertEquals("fulano@email.com", aluno.getEmail());
    }
}
