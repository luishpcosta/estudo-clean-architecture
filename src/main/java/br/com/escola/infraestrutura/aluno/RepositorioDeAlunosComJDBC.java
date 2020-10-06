package br.com.escola.infraestrutura.aluno;

import br.com.escola.dominio.aluno.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAluno {

    private final Connection connection;

    RepositorioDeAlunosComJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno) {

        try {
            String sql = "INSERT INTO aluno VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, aluno.getCpf());
            preparedStatement.setString(2, aluno.getNome());
            preparedStatement.setString(3, aluno.getEmail());
            preparedStatement.execute();

            sql = "INSERT INTO telefone VALUES(?,?,a.id_aluno) SELECT id_aluno from aluno a where cpf=? ";
            preparedStatement = connection.prepareStatement(sql);
            for (Telefone telefone : aluno.getTelefones()) {
                preparedStatement.setString(1, telefone.getDDD());
                preparedStatement.setString(2, telefone.getNumero());
                preparedStatement.setString(3, aluno.getCpf());
                preparedStatement.execute();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) {
        Aluno aluno;

        try {
            String sql = "SELECT cpf, nome, email  FROM aluno WHERE cpf=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf.getNumero());
            ResultSet resultSet = preparedStatement.executeQuery();


            int i = 0;
            var cpfDb = resultSet.getString(++i);
            var nomeDb = resultSet.getString(++i);
            var emailDb = resultSet.getString(++i);
            aluno = new FabricaDeAluno().comNomeCPFEmail(cpfDb, nomeDb, emailDb).criar();


            setTelefonesDoAlunoPorCPF(cpf.getNumero(), aluno);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return aluno;
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            String sql = "SELECT cpf, nome, email  FROM aluno";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            do {
                int i = 0;
                var cpfDb = resultSet.getString(++i);
                var nomeDb = resultSet.getString(++i);
                var emailDb = resultSet.getString(++i);
                alunos.add(new FabricaDeAluno().comNomeCPFEmail(cpfDb, nomeDb, emailDb).criar());
            } while (resultSet.next());

            for (Aluno aluno : alunos) {
                setTelefonesDoAlunoPorCPF(aluno.getCpf(), aluno);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void setTelefonesDoAlunoPorCPF(String cpf, Aluno aluno) throws SQLException {
        String sql = "SELECT ddd, numero FROM telefone t JOIN usuario u on u.id_usuario=t.id_usuario WHERE u.cpf=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        ResultSet resultSet = preparedStatement.executeQuery();

        int i;
        while (resultSet.next()) {
            i = 0;
            var ddd = resultSet.getString(++i);
            var telefone = resultSet.getString(++i);
            aluno.adicionarTelefone(ddd, telefone);
        }
    }
}
