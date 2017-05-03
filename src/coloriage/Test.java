package coloriage ;

import coloriage.* ;
import java.util.* ;

public class Test{
  public static void main(String[] args){
    Sommet s1 = new Sommet("1") ;
    Sommet s2 = new Sommet("2") ;
    Arete a = new Arete(1,false,s1,s2) ;
    ArrayList<Sommet> sommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> aretes = new ArrayList<Arete>() ;
    sommets.add(s1) ;
    sommets.add(s2) ;
    aretes.add(a) ;
    Graph g = new Graph(aretes,sommets) ;
    System.out.println(g) ;
    g.removeSommet(s1) ;
    System.out.println(g) ;
  }
}
