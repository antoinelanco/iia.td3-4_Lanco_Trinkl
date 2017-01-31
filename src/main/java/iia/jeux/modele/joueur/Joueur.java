package iia.jeux.modele.joueur;


public class Joueur  //A
{

    private String id;
    private int graines;

    public Joueur(String s)
    {
        id = s;
        this.graines = 0;
    }
    public int getGraines(){
    	return this.graines;
    }
    public void addGraines(int nb){
    	this.graines = this.graines+nb;
    }

    public String getId()    // A
    {
        return id;
    }

    public void setId(String s)   //A(String)
    {
        id = s;
    }

    public String toString()
    {
        return id;
    }

}
