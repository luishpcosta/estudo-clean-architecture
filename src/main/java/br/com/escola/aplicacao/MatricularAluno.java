package br.com.escola.aplicacao;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.RepositorioDeAluno;

public class MatricularAluno {
    private final RepositorioDeAluno repositorio;

    public MatricularAluno(RepositorioDeAluno repositorio) {
        this.repositorio = repositorio;
    }

    public void executa(MatricularAlunoDto matricularAlunoDto) {
        Aluno aluno = matricularAlunoDto.criarAluno();
        this.repositorio.matricular(aluno);
    }

}
