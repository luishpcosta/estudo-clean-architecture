package br.com.escola.aplicacao;

import br.com.escola.dominio.aluno.Aluno;
import br.com.escola.dominio.aluno.FabricaDeAluno;

public class MatricularAlunoDto {

    private String cpf;
    private String nome;
    private String email;

    public MatricularAlunoDto(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Aluno criarAluno() {
        return new FabricaDeAluno().comNomeCPFEmail(nome, cpf, email).criar();
    }
}
