import java.util.ArrayList;

public class Sistema {

    private ArrayList<Pessoa> pessoas;
    private ArrayList<Empresa> empresas;


    public Sistema() {
        this.pessoas = new ArrayList<>();
        this.empresas = new ArrayList<>();
    }

    public Usuario buscarUsuario(String login) {
        for (Usuario pessoa : pessoas) {
            if (pessoa.getLogin().equals(login)) {
                return pessoa;
            }
        }

        for (Usuario empresa : empresas) {
            if (empresa.getLogin().equals(login)) {
                return empresa;
            }
        }

        return null;
    }

    public void novaPessoa(Pessoa p){
        this.pessoas.add(p);
    }

    public void novaEmpresa(Empresa e){
        this.empresas.add(e);
    }

    public void listarUsuarios(){
        System.out.println("\n**          USUÁRIOS DISPONÍVEIS        **");
        for(Pessoa pessoa : pessoas){
            System.out.println(pessoa);
        }

        for(Empresa empresa : empresas){
            System.out.println(empresa);
        }
    }
}