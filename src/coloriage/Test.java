package coloriage ;

import coloriage.* ;
import java.util.* ;

public class Test{
  public static void main(String[] args){
    Sommet s1 = new Sommet("1") ;
    Sommet s2 = new Sommet("2") ;
    Sommet s3 = new Sommet("3") ;
    Sommet s4 = new Sommet("4") ;
    Arete a1 = new Arete(1,false,s1,s2) ;
    Arete a2 = new Arete(2, false, s2, s3);
    Arete a3 = new Arete(3,false,s3,s4) ;
    Arete a4 = new Arete(4, false, s4, s1);
    ArrayList<Sommet> sommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> aretes = new ArrayList<Arete>() ;
    sommets.add(s1) ;
    sommets.add(s2) ;
    sommets.add(s3) ;
    sommets.add(s4) ;
    aretes.add(a1) ;
    aretes.add(a2) ;
    aretes.add(a3) ;
    aretes.add(a4) ;
    Graph g = new Graph(aretes,sommets) ;
    System.out.println("Before :") ;
    System.out.println(g) ;
    g.colorier(2) ;
    System.out.println("After :") ;
    System.out.println(g) ;
    //g.removeSommet(s3) ;
    //System.out.println(g) ;
  }
}
