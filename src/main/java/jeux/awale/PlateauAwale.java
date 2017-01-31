package jeux.awale;

import java.io.PrintStream;
import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu{
	
	public final static int TAILLE = 6;
	public final static int NBGRAINESINIT = 4;
	
	private static Joueur joueurBlanc;
	private static Joueur joueurNoir;
	private int campBlanc[];
	private int campNoir[];
	
	
	public PlateauAwale(){
		this.campBlanc = new int[TAILLE];
		this.campNoir = new int[TAILLE];
		for(int i=0; i < TAILLE; i++){
			this.campBlanc[i] = NBGRAINESINIT;
		}
		for(int i=0; i < TAILLE; i++){
			this.campNoir[i] = NBGRAINESINIT;
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
		
		CoupAwale ca = (CoupAwale) c;
		int trou = ca.getTrou();
		int nbGraines;
		int pointeur = trou;
		int[] campCourant;
		
		
		
		if(this.isJoueurBlanc(j)){
			nbGraines = this.campBlanc[trou];
			this.campBlanc[trou] = 0;
			campCourant = this.campBlanc;
			while(nbGraines > 0){
				
				if((pointeur+1)>=6){
					if(campCourant == this.campBlanc){
						campCourant = this.campNoir;
					}else{
						campCourant = this.campBlanc;
					}
					pointeur = -1;
				}
					
				pointeur++;
				if(pointeur == trou && campCourant == campBlanc){
					continue;
				}
				nbGraines--;
				campCourant[pointeur]++;				
			}
			
			while((campCourant[pointeur] == 2 || campCourant[pointeur] == 3) && campCourant == this.campNoir){
				j.addGraines(campCourant[pointeur]);
				campCourant[pointeur] = 0;
				
				if((pointeur-1)<=0){
					if(campCourant == this.campBlanc){
						campCourant = this.campNoir;
					}else{
						campCourant = this.campBlanc;
					}
					pointeur = 6;
				}
				pointeur--;
			}
			
			
		}else{
			nbGraines = this.campNoir[trou];
			this.campNoir[trou] = 0;
			campCourant = this.campNoir;
			while(nbGraines > 0){
				
				if((pointeur+1)>=6){
					if(campCourant == this.campBlanc){
						campCourant = this.campNoir;
					}else{
						campCourant = this.campBlanc;
					}
					pointeur = -1;
				}
					
				pointeur++;
				if(pointeur == trou && campCourant == campNoir){
					continue;
				}
				nbGraines--;
				campCourant[pointeur]++;				
			}
			
			while((campCourant[pointeur] == 2 || campCourant[pointeur] == 3) && campCourant == this.campBlanc){
				j.addGraines(campCourant[pointeur]);
				campCourant[pointeur] = 0;
				
				if((pointeur-1)<=0){
					if(campCourant == this.campBlanc){
						campCourant = this.campNoir;
					}else{
						campCourant = this.campBlanc;
					}
					pointeur = 6;
				}
				pointeur--;
			}

		}
		
	}
	
	public String toString() {
		String retstr = new String("");
		
		retstr += "-------------------------------\n";
		retstr += "| ";
		for(int i=0; i<6; i++){
			retstr += String.format("%02d", this.campNoir[i])+" | ";
		}
		retstr += "\n| ";
		for(int i=0; i<6; i++){
			retstr += String.format("%02d", this.campBlanc[i])+" | ";
		}
		retstr += "\n-------------------------------";
		
		return retstr;
	}

	public void printPlateau(PrintStream out) {
		out.println(this.toString());		
	}

	@Override
	public boolean finDePartie() {
		if(this.joueurBlanc.getGraines() > 25 || this.joueurNoir.getGraines() > 25 || PlateauBlancVide() || PlateauNoirVide() ){
			return true;
		}
		return false;
	}
	
	private boolean PlateauBlancVide(){
		int res = 0;
		for(int c: this.campBlanc){
			res += c;
		}
		return res==0;
	}
	
	private boolean PlateauNoirVide(){
		int res = 0;
		for(int c: this.campNoir){
			res += c;
		}
		return res==0;
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
