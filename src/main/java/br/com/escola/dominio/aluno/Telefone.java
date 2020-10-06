package br.com.escola.dominio.aluno;

public class Telefone {

    private String DDD;
    private String numero;

    public Telefone(String ddd, String numero) {

        if (ddd == null || !(ddd.length() == 2) || !ddd.matches("\\d{2,}")) {
            throw new IllegalArgumentException("ddd invalido");
        }

        if (numero == null || !(numero.length() == 8 || numero.length() == 9) || !numero.matches("\\d{2,}")) {
            throw new IllegalArgumentException("Telefone invalido");
        }

        this.DDD = ddd;
        this.numero = numero;
    }

    public String getDDD() {
        return DDD;
    }

    public String getNumero() {
        return numero;
    }
}
