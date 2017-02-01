package iia.jeux.alg;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class NegMinMax implements AlgoJeu {

    private final static int PROFMAXDEFAUT = 2;
    private int profMax = PROFMAXDEFAUT;
    private Heuristique h;
    private Joueur joueurMax;
    private int nbnoeuds;
    private int nbfeuilles;


    public NegMinMax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public NegMinMax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
    }

    public CoupJeu meilleurCoup(PlateauJeu p) {

        int alpha = Integer.MIN_VALUE;


        CoupJeu meilleurCoup = p.coupsPossibles(this.joueurMax).get(0);

        for(CoupJeu c : p.coupsPossibles(this.joueurMax)) {
            PlateauJeu tmp = p.copy();
            tmp.joue(this.joueurMax, c);
            this.nbnoeuds++;
            int minMaxValue = negMax(tmp, profMax - 1);

            if(alpha < minMaxValue){
                meilleurCoup = c;
                alpha = minMaxValue;
            }
        }

        return meilleurCoup;
    }

    public String toString() {
        return "NegMinMax(ProfMax="+profMax+")";
    }


    //private int negAlphaBeta(PlateauJeu p,int prof, int alpha, int beta){
    //    if (p.finDePartie() || prof == 0){
    //        this.nbfeuilles++;
    //        alpha = h.eval(p, this.joueurMax);
    //    }
    //    else {
    //        for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
    //			  this.nbnoeuds++;
    //            PlateauJeu tmp = p.copy();
    //            Joueur Jtmp = joueurMax.copy();
    //            tmp.joue(Jtmp, c);
    //            alpha = Math.max(alpha, -negAlphaBeta(tmp, prof - 1, -beta, -alpha));
    //            if (alpha >= beta){
    //                return beta;
    //            }
    //        }
    //        //System.out.println(min)
    //    }
    //    return alpha;
    //}



    private int negMax(PlateauJeu p,int prof){
        if (p.finDePartie() || prof == 0){
        	this.nbfeuilles++;
            return h.eval(p, this.joueurMax);
        }
        else {
            int max = Integer.MIN_VALUE;
            for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
            	this.nbnoeuds++;
                PlateauJeu tmp = p.copy();
                tmp.joue(this.joueurMax, c);
                max = Math.max(max, -negMax(tmp, prof - 1));
            }
            //System.out.println(min)
            return max;
        }
    }


}