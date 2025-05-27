package model;

import crypto.CriptografiaAES;

// Classe que representa o usuário do sistema
public class Usuario {
    private String nome;
    private String senhaCriptografada;

    public Usuario(String nome, String senhaCriptografada) {
        this.nome = nome;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getNome() {
        return nome;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    // Verifica se a senha digitada corresponde à senha armazenada
    public boolean verificarSenha(String senhaDigitada) {
        try {
            String senhaOriginal = CriptografiaAES.descriptografar(senhaCriptografada);
            return senhaOriginal.equals(senhaDigitada);
        } catch (Exception e) {
            return false;
        }
    }
}
