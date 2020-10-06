package br.com.escola.infraestrutura.aluno;

import br.com.escola.dominio.aluno.CifradorDeSenha;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class CifradorDeSenhaComSha512 implements CifradorDeSenha {

    private byte[] salt;

    public CifradorDeSenhaComSha512(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String cifrarSenha(String senha) {
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }

    @Override
    public boolean validarSenhaCifrada(String senhaCifrada, String tentativa) {
        return senhaCifrada.equals(cifrarSenha(tentativa));
    }
}
