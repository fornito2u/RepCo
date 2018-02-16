package Main;

public abstract class EtatJeu 
{
	protected Joueur joueurActuel;
	
	public EtatJeu(Joueur joueur)
	{
		joueurActuel = joueur;
	}
	
	public Joueur getJoueurActuel(){
		return this.joueurActuel;
	}
	
	
	
	public void setJoueurActuel(Joueur id){
		this.joueurActuel = id;
	}
	
}
