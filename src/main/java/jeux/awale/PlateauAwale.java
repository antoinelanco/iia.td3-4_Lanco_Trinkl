package jeux.awale;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu{
	
	public final static int TAILLE = 6;
	public final static int NBGRAINES = 4;
	
	private static Joueur joueurBlanc;
	private static Joueur joueurNoir;
	private int campBlanc[];
	private int campNoir[];
	
	
	public PlateauAwale(){
		this.campBlanc = new int[TAILLE];
		this.campNoir = new int[TAILLE];
		for(int i=0; i < TAILLE; i++){
			this.campBlanc[i] = NBGRAINES;
		}
		for(int i=0; i < TAILLE; i++){
			this.campNoir[i] = NBGRAINES;
		}
		
	}
	
	public PlateauAwale(int[] campBlanc, int[] campNoir){
		this.campBlanc = new int[TAILLE];
		this.campNoir = new int[TAILLE];
		for(int i=0; i < TAILLE; i++){
			this.campBlanc[i] = campBlanc[i];
		}
		for(int i=0; i < TAILLE; i++){
			this.campNoir[i] = campNoir[i];
		}
		
	}
	
	public static void setJoueurs(Joueur jb, Joueur jn) {
		joueurBlanc = jb;
		joueurNoir = jn;
	}

	public boolean isJoueurBlanc(Joueur jb) {
		return joueurBlanc.equals(jb);
	}

	public boolean isJoueurNoir(Joueur jn) {
		return joueurNoir.equals(jn);
	}


	@Override
	public ArrayList<CoupJeu> coupsPossibles(Joueur j) {
		ArrayList<CoupJeu> lesCoupsPossibles = new ArrayList<CoupJeu>();
		if(isJoueurBlanc(j)){
			for(int i=0 ; i < TAILLE ; i++) { 
				if(this.campBlanc[i] != 0){
					lesCoupsPossibles.add(new CoupAwale(i));
				}
			}	
		}else{
			for(int i=0; i< TAILLE; i++){
				if(this.campNoir[i] != 0){
					lesCoupsPossibles.add(new CoupAwale(i));
				}
			}
		}
		return lesCoupsPossibles;
	}

	@Override
	public void joue(Joueur j, CoupJeu c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean finDePartie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlateauJeu copy() {
		return new PlateauAwale(this.campBlanc,this.campNoir);
	}

	@Override
	public boolean coupValide(Joueur j, CoupJeu c) {
		// TODO Auto-generated method stub
		return false;
	}

}
