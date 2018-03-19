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
	protected static int tour = 1;
	
	/**
	 * Constructeur
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatJeu()
	{
		
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
	public ArrayList<Joueur> getListJoueur(){
		return this.listeJoueur;
	}
	
	/**
	 * Ajoute un  joueur dans la liste 
	 * @param id
	 */
	public void setJoueur(Joueur id){
		this.listeJoueur.add(id);
	}
	
	public static int getTour() {
		return tour;
	}
	
	public static void setTour() {
		tour++;
	}
	
}
