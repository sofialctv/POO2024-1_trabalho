import java.util.ArrayList;

public class Usuario {

  protected String login, nome, senha;
  protected Local cidade;
  protected ArrayList<Postagem> posts;
  protected ArrayList<Usuario> seguindo, seguidores;
  protected ArrayList<Pessoa> interessados;


  public Usuario(String login, String nome, String senha) {
    this.login = login;
    this.nome = nome;
    this.senha = senha;
    this.posts = new ArrayList<>();
    this.seguindo = new ArrayList<>();
    this.seguidores = new ArrayList<>();
    this.interessados = new ArrayList<>();
  }

  public String getLogin() {
    return login;
  }

  public boolean validarAcesso(String pwd){
    return this.senha.equals(pwd);
  }

  
  public void postarFoto(String foto, String legenda, Data hoje, String senha){
    if(validarAcesso(senha)){
      this.posts.add(new Postagem(foto, legenda, hoje));
      System.out.println("POSTAGEM ADICIONADA COM SUCESSO!");
    }
    else {
      System.out.println("** SENHA INVÁLIDA. Postagem não adicionada.\n");
    }
  }

  public void seguir(Usuario u){
    this.seguindo.add(u);
    u.seguidores.add(this);
  }

  public void mostrarPosts(){
    for(Postagem post : this.posts){
      System.out.println("—");
      System.out.println(this);
      post.mostrarDados();
    }
  }

  public void feed(){
    for(Usuario seguidor : this.seguindo){
      seguidor.mostrarPosts();
    }
  }

  public String toString(){
    return String.format("%s (%s)", nome, login);
    }
}