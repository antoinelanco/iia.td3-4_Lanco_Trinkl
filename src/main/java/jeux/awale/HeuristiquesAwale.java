package jeux.awale;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;
import jeux.dominos.PlateauDominos;


public class HeuristiquesAwale{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pd = (PlateauAwale) p;
			return j.getGraines();
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pd = (PlateauAwale) p;
			return j.getGraines();
		}
	};

}
