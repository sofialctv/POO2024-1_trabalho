import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pessoa extends Usuario implements Salvavel {

    private String cpf, bio;
    private Data nasc;
    private ArrayList<Usuario> interesses;

    public Pessoa(String login, String nome, String senha, String cpf, int dia, int mes, int ano) throws CPFInvalidoException, DataInvalidaException {
        super(login, nome, senha);
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new CPFInvalidoException("CPF deve estar no formato ###.###.###-## e conter apenas números, pontos e hífen.");
        }
        this.cpf = cpf;
        this.nasc = new Data(dia, mes, ano);
        this.interesses = new ArrayList<>();
        System.out.println("\n**   NOVA PESSOA CADASTRADA NO SISTEMA    **");
    }

    public Pessoa(BufferedReader r) throws CPFInvalidoException, DataInvalidaException{
        super(r);
        try {
            String cpf = r.readLine();
            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                throw new CPFInvalidoException("CPF deve estar no formato ###.###.###-## e conter apenas números, pontos e hífen.");
            }
            this.cpf = cpf;
            this.nasc = new Data(r);
            this.interesses = new ArrayList<>();
            System.out.println("\n**   NOVA PESSOA CADASTRADA NO SISTEMA    **");

        } catch (IOException e) {
            System.out.println("ERRO AO LER USUARIO.");
        }
    }

    public void salvarArq(BufferedWriter b) throws IOException {
        try {
            b.write("P \n");
            super.salvarArq(b);
            b.write(this.cpf + "\n");
            b.write(this.nasc.getDia() + "\n");
            b.write(this.nasc.getMes() + "\n");
            b.write(this.nasc.getAno() + "\n");

        } catch (IOException e){
            System.out.println("Erro ao salvar arquivo de Pessoa.");
        }
    }

    public String toString(){
        return String.format("%s (%s - %s)", nome, login, cpf);
    }
}
