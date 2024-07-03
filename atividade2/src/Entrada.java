
import javax.xml.transform.Source;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior, David
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt"));
            // NAO ALTERE A LOCALICAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public int menu1() {
        // Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar pessoa.\n" +
                "2) Cadastrar empresa.\n" +
                "3) Login.\n" +
                "0) Sair.\n";

        int op = this.lerInteiro(msg);
        System.out.println(op);

        while (op < 0 || op > 3) {
            System.out.println("OPÇÃO INVÁLIDA. Tente novamente: \n");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    public int menu2(Sistema s, Usuario u){
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Seguir alguém.\n" +
                "2) Fazer postagem.\n" +
                "3) Exibir seu feed.\n" +
                "0) Sair.\n";

        int op = this.lerInteiro(msg);
        System.out.println(op);

        while (op < 0 || op > 3) {
            System.out.println("OPÇÃO INVÁLIDA. Tente novamente: \n");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    /***************************************************/

    /**
     * Lê os dados de uma nova Pessoa e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadPessoa(Sistema s) {
        String login = this.lerLinha("\nEscolha um login: ");

        while (s.buscarUsuario(login) != null) {
            login = this.lerLinha("USUÁRIO JÁ EXISTENTE. Escolha outro login: ");
        }

        String nome = this.lerLinha("Digite seu nome: ");
        String senha = this.lerLinha("Digite sua senha: ");
        String cpf = this.lerLinha("Digite seu CPF: ");
        int dia = this.lerInteiro("Digite seu dia de nascimento: ");
        int mes = this.lerInteiro("Digite seu mês de nascimento: ");
        int ano = this.lerInteiro("Digite seu ano de nascimento: ");

        Pessoa p = new Pessoa(login, nome, senha, cpf, dia, mes, ano);
        s.novaPessoa(p);
        System.out.println(p);
    }

    public void cadEmpresa(Sistema s) {
        String login = this.lerLinha("\nEscolha um login: ");

        while (s.buscarUsuario(login) != null) {
            login = this.lerLinha("USUÁRIO JÁ EXISTENTE. Escolha outro login: ");
        }

        String nome = this.lerLinha("Digite seu nome: ");
        String senha = this.lerLinha("Digite sua senha: ");
        String cnpj = this.lerLinha("Digite seu CNPJ: ");

        Empresa e = new Empresa(login, nome, senha, cnpj);
        s.novaEmpresa(e);
        System.out.println(e);
    }
    
    
    public void login(Sistema s){
        String login = this.lerLinha("\nEscolha um login: ");

        while (s.buscarUsuario(login) == null) {
            login = this.lerLinha("Usuário inexistente. Escolha outro login: ");
        }

        Usuario u = s.buscarUsuario(login);
        String senha = this.lerLinha("Digite sua senha: ");
        
        if(u.validarAcesso(senha)){
            System.out.println("\n**    USUÁRIO LOGADO COM SUCESSO: " + u +"    **");
            int op2 = this.menu2(s, u);

            while (op2 != 0) {
                if (op2 == 1) {
                    this.seguir(s, u);
                }
                if (op2 == 2) {
                    this.postar(u);
                }
                if (op2 == 3) {
                    this.feed(u);
                }
    
                op2 = this.menu2(s, u);
            }
        }
        else {
            System.out.println("\n**   LOGIN INCOMPLETO. ACESSO INVÁLIDO.    **");
        }
    }

    public void seguir(Sistema s, Usuario u){
        s.listarUsuarios();

        String login = this.lerLinha("\nEscolha um usuário para seguir: ");

        while (s.buscarUsuario(login) == null) {
            login = this.lerLinha("USUÁRIO INEXISTENTE. Escolha outro usuário: ");
        }

        Usuario user = s.buscarUsuario(login);
        u.seguir(user);
        System.out.println("\n> " + u + " AGORA SEGUE " + user);
    }

    public void postar(Usuario u){

        System.out.println("\n**      CRIANDO POSTAGEM      **");
        String foto = this.lerLinha("Nome da foto: ");
        String legenda = this.lerLinha("Legenda: ");
        int dia = this.lerInteiro("Dia: ");
        int mes = this.lerInteiro("Mês: ");
        int ano = this.lerInteiro("Ano: ");
        String senha = this.lerLinha("Informe sua senha: ");

        Data d = new Data(dia, mes, ano);

        u.postarFoto(foto, legenda, d, senha);
    }

    public void feed(Usuario u){
        System.out.println("\n**    FEED DE " + u.login + "     **" );
        u.feed();
    }
}