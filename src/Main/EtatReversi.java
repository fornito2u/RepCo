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
	protected JoueurReversi joueurActuel;
	

	
	/**
	 * Constructeur pour l'état initial
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatReversi() {
		// TODO Auto-generated constructor stub
		super();
		this.setJoueur(new JoueurReversi(1));
		this.setJoueur(new JoueurReversi(2));
		plateau = new String[8][8];
		this.etatInitial();
		this.succ = new ArrayList<>();
		this.setJoueurActuel((JoueurReversi)this.getJoueur(0));
		
	}
	
	/*
	 * constructeur par copie*/
	public EtatReversi(EtatReversi e) {
		
		super();
		this.plateau = e.getTab();
		this.listeJoueur = this.getListJoueur();
		this.succ = new ArrayList<>();
		if(e.getJoueurActuel()==this.getJoueur(1)) {
			this.setJoueurActuel((JoueurReversi) this.getJoueur(0));
		}
		else {
			this.setJoueurActuel((JoueurReversi)this.getJoueur(1));
		}
		
		
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
	
	public void setJoueurActuel(JoueurReversi j) {
		this.joueurActuel = j;
	}
	public JoueurReversi getJoueurActuel() {
		return this.joueurActuel;
	}
	
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
	
	public void succ() 
	{	
		String[][] tmp = this.copieEtat();
		boolean done = false;
		//permet de regarder chaques cases du plateau
		for (int i =0; i<plateau.length;i++) 
		{
			for(int j =0; j<plateau[0].length;j++) 
			{
				if(plateau[i][j]!="   ") 
				{
					if()
					// Regarder uniquement les cases blanches
					if(plateau[i][j]== " B ")
					{
						// Regarder en haut = Pour chaque case vers le haut
						for(int k=i-1; k>0; k--)
						{
							if(plateau[k][j] == " N ")
							{
								if(plateau[k-1][j] == "   ")
								{
										
								}
							}
						}			
					}
				}		
			}
		}
	}


	/**
	 * Méthode principal de lancement
	 * @param args
	 * 				Arguments
	 */

	 public static void main(String[] args) {
		 
		 EtatReversi er = new EtatReversi();
		 er.afficherTab();
		// System.out.println("////////////");
		 String [][]tab=new String[er.getTab().length][er.getTab()[0].length];
		/* for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 tab[i][j] = er.getTab()[i][j]; 
			 }
		 }
		for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 //System.out.print("["+tab[i][j]+"] ");
				 tab = er.gauche(er.getTab(), i, j);
				
			 }*/
			 
			 //System.out.println(" ");
		 //}
		// System.out.println("////////////");
		 //er.setEtat(tab);
		 //er.afficherTab();
	 }


}
