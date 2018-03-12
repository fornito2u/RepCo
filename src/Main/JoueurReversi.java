package Main;
/**
 * 
 * @author Damien et Marvin
 * classe qui permet de créer un joueur pour jouer à réversi
 *
 */

public class JoueurReversi extends Joueur {
	
	protected String couleur;
/**
 * 
 * @param id  
 * numéro du joueur
 */
	public JoueurReversi(int id, String couleur) {
		// TODO Auto-generated constructor stub
		super(id);
		this.couleur = couleur;
	}
/*
	public JoueurReversi getJoueurSuivant() {
		JoueurReversi joueurSuivant;
		
	}
	*/
}
