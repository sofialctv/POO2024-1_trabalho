import java.io.*;
import java.util.ArrayList;

public class Sistema {

    private ArrayList<Pessoa> pessoas;
    private ArrayList<Empresa> empresas;
    private ArrayList<Salvavel> salvaveis;


    public Sistema() {
        this.pessoas = new ArrayList<>();
        this.empresas = new ArrayList<>();
        salvaveis = new ArrayList<>();
    }

    public void novaPessoa(Pessoa p){
        this.pessoas.add(p);
        salvaveis.add(p);
    }

    public void novaEmpresa(Empresa e){
        this.empresas.add(e);
        salvaveis.add(e);
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

    public Usuario buscarUsuario(String login)  {
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

    public void salvarArq(Sistema s){

        try {
            FileWriter f = new FileWriter("dados.txt");
            BufferedWriter b = new BufferedWriter(f);

            for (Salvavel p : this.salvaveis ) {
                p.salvarArq(b);
            }

            for (Salvavel p : this.salvaveis) {
                if (p instanceof Usuario) {
                    ((Usuario) p).salvarSeguindo(b);
                }
            }

            b.write("F");

            b.close();
        }
        catch (IOException e) {
            System.out.println("ERRO AO SALVAR ARQUIVO.");
        }
    }

    public void read_file() {
        try {
            FileReader fr = new FileReader("dados.txt");
            BufferedReader r = new BufferedReader(fr);

            String line = r.readLine();

            while (line.charAt(0) != 'F') {
                char c = line.charAt(0);

                try {

                    if (c == 'E') {
                        Empresa e = new Empresa(r);
                        this.novaEmpresa(e);
                    } else if (c == 'P') {
                        Pessoa p = new Pessoa(r);
                        this.novaPessoa(p);
                    }else if (c == 'S'){
                        String login = r.readLine();
                        Usuario user = this.buscarUsuario(login);
                        String login2 = r.readLine();
                        Usuario user2 = this.buscarUsuario(login2);
                        if(user != null && user2 != null && user != user2){
                            user.seguir(user2);
                            System.out.println("\n> " + user + " AGORA SEGUE " + user2);
                        } else if (user == user2){
                                System.out.println("USUÁRIO INVÁLIDO. Não é possível seguir a si próprio. Tente novamente.");
                        } else {
                                System.out.println("ERRO AO SEGUIR : USUÁRIO INEXISTENTE. ");
                        }

                    }

                } catch (CPFInvalidoException e) {
                    System.out.println("CPF INVÁLIDO: " + e.getMessage());
                } catch (DataInvalidaException e) {
                    System.out.println("DATA INVÁLIDA: " + e.getMessage());
                } catch (CNPJInvalidoException e) {
                    System.out.println("CNPJ INVÁLIDO: " + e.getMessage());
                }

                line = r.readLine();
            }

            this.listarUsuarios();

            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: ARQUIVO NÃO ENCONTRADO.");
        } catch (IOException e) {
            System.out.println("ERRO AO LER O ARQUIVO.");
        } catch (SeguirAlguemQueJaSegueException e) {
            System.out.println(e.getMessage());
        }
    }


}