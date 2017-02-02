package jeux.awale;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;


public class HeuristiquesAwale{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pa = (PlateauAwale) p;
			//return -pa.getGrainesPlateauNoir();
			return 2*pa.getGrainesBlanc()-pa.getGrainesNoir();
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pa = (PlateauAwale) p;
			//return -pa.getGrainesPlateauBlanc();
			return 2*pa.getGrainesNoir()-pa.getGrainesBlanc();
		}
	};

}
