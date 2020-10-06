package br.com.escola.dominio.aluno;

public class FabricaDeAluno {

    private Aluno aluno;

    public FabricaDeAluno comNomeCPFEmail(String nome, String cpf, String email) {
        if (this.aluno != null) {
            throw new IllegalArgumentException("Recorrência de chamada, apenas uma chamada comNomeCPFEmail por instância");
        }

        this.aluno = new Aluno(new CPF(cpf), nome, new Email(email));
        return this;
    }

    public FabricaDeAluno comTelefone(String ddd, String numero) {
        if (this.aluno == null) {
            throw new IllegalArgumentException("Ordem incorreta, chamar o comNomeCPFEmail antes");
        }

        this.aluno.adicionarTelefone(ddd, numero);
        return this;
    }

    public Aluno criar() {
        return this.aluno;
    }

}
