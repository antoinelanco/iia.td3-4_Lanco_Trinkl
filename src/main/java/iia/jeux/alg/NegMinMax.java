package iia.jeux.alg;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class NegMinMax implements AlgoJeu {

    private final static int PROFMAXDEFAUT = 2;
    private int profMax = PROFMAXDEFAUT;
    private Heuristique h;
    private Joueur joueurMin;
    private Joueur joueurMax;
    private int nbnoeuds;
    private int nbfeuilles;
    private int[] tabTaMere;


    public NegMinMax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public NegMinMax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
    }

    public CoupJeu meilleurCoup(PlateauJeu p) {

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;


        CoupJeu meilleurCoup = p.coupsPossibles(joueurMax).get(0);

        for(CoupJeu c : p.coupsPossibles(joueurMax)) {
            PlateauJeu tmp = p.copy();
            tmp.joue(joueurMax, c);

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
    //        alpha = h.eval(p, this.joueurMax);
    //    }
    //    else {
    //        for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
    //            PlateauJeu tmp = p.copy();
    //            tmp.joue(this.joueurMax, c);
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
            return h.eval(p, this.joueurMax);
        }
        else {
            int max = Integer.MIN_VALUE;
            for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
                PlateauJeu tmp = p.copy();
                tmp.joue(this.joueurMax, c);
                max = Math.max(max, -negMax(tmp, prof - 1));
            }
            //System.out.println(min)
            return max;
        }
    }


}