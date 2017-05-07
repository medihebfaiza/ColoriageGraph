package coloriage;

import coloriage.* ;
import java.util.* ;

/**
* The class that represents the whole Graph
* @author FAIZA Mohamed Iheb & DALGER Chloé
*/
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
	* @return the Edges ArrayList
	*/
	public ArrayList<Arete> getAretes() {
		return aretes;
	}

	/**
	* Sets new value of aretes
	* @param aretes the new ArrayList of Edges
	*/
	public void setAretes(ArrayList<Arete> aretes) {
		this.aretes = aretes;
	}

	/**
	* Returns value of sommets
	* @return the Nodes ArrayList
	*/
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	/**
	* Sets new value of sommets
	* @param sommets the new Nodes ArrayList
	*/
	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	/**
	* Create string representation of Graph for printing
	* @return the Graph's string representation
	*/
	@Override
	public String toString() {
		return "Graph {" + aretes + ", sommets=" + sommets + "}";
	}

  /**
  * Removes a given Node from the Nodes ArrayList
  * @param s the Node to be removed
  */
  public Boolean removeSommet(Sommet s){
    if (sommets.indexOf(s) != -1){
      sommets.remove(sommets.indexOf(s)) ;
      return true ;
    }
    return false ;
  }

  /**
  * Returns the index of the Node having the smallest degree
  * @return the index of the Node having the smallest degree
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

  /**
  * Returns the index of the Node having the biggest degree
  * @return the index of the Node having the biggest degree
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

  /**
  * The function that attributes the right color to a given Node
  * @param s the Node to be colored
  * @param k the number of colors to use
  */
  public void colorierSommet(Sommet s, int k){
    boolean[] couleurPrise = new boolean[k] ; // couleurPrise[k] = true => couleur k+1 prise
    boolean[] couleurPref = new boolean[k] ; // couleurPreference[k] = true => priorité couleur k+1 priorité
    // pas besoin de initialiser, valeurs par défault false
    // on regarde que les voisins qui ont couleurs != -1
    for (Arete a : aretes){
      if (!a.getPreference()){
        if (s == a.getSommetA() && a.getSommetB().getColor()!=-1) {
          couleurPrise[a.getSommetB().getColor()-1] = true ;
        }
        else if (s == a.getSommetB() && a.getSommetA().getColor()!=-1){
          couleurPrise[a.getSommetA().getColor()-1] = true ;
        }
      }
      else {
        if (s == a.getSommetA() && a.getSommetB().getColor()!=-1) {
          couleurPref[a.getSommetB().getColor()-1] = true ;
        }
        else if (s == a.getSommetB() && a.getSommetA().getColor()!=-1){
          couleurPref[a.getSommetA().getColor()-1] = true ;
        }
      }
    }
    //si s a une couleur de preference disponible on lui attribue
    int i = 0 ;
    while ((s.getColor() == 0 || s.getColor() == -1) && i<k){
      if (couleurPref[i] == true && couleurPrise[i] == false){
        s.setColor(i+1) ;
      }
      i++ ;
    }
    //sinon on attribue à s la première couleur disponible
    i = 0 ;
    while ((s.getColor() == 0 || s.getColor() == -1) && i<k){ // ici 0 et -1 ne sont pas des couleurs
      if (couleurPrise[i] == false){
        s.setColor(i+1) ;
      }
      i++ ;
    }
  }

  /**
  * The function that colors the Graph using Kempe's graph-coloring algorithm.
  * Instead of Stacks we are using ArrayLists.
  * Step 1 : Stack the Nodes and their concerned Edges in right order and Spill the Nodes with degrees superior or equal the number of colors k.
  * Step 2 : Unstack the Nodes one by one and attribute colors.
  * Step 3 : Try to color the spilled Nodes for an optimistic coloring.
  * The reason why we are using Iterators in this function to read the ArrayList is because we are modifying them while going through them.
  * @param k number of colors > 0
  */
  public void colorier(int k){
    ArrayList<Sommet> listeSommets = new ArrayList<Sommet>() ;
    ArrayList<Arete> listeAretes = new ArrayList<Arete>() ;

    /* Etape 1 */
    while (sommets.size()>0){
        Sommet s = sommets.get(indiceSommetmin());
        if (s.getDegre() >= k){
          s = sommets.get(indiceSommetmax()) ;
          s.setColor(-1) ; // color = -1 --> Node is spilled
        }
        listeSommets.add(s) ;
        removeSommet(s) ;
        /*Empiler tous les aretes contenant le sommet de degre min*/
        Arete a ;
        for (Iterator<Arete> iteratorAretes = aretes.iterator(); iteratorAretes.hasNext(); ) {
          a = iteratorAretes.next() ;
          if ((a.getSommetA() == s) || (a.getSommetB() == s)) {
            a.getSommetA().decrementerDegre() ;
            a.getSommetB().decrementerDegre() ;
            listeAretes.add(a) ;
            iteratorAretes.remove() ;
          }
        }
    }

    /* Etape 2 */
    Sommet s ;
    for (ListIterator<Sommet> iteratorSommets = listeSommets.listIterator(listeSommets.size()) ; iteratorSommets.hasPrevious(); ){
      //Etape 2.1
      s = iteratorSommets.previous() ;
      sommets.add(s);
      iteratorSommets.remove() ;
      //charger les aretes
      //si s (le dernier sommet chargé) a encore une arete qui lui concerne dans la pile des aretes
      Arete a ;
      boolean continuer = (s == listeAretes.get(listeAretes.size()-1).getSommetA()) || (s == listeAretes.get(listeAretes.size()-1).getSommetB()) ;
      for (ListIterator<Arete> iteratorAretes = listeAretes.listIterator(listeAretes.size()); iteratorAretes.hasPrevious() && continuer; ) {
        a = iteratorAretes.previous() ;
        // on regarde si c'est le sommetA ou le sommetB de l'arete
        if (s == a.getSommetA()){
          // si sommetB était dèja dépilé
          if (sommets.contains(a.getSommetB())){
            a.getSommetA().incrementerDegre() ;
            a.getSommetB().incrementerDegre() ;
            aretes.add(a) ;
            iteratorAretes.remove() ;
          }
          else {
            continuer = false ;
          }
        }
        else  {
          // si sommetA était dèja dépilé
          if (sommets.contains(a.getSommetA())){
            a.getSommetA().incrementerDegre() ;
            a.getSommetB().incrementerDegre() ;
            aretes.add(a) ;
            iteratorAretes.remove() ;
          }
          else {
            continuer = false ;
          }
        }
        if (listeAretes.size() != 0){
          continuer = continuer && (s == listeAretes.get(listeAretes.size()-1).getSommetA()) || (s == listeAretes.get(listeAretes.size()-1).getSommetB()) ;
        }
      }
      // Etape 2.2
      if (s.getColor() != -1) {
        colorierSommet(s,k) ;
      }
    }

    /* Etape 3 */
    // un dernier passage pour les sommets spillés pour un coloriage optimiste
    for (Iterator<Sommet> iteratorSommets = sommets.iterator(); iteratorSommets.hasNext(); ) {
      s = iteratorSommets.next() ;
      if (s.getColor() == -1){
        colorierSommet(s,k) ;
      }
    }
  }

}
