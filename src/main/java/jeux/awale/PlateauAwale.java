package jeux.awale;

import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauGraph;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu{

	public final static int TAILLE = 6;
	public final static int NBGRAINESINIT = 4;

	private static Joueur joueurBlanc;
	private static Joueur joueurNoir;
	private int campBlanc[];
	private int campNoir[];
	private int nbGrainesBlanc;
	private int nbGrainesNoir;


	public PlateauAwale(){
		this.campBlanc = new int[TAILLE];
		this.campNoir = new int[TAILLE];
		this.nbGrainesBlanc = 0;
		this.nbGrainesNoir = 0;
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
		this.nbGrainesBlanc = 0;
		this.nbGrainesNoir = 0;
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
			@SuppressWarnings("unchecked")
			ArrayList<CoupJeu> tmpArray = (ArrayList<CoupJeu>) lesCoupsPossibles.clone();
			for(CoupJeu c : tmpArray){
				PlateauAwale tmp = (PlateauAwale) this.copy();
				tmp.joue(j, c);
				if(getGrainesPlateauNoir()==0){
					lesCoupsPossibles.remove(c);
				}
				
			}
		}else{
			for(int i=0; i< TAILLE; i++){
				if(this.campNoir[i] != 0){
					lesCoupsPossibles.add(new CoupAwale(i));
				}
			}
			@SuppressWarnings("unchecked")
			ArrayList<CoupJeu> tmpArray = (ArrayList<CoupJeu>) lesCoupsPossibles.clone();
			for(CoupJeu c : tmpArray){
				PlateauAwale tmp = (PlateauAwale) this.copy();
				tmp.joue(j, c);
				if(getGrainesPlateauBlanc()==0){
					lesCoupsPossibles.remove(c);
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
				this.nbGrainesBlanc+=campCourant[pointeur];
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
				this.nbGrainesNoir+=campCourant[pointeur];
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

		for(int i=5; i>=0; i--){
			retstr += String.format("%02d", this.campBlanc[i])+" | ";
		}
		retstr += "\n| ";
		for(int i=0; i<6; i++){
			retstr += String.format("%02d", this.campNoir[i])+" | ";
		}
		retstr += "\n-------------------------------";

		return retstr;
	}

	public void printPlateau(PrintStream out) {
		out.println(this.toString());
	}
	
	@SuppressWarnings("unused")
	public void toGraph(){
		JFrame frame = new PlateauGraph("Awale");
	}

	@Override
	public boolean finDePartie() {
		if(this.nbGrainesBlanc > 25 || this.nbGrainesNoir > 25 || getGrainesPlateauBlanc()==0 || getGrainesPlateauNoir()==0 ){
			return true;
		}
		return false;
	}

	public int getGrainesPlateauBlanc(){
		int res = 0;
		for(int c: this.campBlanc){
			res += c;
		}
		return res;
	}

	public int getGrainesPlateauNoir(){
		int res = 0;
		for(int c: this.campNoir){
			res += c;
		}
		return res;
	}
	
	public int getGrainesBlanc(){
		return this.nbGrainesBlanc;
	}
	
	public int getGrainesNoir(){
		return this.nbGrainesNoir;
	}

	@Override
	public PlateauJeu copy() {
		return new PlateauAwale(this.campBlanc,this.campNoir);
	}

	@Override
	public boolean coupValide(Joueur j, CoupJeu c) {
		if(isJoueurBlanc(j)){
			return this.campBlanc[((CoupAwale) c).getTrou()] != 0;
		}else{
			return this.campNoir[((CoupAwale) c).getTrou()] != 0;
		}
	}

}
