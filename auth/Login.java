package auth;

import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class Login {

    public static Usuario autenticar(List<Usuario> usuarios) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Usuário: ");
            String nome = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Compara nome e valida senha usando método seguro do Usuario
            for (Usuario u : usuarios) {
                if (u.getNome().equals(nome) && u.verificarSenha(senha)) {
                    System.out.println("Login realizado com sucesso!");
                    return u;
                }
            }

            System.out.println("Credenciais inválidas. Tentar novamente?");
        }
    }
}
