package auth;

import crypto.CriptografiaAES;
import model.Usuario;
import utils.UsuarioArquivo;
import utils.VerificadorVazamento;

import java.util.List;
import java.util.Scanner;

public class Registro {

    // Método para registrar um novo usuário
    public static void registrar(List<Usuario> usuarios) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Registro de novo usuário ===");

        while (true) {
            System.out.print("Digite o nome de usuário: ");
            String nome = scanner.nextLine();

            // Verifica se o nome já existe
            boolean existe = false;
            for (Usuario u : usuarios) {
                if (u.getNome().equalsIgnoreCase(nome)) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                System.out.println("Usuário já existe. Tente outro nome.");
                System.out.print("Deseja tentar novamente? (s/n): ");
                String opcao = scanner.nextLine();
                if (!opcao.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando registro.");
                    return;
                }
            } else {
                String senha;

                // Loop para garantir senha segura e não vazada
                while (true) {
                System.out.print("Digite a senha: ");
                senha = scanner.nextLine();

                System.out.print("Confirme a senha: ");
                String confirmacao = scanner.nextLine();

                if (!senha.equals(confirmacao)) {
                    System.out.println("As senhas não coincidem. Tente novamente.");
                    continue;
                }

                if (VerificadorVazamento.senhaVazada(senha)) {
                    System.out.println("Atenção: essa senha já foi exposta em vazamentos! Por favor, escolha outra.");
                } else {
                    break; // senha válida, sai do loop
                }
            }

                // Criptografa a senha antes de salvar
                String senhaCriptografada = CriptografiaAES.criptografar(senha);
                usuarios.add(new Usuario(nome, senhaCriptografada));
                UsuarioArquivo.salvarUsuarios(usuarios);

                System.out.println("Usuário registrado com sucesso!");
                return;
            }
        }
    }
}
