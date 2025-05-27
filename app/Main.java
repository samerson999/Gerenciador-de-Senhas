package app;

import auth.Login;
import auth.Registro;
import auth.Autenticacao2FA;
import model.Usuario;
import utils.UsuarioArquivo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Carrega usuários já cadastrados no sistema
        List<Usuario> usuarios = UsuarioArquivo.carregarUsuarios();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Gerenciador de Senhas ===");
            System.out.println("1. Login");
            System.out.println("2. Registrar-se");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    // Autenticação do usuário
                    Usuario user = Login.autenticar(usuarios);
                    if (user != null) {
                        System.out.println("Realize a autenticação 2FA para continuar.");
                        // Requisita autenticação 2FA para segurança extra
                        boolean autenticado2FA = Autenticacao2FA.autenticar();
                        if (autenticado2FA) {
                            // Exibe menu do usuário autenticado
                            Menu.exibir(user);
                        } else {
                            System.out.println("Falha na autenticação 2FA. Voltando ao menu principal.");
                        }
                    }
                    break;

                case "2":
                    // Registro de novo usuário
                    Registro.registrar(usuarios);
                    UsuarioArquivo.salvarUsuarios(usuarios);
                    break;

                case "3":
                    // Finaliza o programa
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
