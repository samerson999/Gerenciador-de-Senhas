package utils;

import model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioArquivo {
    private static final String ARQUIVO = "usuarios.txt";

    /**
     * Salva a lista de usuários no arquivo usuarios.txt.
     * Cada usuário é salvo em uma linha no formato:
     * nome,senhaCriptografada
     */
    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario u : usuarios) {
                bw.write(u.getNome() + "," + u.getSenhaCriptografada());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    /**
     * Carrega os usuários do arquivo usuarios.txt.
     * Retorna uma lista vazia caso o arquivo não exista.
     */
    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(ARQUIVO);
        if (!file.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Divide a linha em nome e senha criptografada
                String[] partes = linha.split(",", 2);
                if (partes.length == 2) {
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
        return usuarios;
    }
}
