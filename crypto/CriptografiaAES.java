package crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CriptografiaAES {
    private static final String CHAVE_SECRETA = "1234567890123456"; // chave 128 bits

    // Método para criptografar texto usando AES
    public static String criptografar(String texto) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] textoCriptografado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(textoCriptografado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar", e);
        }
    }

    // Método para descriptografar texto AES
    public static String descriptografar(String textoCriptografado) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] textoDecodificado = Base64.getDecoder().decode(textoCriptografado);
            byte[] textoOriginal = cipher.doFinal(textoDecodificado);
            return new String(textoOriginal, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar", e);
        }
    }
}
