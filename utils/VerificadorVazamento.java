package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class VerificadorVazamento {

    public static boolean senhaVazada(String senha) {
        try {
            // 1. Gerar hash SHA-1 da senha
            String hash = sha1(senha).toUpperCase();

            // 2. Separar prefixo (5 primeiros chars) e sufixo (restante)
            String prefixo = hash.substring(0, 5);
            String sufixo = hash.substring(5);

            // 3. Fazer requisição GET para API HIBP
            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefixo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Java Password Leak Checker");

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Erro ao acessar API HIBP: " + responseCode);
                return false; // em caso de erro, considere não vazada para não bloquear o usuário sem motivo
            }

            // 4. Ler resposta da API linha por linha
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                // Cada linha tem formato: SUFIXO_HASH:COUNT
                // Exemplo: 0033E3F9A8B84218EF5AE4A57EFDB3B30CF:2
                if (line.startsWith(sufixo)) {
                    // Encontrou o hash completo na lista -> senha vazada
                    in.close();
                    return true;
                }
            }
            in.close();

            // Se não encontrou na lista, senha não está vazada
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false; // em caso de erro, considerar senha não vazada
        }
    }

    private static String sha1(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = md.digest(input.getBytes("UTF-8"));

        // Converter bytes para string hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
