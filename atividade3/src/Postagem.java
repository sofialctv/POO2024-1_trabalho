import java.util.Collections;

public class Postagem implements Comparable<Postagem>{

    private String foto, legenda, login;
    private Data d;
    private Usuario usuario;

    public Postagem(String f, String l, Data data, Usuario usuario){
        this.foto =  f;
        this.legenda = l;
        this.d = data;
        this.usuario = usuario;
        System.out.println("\n\n**      POSTAGEM CRIADA.     **");
    }

    public void mostrarDados(){
        System.out.println("*");
        System.out.println(this.usuario.nome + " (" + this.usuario.seguidores.size() + " seguidores)");
        System.out.println(this.foto);
        System.out.println(this.legenda);
        System.out.println(this.d.toString());
    }
    public int compareTo(Postagem p) {
        int comparacaoData = this.d.compareTo(p.d);
        if (comparacaoData != 0) {
            return comparacaoData;
        }

        int comparacaoSeguidores = Integer.compare(p.usuario.seguidores.size(), this.usuario.seguidores.size());
        if (comparacaoSeguidores != 0) {
            return comparacaoSeguidores;
        }

        return this.usuario.compareTo(p.usuario);
    }
}