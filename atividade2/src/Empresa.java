public class Empresa extends Usuario {

    private String cnpj, endereco, site, descricao;
    private Area area;


    public Empresa(String login, String nome, String senha, String cnpj) {
        super(login, nome, senha);
        this.cnpj = cnpj;
        System.out.println("\n**   NOVA EMPRESA CADASTRADA NO SISTEMA    **");
    }

    public String toString() {
        return String.format("%s (%s - %s)", nome, login, cnpj);
    }
}