package utils;

import java.security.SecureRandom;

public class GeradorSenha {
    // Conjunto de caracteres permitidos na senha
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*()-_=+";
    private static final SecureRandom random = new SecureRandom();

    /**
     * Gera uma senha aleatória com o tamanho especificado,
     * escolhendo caracteres aleatórios da string CARACTERES.
     * @param tamanho tamanho da senha a ser gerada
     * @return senha aleatória
     */
    public static String gerarSenha(int tamanho) {
        StringBuilder senha = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(CARACTERES.length());
            senha.append(CARACTERES.charAt(index));
        }
        return senha.toString();
    }
}
