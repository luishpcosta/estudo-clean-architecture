package br.com.escola.dominio.aluno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aluno {

    private CPF cpf;
    private String nome;
    private Email email;
    private List<Telefone> telefones = new ArrayList<>();
    private String senha;

    public Aluno(CPF cpf, String nome, Email email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public String getCpf() {
        return this.cpf.getNumero();
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email.getEndereco();
    }

    public void adicionarTelefone(String ddd, String numero) {
        this.telefones.add(new Telefone(ddd, numero));
    }

    public List<Telefone> getTelefones() {
        return Collections.unmodifiableList(telefones);
    }
}
