package Main;

public abstract class EtatJeu 
{
	private int joueurActuel;
	private String[][] tab;
	
	public EtatJeu(){};
	
	public abstract int getJoueurActuel();
	
	public abstract String[][] getTab();
	
	public abstract void setJoueurActuel(int id);
}
