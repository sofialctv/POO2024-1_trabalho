import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Empresa extends Usuario implements Salvavel {

    private String cnpj, endereco, site, descricao;
    private Area area;


    public Empresa(String login, String nome, String senha, String cnpj) throws CNPJInvalidoException{
        super(login, nome, senha);
        if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
            throw new CNPJInvalidoException("CNPJ deve estar no formato ##.###.###/####-## e conter apenas números, pontos, hífen e /.");
        }
        this.cnpj = cnpj;
        System.out.println("\n**   NOVA EMPRESA CADASTRADA NO SISTEMA    **");
    }

    public Empresa(BufferedReader r) throws CNPJInvalidoException{
        super(r);
        try {
            String cnpj = r.readLine();
            if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
                throw new CNPJInvalidoException("CNPJ deve estar no formato ##.###.###/####-## e conter apenas números, pontos, hífen e /.");
            }
            this.cnpj = cnpj;
            System.out.println("\n**   NOVA EMPRESA CADASTRADA NO SISTEMA    **");
        } catch (IOException e){
            System.out.println("ERRO AO LER ARQUIVO");
        }
    }

    public void salvarArq(BufferedWriter b) throws IOException {
        try {
            b.write("E\n");
            super.salvarArq(b);
            b.write(this.cnpj + "\n");
        } catch (IOException e){
            System.out.println("Erro ao salvar arquivo de Empresa.");
        }
    }

    public String toString() {
        return String.format("%s (%s - %s)", nome, login, cnpj);
    }
}