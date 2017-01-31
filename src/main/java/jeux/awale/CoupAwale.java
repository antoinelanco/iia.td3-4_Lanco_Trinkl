package jeux.awale;

import iia.jeux.modele.CoupJeu;

public class CoupAwale implements CoupJeu{
	
	private int trou;
	
	public CoupAwale(int i){
		this.trou = i;
	}
	
	public int getTrou() {
		return this.trou;
	}

	public String toString() {
		return ""+trou;
	}
}
