package auth;

import java.util.Scanner;
import java.util.Random;

public class Autenticacao2FA {
    private static String codigoAtual = "";
    private static long tempoUltimoCodigo = 0;

    public static boolean autenticar() {
        Scanner scanner = new Scanner(System.in);
        gerarCodigo();

        while (true) {
            System.out.println("\n=== Autenticação 2FA ===");
            System.out.println("1. Digitar código");
            System.out.println("2. Gerar novo código (aguarde 60s)");
            System.out.println("3. Voltar ao menu principal");
            System.out.println("0. Sair do sistema");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o código: ");
                    String entrada = scanner.nextLine();
                    if (codigoAtual.equals(entrada)) {
                        System.out.println("Autenticado com sucesso!");
                        return true;
                    } else {
                        System.out.println("Código incorreto. Tente novamente.");
                    }
                    break;

                case "2":
                    long agora = System.currentTimeMillis();
                    if ((agora - tempoUltimoCodigo) >= 60000) {
                        gerarCodigo();
                        System.out.println("Novo código gerado: " + codigoAtual); // Simula envio
                    } else {
                        long segundosRestantes = (60000 - (agora - tempoUltimoCodigo)) / 1000;
                        System.out.println("Aguarde " + segundosRestantes + " segundos para gerar novo código.");
                    }
                    break;

                case "3":
                    System.out.println("Retornando ao menu sem autenticar...");
                    return false;

                case "0":
                    System.out.println("Encerrando o sistema...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerarCodigo() {
        Random rand = new Random();
        int numero = 100000 + rand.nextInt(900000); // Gera código 6 dígitos
        codigoAtual = String.valueOf(numero);
        tempoUltimoCodigo = System.currentTimeMillis();
        System.out.println("Código 2FA gerado: " + codigoAtual); // Apenas simulação para teste
    }
}
