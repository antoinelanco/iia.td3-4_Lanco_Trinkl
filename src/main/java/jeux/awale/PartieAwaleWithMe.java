package jeux.awale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import iia.jeux.alg.AlgoJeu;
import iia.jeux.alg.AlphaBeta;
import iia.jeux.alg.NegMinMax;
import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PartieAwaleWithMe {

    public static void main(String[] args) throws IOException {

        Joueur jBlanc = new Joueur("Blanc");
        Joueur jNoir = new Joueur("Noir");

        Joueur[] lesJoueurs = new Joueur[2];

        lesJoueurs[0] = jBlanc;
        lesJoueurs[1] = jNoir;



        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        //AlgoJoueur[0] = new Minimax(HeuristiquesDominos.hblanc, jBlanc, jNoir,4); // Il faut remplir la méthode !!!
        //AlgoJoueur[1] = new Minimax(HeuristiquesDominos.hnoir, jNoir, jBlanc,4);  // Il faut remplir la méthode !!!

        AlgoJoueur[0] = new AlphaBeta(HeuristiquesAwale.hblanc, jBlanc, jNoir,10);
       // AlgoJoueur[1] = new AlphaBeta(HeuristiquesDominos.hnoir, jNoir, jBlanc,6);

        //AlgoJoueur[0] = new NegMinMax(HeuristiquesDominos.hblanc, jBlanc, jNoir,6);
        AlgoJoueur[1] = new NegMinMax(HeuristiquesAwale.hnoir, jNoir, jBlanc,6);

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
		Scanner input = new Scanner( System.in );
        while (!jeufini) {
        	if(((PlateauAwale) plateauCourant).affame(lesJoueurs[jnum])){
        		System.out.println(((PlateauAwale) plateauCourant).QuiAGG());
        		jeufini = true;
        		continue;
        	}
        	System.out.println("NB Graines Blanc : "+((PlateauAwale) plateauCourant).getGrainesBlanc());
        	System.out.println("NB Graines Noir : "+((PlateauAwale) plateauCourant).getGrainesNoir());
            System.out.println("" + plateauCourant);
            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
            // Vérifie qu'il y a bien des coups possibles
            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
            ArrayList<CoupJeu> lesCoupsPossibles = plateauCourant.coupsPossibles(lesJoueurs[jnum]);
            System.out.println("Coups possibles pour " + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
            if (!plateauCourant.finDePartie()) {
                // On écrit le plateau

            	if(jnum == 0){
                    // Lancement de l'algo de recherche du meilleur coup
                    System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
                    meilleurCoup = AlgoJoueur[jnum].meilleurCoup(plateauCourant);

            	}else{
	   	             

		             System.out.print("i :");
		             int i = input.nextInt();
		             //input.close();
		             	
		             meilleurCoup = new CoupAwale(i);
		             if(!plateauCourant.coupValide(lesJoueurs[jnum],meilleurCoup)){
		            	 System.out.println("Coup invalide");
		            	 continue;
		             }
            	}

	            System.out.println("Coup joué : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum]);

	            plateauCourant.joue(lesJoueurs[jnum], meilleurCoup);
	            // Le coup est effectivement joué
	            jnum = 1 - jnum;

            } else {
            	System.out.println(((PlateauAwale) plateauCourant).QuiAGG());
                jeufini = true;

            }
        }
        input.close();
    }
}
