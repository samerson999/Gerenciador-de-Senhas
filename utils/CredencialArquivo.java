package utils;

import model.Credencial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CredencialArquivo {

    private static final String ARQUIVO = "credenciais.txt";

    /**
     * Salva a lista de credenciais no arquivo credenciais.txt.
     * Cada credencial é gravada em uma linha no formato:
     * usuarioNome,servico,loginServico,senhaCriptografada
     */
    public static void salvarCredenciais(List<Credencial> credenciais) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Credencial c : credenciais) {
                // Grava os dados separados por vírgula
                bw.write(String.join(",", c.getUsuarioNome(), c.getServico(), c.getLoginServico(), c.getSenhaCriptografada()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar credenciais: " + e.getMessage());
        }
    }

    /**
     * Carrega as credenciais do arquivo credenciais.txt.
     * Retorna uma lista vazia se o arquivo não existir.
     */
    public static List<Credencial> carregarCredenciais() {
        List<Credencial> credenciais = new ArrayList<>();
        File file = new File(ARQUIVO);

        if (!file.exists()) {
            return credenciais;  // Retorna lista vazia se arquivo não existe
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Divide a linha em até 4 partes (nome, serviço, login, senha)
                String[] partes = linha.split(",", 4);
                if (partes.length == 4) {
                    credenciais.add(new Credencial(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar credenciais: " + e.getMessage());
        }

        return credenciais;
    }
}
