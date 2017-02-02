package iia.jeux.alg;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class AlphaBeta implements AlgoJeu {

    private final static int PROFMAXDEFAUT = 2;
    private int profMax = PROFMAXDEFAUT;
    private Heuristique h;
    private Joueur joueurMin;
    private Joueur joueurMax;
    private int nbnoeuds;
    private int nbfeuilles;


    public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

	public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        this.profMax = profMaxi;
	}

    public CoupJeu meilleurCoup(PlateauJeu p) {

    	this.nbfeuilles = 0;
    	this.nbnoeuds = 0;

        CoupJeu meilleurCoup = p.coupsPossibles(joueurMax).get(0);

    	int alpha = Integer.MIN_VALUE;
    	int beta = Integer.MAX_VALUE;
    	int MH = Integer.MIN_VALUE;

		for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
			this.nbnoeuds ++;
			PlateauJeu tmp = p.copy();
			tmp.joue(this.joueurMax, c);
			int Max = minMax(tmp, profMax - 1,alpha,beta);
			//System.out.println("mh : "+Max);
			if (alpha < Max){
				alpha = Max;
				MH = alpha;
				meilleurCoup =c;
				
			}
		}


        System.out.println("N°Noeuds :"+this.nbnoeuds);
        System.out.println("N°Feuilles :"+this.nbfeuilles);
        //System.out.println("mc :"+MH);
        return meilleurCoup;
     }

    public String toString() {
        return "AlphaBeta(ProfMax="+profMax+")";
    }

    private int maxMin(PlateauJeu p, int prof, int alpha, int beta){
    	if (p.finDePartie() || prof == 0){
    		this.nbfeuilles ++;
    		//System.out.println("eval h "+h.eval(p, this.joueurMax));
    		return h.eval(p, this.joueurMax);
    	}
    	else {
    		for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
    			this.nbnoeuds ++;
    			PlateauJeu tmp = p.copy();
    			tmp.joue(this.joueurMax, c);
    			alpha = Math.max(alpha, minMax(tmp, prof - 1,alpha,beta));
    			if (alpha >= beta){
    				return beta;
    			}
    		}
    	//System.out.println(alpha);
    	return alpha;
    	}
    }

    private int minMax(PlateauJeu p,int prof, int alpha, int beta){
    	if (p.finDePartie() || prof == 0){
    		this.nbfeuilles ++;
    		//System.out.println("eval h "+h.eval(p, this.joueurMin));
    		return h.eval(p,this.joueurMin);
    	}
    	else {
    		for (CoupJeu c : p.coupsPossibles(this.joueurMin)){
    			this.nbnoeuds ++;
    			PlateauJeu tmp = p.copy();
    			tmp.joue(this.joueurMin, c);
    			beta = Math.min(beta, maxMin(tmp, prof - 1,alpha,beta));
    			if (alpha >= beta){
    				return alpha;
    			}
    		}
    	//System.out.println(min);
    	return beta;
    	}
    }



}
