package Main;

/**
 * Classe représentant l'état d'un jeu
 * @author Marvin / Damien
 *
 */
public class EtatReversi extends EtatJeu {

	/**
	 * Plateau de jeu
	 */
	protected String[][] plateau;
	
	/**
	 * Constructeur
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatReversi(JoueurReversi joueur) {
		// TODO Auto-generated constructor stub
		super(joueur);
		plateau = new String[8][8];
		this.etatInitial();
		
	}
 
	/**
	 * Initialisation du premier etat
	 */
	public void etatInitial(){
		for(int i=0; i<plateau.length;i++){
			for(int j =0; j< plateau[0].length; j++)
			{
				plateau[i][j] = "vide";
			}
		}
		plateau[3][3] = " B  ";
		plateau[3][4] = " N  ";
		plateau[4][4] = " B  ";
		plateau[4][3] = " N  ";
	}
	
	/**
	 * Renvoit true si les deux etats sont identiques
	 * @param etat
	 * 			Etat a fournir en parametre
	 * @return b
	 * 			Boolean
	 */
	public boolean compareEtat(EtatReversi etat)
	{
		boolean b = true;
		if(this.getJoueurActuel() == etat.getJoueurActuel())
		{
			for(int i = 0; i < plateau.length; ++i)
			{
				for(int j = 0; j < plateau.length; ++j)
				{
					if(plateau[i][j] != etat.plateau[i][j])
					{
						return false;
					}
				}
			}
		}
		else
		{
			b = false;
		}
		
		return b;
	}
	
	/**
	 * Setter du plateau
	 * @param tab
	 */
	public void setEtat(String[][] tab){
		this.plateau = tab;
	}
	
	/**
	 * Getter du plateau
	 * @return plateau
	 */
	public String[][] getTab() {
		// TODO Auto-generated method stub
		return plateau;
	}

	/**
	 * Méthode d'affichage de l'état
	 */
	public void afficherTab(){
		for(int i=0; i<plateau.length;i++){
			for(int j =0; j< plateau[0].length; j++)
			{
				System.out.print("["+plateau[i][j]+"] ");
			}
			System.out.println(" ");
		}
		
	}
	
	/**
	 * Méthode principal de lancement
	 * @param args
	 * 				Arguments
	 */
	 public static void main(String[] args) {
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
	 }


}
