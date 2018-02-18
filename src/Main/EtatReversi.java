package Main;

import java.util.ArrayList;

/**
 * Classe repr�sentant l'�tat d'un jeu
 * @author Marvin / Damien
 *
 */
public class EtatReversi extends EtatJeu {

	/**
	 * Plateau de jeu
	 */
	protected String[][] plateau;
	protected ArrayList<EtatReversi> succ;
	
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
		this.succ = new ArrayList<>();
		
	}
 
	/**
	 * Initialisation du premier etat
	 */
	public void etatInitial(){
		for(int i=0; i<plateau.length;i++){
			for(int j =0; j< plateau[0].length; j++)
			{
				plateau[i][j] = "   ";
			}
		}
		plateau[3][3] = " B ";
		plateau[3][4] = " N ";
		plateau[4][4] = " B ";
		plateau[4][3] = " N ";
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
	 * M�thode d'affichage de l'�tat
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
	 * 
	 * @return succ
	 * la liste de successeur
	 */
	public ArrayList<EtatReversi> getSuccesseur() {
		return this.succ;
	}

	/**
	 * 
	 * @param e permet d'ajouter un etat successeur à la liste d'etat successeur
	 */
	public void setSuccesseur(EtatReversi e){
		this.succ.add(e);
	}

	public String[][] copieEtat(){
		String [][] copie = new String[plateau.length][plateau[0].length];
		for(int i=0; i<copie.length;i++) {
			for(int j=0; j<copie[0].length;j++) {
				copie[i][j] = plateau[i][j];
			}
		}
		return copie;
	}
	
	public void succ() {
		String[][] tmp = this.copieEtat();
		//permet de regarder chaques cases du plateau
		for (int i =0; i<plateau.length;i++) {
			for(int j =0; j<plateau[0].length;j++) {
				
				if(plateau[i][j]!="   ") {
					
					if(i==0) {
						
						if(j==0) {
							//regarder à gauche
							
							//regarder en dessous à gauche
							
							//regarder en dessous
							
						}
					}
				}
			}
		}
	}
	
	/**
	 * M�thode principal de lancement
	 * @param args
	 * 				Arguments
	 */

	 public static void main(String[] args) {
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
	 }


}
