package Main;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Point2D;

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
	
	/**
	  * constructeur par copie
	  * pour les etats suivants
	  * */
	
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
		setTour();
		
		
		
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
	
	/**
	 * Renvoit une liste des coordonn�es auquel on peut jouer
	 */
	public Point[] succ() 
	{	
		Point[] tabPoint = new Point[60]; // A ne pas mettre a 60 mais a 61-tourActuel
		int compteurPoint = 0;
		int compt = 0;
		for (int i =0; i<plateau.length;i++) 
		{
			for(int j =0; j<plateau[0].length;j++) 
			{
				// Le joueur actuel est blanc
				if(joueurActuel.getCouleur() == "Blanc")
				{	
					// On regarde donc uniquement les cases � pion noir (car le pion pos� sera foc�ment coll� 
					// � une pi�ce de cette couleur). Soit i la case que l'on regarde actuellement (noir)
					if(plateau[i][j]== " N ")
					{
						// On regarde la case a un cran vers le haut de i (uniquement si i est pas d�j� en haut du plateau) 
						if(i>0 && plateau[i-1][j]=="   ")
						{
							// Dans le cas ou la case vers le haut est vide, on regarde vers le bas de i
							// Si on ne trouve pas de vide et qu'on trouve un pion de notre couleur (blanc)
							// dans ce cas la case vide au dessus de i est une position valide pour poser notre pion blanc
							compt = i;
							// compt passe a -1 quand on se rend compte que la case en haut de i n'est pas une solution
							// valide
							while(compt > -1)
							{
								// Si on peut aller vers le bas (le pion actuel n'est pas coll� en bas du plateau)
								// et si la case en bas n'est pas vide
								// dans ce cas soit : - la case en bas de i est noir (appelez case k) 
								//						alors on peut verifier la case en bas de k (incr�mentation du compteur)
								//					  - la case en bas est blanche, donc la case en haut de i est une position
								//						valide pour un pion blanc
								if(compt<plateau.length && plateau[compt+1][j]!="   ")
								{
									if(plateau[compt+1][j]== " B ")
									{
										compt +=1;
									}
									else
									{
										tabPoint[compteurPoint] = new Point(i-1, j);
									}
								}
							}
						}
					}
				}	
			}
		}
		return null;
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
