package model;

// Classe que representa uma credencial armazenada (senha, login, serviço)
public class Credencial {
    private String usuarioNome;
    private String servico;
    private String loginServico;
    private String senhaCriptografada;

    public Credencial(String usuarioNome, String servico, String loginServico, String senhaCriptografada) {
        this.usuarioNome = usuarioNome;
        this.servico = servico;
        this.loginServico = loginServico;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public String getServico() {
        return servico;
    }

    public String getLoginServico() {
        return loginServico;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }
}
