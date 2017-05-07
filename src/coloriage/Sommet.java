package coloriage ;

import coloriage.Color ;

/**
* The class that represents a Node
* @author FAIZA Mohamed Iheb & DALGER Chloé
*/
public class Sommet{
  private String nom ;
  private int color ; // 0 : blanc (not colored) , -1 : noir (spilled)
  private int degre ;


	/**
	* Default empty Sommet constructor
	*/
	public Sommet() {
    this.degre = 0 ;
	}

	/**
	* Default Sommet constructor
  * @param nom the Node's name
	*/
	public Sommet(String nom) {
		this.nom = nom ;
		this.color = 0 ;
		this.degre = 0 ;
	}


	/**
	* Returns value of nom
	* @return the Node's name
	*/
	public String getNom() {
		return nom;
	}

	/**
	* Sets new value of nom
	* @param nom the Node's new name
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	* Returns value of color
	* @return the Node's color
	*/
	public int getColor() {
		return color;
	}

	/**
	* Sets new value of color
	* @param color the Node's new color
	*/
	public void setColor(int color) {
		this.color = color;
	}

	/**
	* Returns value of degre
	* @return the Node's degree (number of neighbors)
	*/
	public int getDegre() {
		return degre;
	}

	/**
	* Sets new value of degre
	* @param degre the Node's new degree
	*/
	public void setDegre(int degre) {
		this.degre = degre;
	}


	/**
	* Create string representation of Sommet for printing
	* @return string representation of the Node
	*/
	@Override
	public String toString() {
    switch(color){
      case -1 : return Color.whitebg + Color.black + nom + Color.reset + "°" + degre ;
      case 0 : return Color.white + nom + Color.reset + "°" + degre ;
      case 1 : return Color.red + nom + Color.reset + "°" + degre ;
      case 2 : return Color.green + nom + Color.reset + "°" + degre ;
      case 3 : return Color.yellow + nom + Color.reset + "°" + degre ;
      case 4 : return Color.blue + nom + Color.reset + "°" + degre ;
      case 5 : return Color.purple + nom + Color.reset + "°" + degre ;
      case 6 : return Color.cyan + nom + Color.reset + "°" + degre ;
      default : return nom + "(color=" + color + ")°" + degre ;
    }
	}

  /**
  * increase the Node's degree by 1
  */
  public void incrementerDegre(){
    this.degre++ ;
  }

  /**
  * reduce the Node's degree by 1
  */
  public void decrementerDegre(){
    this.degre-- ;
  }
}
