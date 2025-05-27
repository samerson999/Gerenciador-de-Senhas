package app;

import model.Usuario;
import model.Credencial;
import utils.CredencialArquivo;
import utils.GeradorSenha;
import crypto.CriptografiaAES;
import utils.VerificadorVazamento;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void exibir(Usuario user) {
        Scanner scanner = new Scanner(System.in);
        // Carrega todas as credenciais salvas
        List<Credencial> todasCredenciais = CredencialArquivo.carregarCredenciais();

        while (true) {
            System.out.println("\nBem-vindo, " + user.getNome());
            System.out.println("1. Adicionar senha");
            System.out.println("2. Listar senhas");
            System.out.println("3. Verificar se uma senha foi exposta");
            System.out.println("4. Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    adicionarCredencial(user, todasCredenciais);
                    break;

                case "2":
                    listarCredenciaisDoUsuario(todasCredenciais, user.getNome());
                    break;

                case "3":
                    verificarSenhaVazada();
                    break;

                case "4":
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarCredencial(Usuario user, List<Credencial> todasCredenciais) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do serviço: ");
        String servico = scanner.nextLine();

        System.out.print("Login do serviço: ");
        String loginServico = scanner.nextLine();

        String senha;

        while (true) {
            // Permite gerar senha segura automaticamente ou usar senha própria
            System.out.print("Deseja que o sistema gere uma senha segura para você? (s/n): ");
            String opcaoGerar = scanner.nextLine();

            if (opcaoGerar.equalsIgnoreCase("s")) {
                senha = GeradorSenha.gerarSenha(16);
                System.out.println("Senha gerada automaticamente: " + senha);
            } else {
                System.out.print("Digite a senha: ");
                senha = scanner.nextLine();
            }

            // Verifica se a senha foi exposta em vazamentos
            if (VerificadorVazamento.senhaVazada(senha)) {
                System.out.println("Atenção: essa senha já foi exposta em vazamentos! Por favor, escolha outra.");
            } else {
                break;
            }
        }

        // Criptografa a senha antes de salvar
        String senhaCriptografada = CriptografiaAES.criptografar(senha);

        Credencial nova = new Credencial(user.getNome(), servico, loginServico, senhaCriptografada);
        todasCredenciais.add(nova);
        CredencialArquivo.salvarCredenciais(todasCredenciais);

        System.out.println("Credencial adicionada com sucesso!");
    }

    private static void listarCredenciaisDoUsuario(List<Credencial> todasCredenciais, String nomeUsuario) {
        System.out.println("\nSuas credenciais:");

        boolean encontrou = false;

        for (Credencial c : todasCredenciais) {
            if (c.getUsuarioNome().equalsIgnoreCase(nomeUsuario)) {
                System.out.println("- Serviço: " + c.getServico());
                System.out.println("  Login: " + c.getLoginServico());
                // Descriptografa para mostrar ao usuário
                System.out.println("  Senha: " + CriptografiaAES.descriptografar(c.getSenhaCriptografada()));
                System.out.println();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma credencial cadastrada.");
        }
    }

    private static void verificarSenhaVazada() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a senha para verificar se foi exposta: ");
        String senha = scanner.nextLine();

        if (VerificadorVazamento.senhaVazada(senha)) {
            System.out.println("Atenção: essa senha foi exposta em vazamentos!");
        } else {
            System.out.println("Essa senha não foi encontrada em vazamentos conhecidos.");
        }
    }
}
