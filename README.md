# Gerenciador de Senhas

Sistema simples em Java para gerenciamento seguro de senhas e credenciais, com:

- Registro e login de usuários com senha criptografada (AES).
- Geração de senhas fortes.
- Armazenamento das credenciais e usuários em arquivos texto no formato CSV.
- Verificação básica de vazamento de senhas (simulada).
- Autenticação para acesso seguro.

---

## Funcionalidades

- Registro de usuário com verificação de duplicidade.
- Login com validação de senha criptografada.
- Armazenamento local dos dados em arquivos `.txt`.
- Geração de senhas fortes e aleatórias.
- Verificação contra senhas expostas.
- Criptografia simétrica AES.

---

## Tecnologias Utilizadas

- **Java 8+**
- **Criptografia AES** com `javax.crypto`
- Armazenamento de dados em arquivos `.txt`
- **Visual Studio Code** com extensão de Java para rodar o projeto

---

## Como Executar no VS Code

1. Abra a pasta do projeto no VS Code.
2. Certifique-se de que você possui as extensões Java instaladas (Java Extension Pack).
3. Abra a classe `Main.java` dentro do pacote `app`.
4. Clique no botão ▶️ `Run` no canto superior da tela ou acima do método `main`.

**Não é necessário compilar manualmente.**

---

## Estrutura do Projeto

- `auth/` — Login e Registro de usuários.
- `crypto/` — Criptografia AES.
- `model/` — Usuário e Credencial.
- `utils/` — Arquivos, senhas e validações.
- `app/` — Classe principal `Main.java`.

---

## Melhorias Futuras

- Migrar o armazenamento para **JSON** ou **banco de dados seguro**.
- Implementar **autenticação de dois fatores (2FA)**.
- Adicionar **interface gráfica (GUI)** com JavaFX ou Swing.
- Substituir criptografia por **hash seguro com salt** (ex: bcrypt, PBKDF2).

