package joueur;
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
	public JoueurReversi(int id) {
		
		// TODO Auto-generated constructor stub
		super(id);
		this.setCouleur(id);
		
		
	}

	private void setCouleur(int id) {
		if(id==0) {
			couleur = "noir";
		}
		else {
			couleur = "blanc";
		}
	}
	
	public String getCouleur() {
		return couleur;
		
	}
}
