package br.com.escola.infraestrutura.aluno;

import br.com.escola.aplicacao.EnviarEmailIndicacao;
import br.com.escola.dominio.aluno.Aluno;

public class EnviarEmailIndicacaoComJavaMail implements EnviarEmailIndicacao {

    private String template;

    public EnviarEmailIndicacaoComJavaMail(String template) {
        this.template = template;
    }

    @Override
    public boolean enviarPara(Aluno indicado) {
        // TODO implementar envio com a lib JavaMail
        return false;
    }
}
