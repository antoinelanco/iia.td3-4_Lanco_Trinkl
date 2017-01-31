package jeux.dominos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import iia.jeux.alg.AlgoJeu;
import iia.jeux.alg.AlphaBeta;
import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PartieDominosWithMe {

    public static void main(String[] args) throws IOException {

        Joueur jBlanc = new Joueur("Blanc");
        Joueur jNoir = new Joueur("Noir");

        Joueur[] lesJoueurs = new Joueur[2];

        lesJoueurs[0] = jBlanc;
        lesJoueurs[1] = jNoir;



        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        //AlgoJoueur[0] = new Minimax(HeuristiquesDominos.hblanc, jBlanc, jNoir,4); // Il faut remplir la méthode !!!
        //AlgoJoueur[1] = new Minimax(HeuristiquesDominos.hnoir, jNoir, jBlanc,4);  // Il faut remplir la méthode !!!

        AlgoJoueur[0] = new AlphaBeta(HeuristiquesDominos.hblanc, jBlanc, jNoir,100);
        AlgoJoueur[1] = new AlphaBeta(HeuristiquesDominos.hnoir, jNoir, jBlanc,100);

        System.out.println("TD IIA n.3 - Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");

        boolean jeufini = false;
        CoupJeu meilleurCoup = null;
        int jnum;

        PlateauJeu plateauCourant = new PlateauDominos();
        PlateauDominos.setJoueurs(jBlanc, jNoir);
        // Pour savoir qui joue "noir" et qui joue "blanc"


        // A chaque itération de la boucle, on fait jouer un des deux joueurs
        // tour a tour
        jnum = 0; // On commence par le joueur Blanc (arbitraire)
		Scanner input = new Scanner( System.in );
        while (!jeufini) {
            System.out.println("" + plateauCourant);
            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
            // Vérifie qu'il y a bien des coups possibles
            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
            ArrayList<CoupJeu> lesCoupsPossibles = plateauCourant.coupsPossibles(lesJoueurs[jnum]);
            System.out.println("Coups possibles pour " + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
            if (lesCoupsPossibles.size() > 0) {
                // On écrit le plateau

            	if(jnum == 0){
                    // Lancement de l'algo de recherche du meilleur coup
                    System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
                    meilleurCoup = AlgoJoueur[jnum].meilleurCoup(plateauCourant);

            	}else{
	   	             

		             System.out.print("X:");
		             int x = input.nextInt();
		             System.out.print("Y:");
		             int y = input.nextInt();
		             //input.close();
		             	
		             meilleurCoup = new CoupDominos(x,y);
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
                System.out.println("Le joueur " + lesJoueurs[jnum] + " ne peut plus jouer et abandone !");
                System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gagné cette partie !");
                jeufini = true;

            }
        }
        input.close();
    }
}
