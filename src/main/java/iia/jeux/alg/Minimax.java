/**
 *
 */

package iia.jeux.alg;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class Minimax implements AlgoJeu {

    /** La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 2;


    // -------------------------------------------
    // Attributs
    // -------------------------------------------

    /**  La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

     /**  L'heuristique utilisée par l'algorithme
      */
   private Heuristique h;

    /** Le joueur Min
     *  (l'adversaire) */
    private Joueur joueurMin;

    /** Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue) */
    private Joueur joueurMax;

    /**  Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés) */
       private int nbnoeuds;

    /** Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        this.profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

   // -------------------------------------------
  // Méthodes de l'interface AlgoJeu
  // -------------------------------------------
    public CoupJeu meilleurCoup(PlateauJeu p) {
    	
    	this.nbfeuilles = 0;
    	this.nbnoeuds = 0;
        int max = Integer.MIN_VALUE;
        CoupJeu meilleurCoup = p.coupsPossibles(joueurMax).get(0);
        
        for(CoupJeu c : p.coupsPossibles(joueurMax)) {
            PlateauJeu tmp = p.copy();
            tmp.joue(joueurMax, c);           
            
            if(max < minMax(tmp,profMax-1)){
                max = minMax(tmp,profMax-1);
                meilleurCoup = c;
            }
        }
        System.out.println("N°Noeuds :"+this.nbnoeuds);
        System.out.println("N°Feuilles :"+this.nbfeuilles);
        return meilleurCoup;
     }
  // -------------------------------------------
  // Méthodes publiques
  // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // Méthodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax
    
    
    
    private int maxMin(PlateauJeu p, int prof){
    	if (p.finDePartie() || prof == 0){
    		this.nbfeuilles ++;
    		//System.out.println("eval h "+h.eval(p, this.joueurMax));
    		return h.eval(p, this.joueurMax);
    	}
    	else {
    		int max = Integer.MIN_VALUE;
    		for (CoupJeu c : p.coupsPossibles(this.joueurMax)){
    			this.nbnoeuds ++;
    			PlateauJeu tmp = p.copy();
    			tmp.joue(this.joueurMax, c);
    			max = Math.max(max, minMax(tmp, prof - 1));
    		}
    	//System.out.println(max);
    	return max;
    	}
    }
    
    
    

    private int minMax(PlateauJeu p,int prof){
    	if (p.finDePartie() || prof == 0){
    		this.nbfeuilles ++;
    		return h.eval(p, this.joueurMin);
    	}
    	else {
    		int min = Integer.MAX_VALUE;
    		for (CoupJeu c : p.coupsPossibles(this.joueurMin)){
    			this.nbnoeuds ++;
    			PlateauJeu tmp = p.copy();
    			tmp.joue(this.joueurMin, c);
    			min = Math.min(min, maxMin(tmp, prof - 1));
    		}
    	//System.out.println(min);
    	return min;
    	}
    }

}
