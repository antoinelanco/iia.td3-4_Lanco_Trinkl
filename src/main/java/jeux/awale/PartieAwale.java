package jeux.awale;

import iia.jeux.alg.AlgoJeu;
import iia.jeux.alg.AlphaBeta;
import iia.jeux.alg.Minimax;
import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;
import java.util.ArrayList;

public class PartieAwale {

    public static void main(String[] args) {

        Joueur jBlanc = new Joueur("Blanc");
        Joueur jNoir = new Joueur("Noir");

        Joueur[] lesJoueurs = new Joueur[2];

        lesJoueurs[0] = jBlanc;
        lesJoueurs[1] = jNoir;



        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        //AlgoJoueur[0] = new Minimax(HeuristiquesAwale.hblanc, jBlanc, jNoir,4); // Il faut remplir la méthode !!!
        //AlgoJoueur[1] = new Minimax(HeuristiquesAwale.hnoir, jNoir, jBlanc,4);  // Il faut remplir la méthode !!!
        
        AlgoJoueur[0] = new AlphaBeta(HeuristiquesAwale.hblanc, jBlanc, jNoir,10);
        AlgoJoueur[1] = new AlphaBeta(HeuristiquesAwale.hnoir, jNoir, jBlanc,10);	
        
        System.out.println("TD IIA n.3 - Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");

        boolean jeufini = false;
        CoupJeu meilleurCoup = null;
        int jnum;

        PlateauJeu plateauCourant = new PlateauAwale();
        PlateauAwale.setJoueurs(jBlanc, jNoir);
        // Pour savoir qui joue "noir" et qui joue "blanc"


        // A chaque itération de la boucle, on fait jouer un des deux joueurs
        // tour a tour
        jnum = 0; // On commence par le joueur Blanc (arbitraire)

        while (!jeufini) {
        	if(((PlateauAwale) plateauCourant).affame(lesJoueurs[jnum])){
        		System.out.println("Le joueur " + ((PlateauAwale) plateauCourant).QuiAGG() + " a gagné cette partie !");
        		jeufini = true;
        		continue;
        	}
        	System.out.println("NB Graines Blanc : "+((PlateauAwale) plateauCourant).getGrainesBlanc());
        	System.out.println("NB Graines Noir : "+((PlateauAwale) plateauCourant).getGrainesNoir());
            System.out.println("" + plateauCourant);
        	//((PlateauAwale) plateauCourant).toGraph();
            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
            // Vérifie qu'il y a bien des coups possibles
            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
            ArrayList<CoupJeu> lesCoupsPossibles = plateauCourant.coupsPossibles(lesJoueurs[jnum]);
            System.out.println("Coups possibles pour " + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
            if (!plateauCourant.finDePartie()) {
                // On écrit le plateau

                // Lancement de l'algo de recherche du meilleur coup
                System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
                meilleurCoup = AlgoJoueur[jnum].meilleurCoup(plateauCourant);
                System.out.println("Coup joué : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum]);

                plateauCourant.joue(lesJoueurs[jnum], meilleurCoup);
                // Le coup est effectivement joué
                jnum = 1 - jnum;

            } else {
                System.out.println("Le joueur " + ((PlateauAwale) plateauCourant).QuiAGG() + " a gagné cette partie !");
                jeufini = true;

            }
        }
    }
}
