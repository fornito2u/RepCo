package Main;

/**
 * Classe abstraite représentant l'état d'un jeu
 * @author Marvin / Damien
 *
 */
public abstract class EtatJeu 
{
	/**
	 * Le joueur qui doit jouer
	 */
	protected Joueur joueurActuel;
	
	/**
	 * Constructeur
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatJeu(Joueur joueur)
	{
		joueurActuel = joueur;
	}
	
	/**
	 * Getter joueurActuel
	 * @return joueurActuel
	 * 		   Joueur actuel
	 */
	public Joueur getJoueurActuel(){
		return this.joueurActuel;
	}
	
	
	/**
	 * Setter joueurActuel
	 * @param id
	 */
	public void setJoueurActuel(Joueur id){
		this.joueurActuel = id;
	}
	
}
