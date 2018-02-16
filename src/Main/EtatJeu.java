package Main;

public abstract class EtatJeu 
{
	private Joueur joueurActuel;
	
	public EtatJeu(Joueur joueur)
	{
		joueurActuel = joueur;
	}
	
	public abstract int getJoueurActuel();
	
	public abstract String[][] getTab();
	
	public abstract void setJoueurActuel(int id);
}
