package coloriage ;

import coloriage.* ;
import java.util.* ;

public class Test{
  public static void main(String[] args){
    Sommet s1 = new Sommet("1") ;
    Sommet s2 = new Sommet("2") ;
    Sommet s3 = new Sommet("3");
    Arete a = new Arete(1,false,s1,s2) ;
    Arete b = new Arete(2, true, s2, s3);
    ArrayList<Sommet> sommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> aretes = new ArrayList<Arete>() ;
    sommets.add(s1) ;
    sommets.add(s2) ;
    sommets.add(s3) ;
    aretes.add(a) ;
    aretes.add(b) ;
    Graph g = new Graph(aretes,sommets) ;
    System.out.println(g) ;
    g.colorier(3) ;
    System.out.println(g) ;
    //g.removeSommet(s3) ;
    //System.out.println(g) ;
  }
}
