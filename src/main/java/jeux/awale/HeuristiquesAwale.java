package jeux.awale;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;
import jeux.dominos.PlateauDominos;


public class HeuristiquesAwale{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pd = (PlateauDominos) p;
			return -pd.nbCoupsNoir();
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pd = (PlateauDominos) p;
			return -pd.nbCoupsBlanc();
		}
	};

}
