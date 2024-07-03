public class Postagem {

  private String foto, legenda;
  private Data d;

  /***************************************************/

  public Postagem(String f, String l, Data data){
    this.foto =  f;
    this.legenda = l;
    this.d = data;
    System.out.println("\n\n**      POSTAGEM CRIADA.     **");
  }

  /***************************************************/

  public void mostrarDados(){
    System.out.println(this.foto);
    System.out.println(this.legenda);
    System.out.println(this.d);
  }
}
