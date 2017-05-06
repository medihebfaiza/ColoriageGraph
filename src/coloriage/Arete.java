package coloriage ;

import coloriage.Sommet ;

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
	* @return
	*/
	public int getNumero() {
		return numero;
	}

	/**
	* Sets new value of numero
	* @param
	*/
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	* Returns value of preference
	* @return
	*/
	public Boolean getPreference() {
		return preference;
	}

	/**
	* Sets new value of preference
	* @param
	*/
	public void setPreference(Boolean preference) {
		this.preference = preference;
	}

	/**
	* Returns value of sommetA
	* @return
	*/
	public Sommet getSommetA() {
		return sommetA;
	}

	/**
	* Sets new value of sommetA
	* @param
	*/
	public void setSommetA(Sommet sommetA) {
		this.sommetA = sommetA;
	}

	/**
	* Returns value of sommetB
	* @return
	*/
	public Sommet getSommetB() {
		return sommetB;
	}

	/**
	* Sets new value of sommetB
	* @param
	*/
	public void setSommetB(Sommet sommetB) {
		this.sommetB = sommetB;
	}


	/**
	* Create string representation of Arete for printing
	* @return
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
