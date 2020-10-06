package br.com.escola.aplicacao;

import br.com.escola.dominio.aluno.Aluno;

public interface EnviarEmailIndicacao {

    boolean enviarPara(Aluno indicado);
}
