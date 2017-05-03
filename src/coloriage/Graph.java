package coloriage;

import coloriage.* ;
import java.util.* ;

public class Graph{
  private ArrayList<Arete> aretes ;
  private ArrayList<Sommet> sommets ;

	/**
	* Default empty Graph constructor
	*/
	public Graph() {

	}

	/**
	* Default Graph constructor
	*/
	public Graph(ArrayList<Arete> aretes, ArrayList<Sommet> sommets) {
		this.aretes = aretes;
		this.sommets = sommets;
	}

	/**
	* Returns value of aretes
	* @return
	*/
	public ArrayList<Arete> getAretes() {
		return aretes;
	}

	/**
	* Sets new value of aretes
	* @param
	*/
	public void setAretes(ArrayList<Arete> aretes) {
		this.aretes = aretes;
	}

	/**
	* Returns value of sommets
	* @return
	*/
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	/**
	* Sets new value of sommets
	* @param
	*/
	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	/**
	* Create string representation of Graph for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Graph [aretes=" + aretes + ", sommets=" + sommets + "]";
	}

  /**
  *
  */
  public Boolean addArete(Arete a){
    return this.aretes.add(a) ;
  }

  /**
  *
  */
  public Boolean removeArete(Arete a){
      if (aretes.indexOf(a) != -1) {
        if (!aretes.get(aretes.indexOf(a)).getPreference()) {
          aretes.get(aretes.indexOf(a)).getSommetA().decrementerDegre() ;
          aretes.get(aretes.indexOf(a)).getSommetB().decrementerDegre() ;
        }
        aretes.remove(aretes.indexOf(a)) ;
        return true ;
      }
      return false ;
  }

  /**
  *
  */
  public Boolean addSommet(Sommet s){
    return this.sommets.add(s) ;
  }

  /**
  *
  */
  public Boolean removeSommet(Sommet s){
    if (sommets.indexOf(s) != -1){
      int i ;
      /* Enlever les aretes contenant le sommet s */

      for (i=0 ; i<aretes.size() ; i++){
        if (aretes.get(i).getSommetA() == s) {
          removeArete(aretes.get(i)) ;
        }
        else if (aretes.get(i).getSommetB() == s){
          removeArete(aretes.get(i)) ;
        }
      }
      sommets.remove(sommets.indexOf(s)) ;
      return true ;
    }
    return false ;
  }


  /**
  * Colorier le graphe
  *@param k le nombre de couleurs
  */
  public void colorier(int k){
    ArrayList<Sommet> pileSommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> pileAretes = new ArrayList<Arete>() ;
    ArrayList<Sommet> spilledSommets = new ArrayList<Sommet>() ;

  }

}
