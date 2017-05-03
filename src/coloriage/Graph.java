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
  *@return l'indice du sommet de degre minimim
  *@param
  */
  public int indiceSommetmin(){
    int indiceMin = 0 ;
    int i ;
    for (i=1 ; i<sommets.size() ; i++){
      if (sommets.get(i).getDegre()<sommets.get(indiceMin).getDegre()){
        indiceMin = i ;
      }
    }
    return indiceMin ;
  }

  /*
  *
  */
  public int indiceSommetmax(){
    int indiceMax = 0 ;
    int i ;
    for (i=1 ; i<sommets.size() ; i++){
      if (sommets.get(i).getDegre()>sommets.get(indiceMax).getDegre()){
        indiceMax = i ;
      }
    }
    return indiceMax ;
  }

  /*
  *
  */
  public void colorierSommet(Sommet s, int k){
    boolean[] couleurPrise = new boolean[k] ; // couleurPrise[k] = true => couleur k+1 prise
    // pas besoin de initialiser, valuers par défault false
    // on regarde que les voisins qui ont couleurs != -1
    for (Arete a : aretes){
      if (s == a.getSommetA()) {
        couleurPrise[a.getSommetB().getColor()-1] = true ;
      }
      else if (s == a.getSommetB()){
        couleurPrise[a.getSommetA().getColor()-1] = true ;
      }
    }
    //on attribue à s la première couleur disponnible
    int i = 0 ;
    while (s.getColor() == 0){
      if (couleurPrise[i] == false){
        s.setColor(i+1) ;
      }
    }
  }

  /**
  * Colorier le graphe
  *@param k le nombre de couleurs > 0
  */
  public void colorier(int k){
    ArrayList<Sommet> pileSommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> pileAretes = new ArrayList<Arete>() ;

    /* Etape 1 */
    while (sommets.size()>0){
        int indiceSommetMin = indiceSommetmin();
        if (sommets.get(indiceSommetMin).getDegre() >= k){
          int indiceSommetMax = indiceSommetmax();
          sommets.get(indiceSommetMax).setColor(-1) ;
          indiceSommetMin = indiceSommetMax;
        }
        pileSommets.add(sommets.get(indiceSommetMin)) ;
        removeSommet(sommets.get(indiceSommetMin)) ;
        /*Empiler tous les aretes contenant le sommet de degre min*/
        int i ;
        for (i=0 ; i < aretes.size() ; i++){
          if ((aretes.get(i).getSommetA() == sommets.get(indiceSommetMin)) || (aretes.get(i).getSommetB() == sommets.get(indiceSommetMin))) {
            pileAretes.add(aretes.get(i)) ;
            removeArete(aretes.get(i)) ;
            i-- ;
          }
        }
    }

    /* Etape 2 */
    //
    Sommet s = pileSommets.get(pileSommets.size()-1) ;
    pileSommets.remove(pileSommets.size()-1) ; //dépiler
    s.setColor(k) ;
    sommets.add(s) ;
    while (pileSommets.size()>0){
      //Etape 2.1
      s = pileSommets.get(pileSommets.size()-1) ;
      pileSommets.remove(pileSommets.size()-1) ; //dépiler
      sommets.add(s);
      //charger les aretes
      //si s (le dernier sommet chargé) a encore une arte qui lui concerne dans la pile des aretes
      Arete a ;
      boolean quitter = false ;
      while ((s == pileAretes.get(pileAretes.size()-1).getSommetA() || s == pileAretes.get(pileAretes.size()-1).getSommetB()) && !quitter) {
        // on regarde si c'est le sommetA ou le sommetB de l'arete
        if (s == pileAretes.get(pileAretes.size()-1).getSommetA()){
          // si sommetB était dèja dépilé
          if (sommets.contains(pileAretes.get(pileAretes.size()).getSommetB())){
            a = pileAretes.get(pileAretes.size()-1) ;
            pileAretes.remove(pileAretes.size()-1) ;
            aretes.add(a) ;
          }
          else {
            quitter = true ;
          }
        }
        else  {
          // si sommetA était dèja dépilé
          if (sommets.contains(pileAretes.get(pileAretes.size()).getSommetA())){
            a = pileAretes.get(pileAretes.size()-1) ;
            pileAretes.remove(pileAretes.size()-1) ;
            aretes.add(a) ;
          }
          else {
            quitter = true ;
          }
        }


      }
      // Etape 2.2
      if (s.getColor() != -1) {
        colorierSommet(s,k) ;
      }


    }

    /* Etape 3 */
    // faire les sommets spilled

  }

}
