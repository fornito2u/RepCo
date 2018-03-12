package Main;

import java.util.ArrayList;

/**
 * Classe abstraite repr�sentant l'�tat d'un jeu
 * @author Marvin / Damien
 *
 */
public abstract class EtatJeu 
{
	/**
	 * Le joueur qui doit jouer
	 */
	//protected Joueur joueurActuel;
	protected ArrayList<Joueur> listeJoueur;
	
	/**
	 * Constructeur
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatJeu()
	{
		//joueurActuel = joueur;
		this.listeJoueur = new ArrayList<>();
	}
	
	/**
	 * Getter joueurActuel
	 * @return joueurActuel
	 * 		   Joueur actuel
	 */
	public Joueur getJoueur(int indice){
		return this.listeJoueur.get(indice);
		
	}
	
	
	/**
	 * Ajoute un  joueur dans la liste 
	 * @param id
	 */
	public void setJoueur(Joueur id){
		this.listeJoueur.add(id);
	}
	
}
