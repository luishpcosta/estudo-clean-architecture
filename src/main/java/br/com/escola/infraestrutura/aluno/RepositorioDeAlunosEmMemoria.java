package br.com.escola.infraestrutura.aluno;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.CPF;
import br.com.escola.dominio.aluno.RepositorioDeAluno;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioDeAlunosEmMemoria implements RepositorioDeAluno {

    HashMap<String, Aluno> matriculados = new HashMap<>();

    @Override
    public void matricular(Aluno aluno) {
        matriculados.put(aluno.getCpf(), aluno);
    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) {
        return Optional.of(matriculados.get(cpf.getNumero())).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return matriculados.values().stream().collect(Collectors.toList());
    }
}
