package coloriage ;

import coloriage.Sommet ;

/**
* The class that represents an Edge
* @author FAIZA Mohamed Iheb & DALGER Chloé
*/
public class Arete {
  private int numero ;
  private Boolean preference ;
  private Sommet sommetA ;
  private Sommet sommetB ;

	/**
	* Default empty Arete constructor
	*/
	public Arete() {

	}

	/**
	* Default Arete constructor
	*/
	public Arete(int numero, Boolean preference, Sommet sommetA, Sommet sommetB) {
		this.numero = numero;
		this.preference = preference;
		this.sommetA = sommetA;
		this.sommetB = sommetB;
    if (!preference){
      this.sommetA.incrementerDegre() ;
      this.sommetB.incrementerDegre() ;
    }
	}

	/**
	* Returns value of numero
	* @return the Edge's number (ID)
	*/
	public int getNumero() {
		return numero;
	}

	/**
	* Sets new value of numero
	* @param numero the Edge's new number
	*/
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	* Returns value of preference
	* @return returns true if it's a preference Edge
	*/
	public Boolean getPreference() {
		return preference;
	}

	/**
	* Sets new value of preference
	* @param preference true to change the Edge to a preference Edge
	*/
	public void setPreference(Boolean preference) {
		this.preference = preference;
	}

	/**
	* Returns value of sommetA
	* @return the Edge's first Node value
	*/
	public Sommet getSommetA() {
		return sommetA;
	}

	/**
	* Sets new value of sommetA
	* @param sommetA the Edge's new first Node value
	*/
	public void setSommetA(Sommet sommetA) {
		this.sommetA = sommetA;
	}

	/**
	* Returns value of sommetB
	* @return the Edge's second Node value
	*/
	public Sommet getSommetB() {
		return sommetB;
	}

	/**
	* Sets new value of sommetB
	* @param sommetB the Edge's new second Node value
	*/
	public void setSommetB(Sommet sommetB) {
		this.sommetB = sommetB;
	}


	/**
	* Create string representation of Arete for printing
	* @return the Edge's string representation
	*/
	@Override
	public String toString() {
    if (preference) {
      return "(" + numero + ",pref" + "," + sommetA + "," + sommetB + ")";
    }
    else {
      return "(" + numero + ",interf" + "," + sommetA + "," + sommetB + ")";
    }

	}
}
