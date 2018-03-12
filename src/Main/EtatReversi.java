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
	 * Constructeur pour l'état initial
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatReversi(JoueurReversi joueur1) {
		// TODO Auto-generated constructor stub
		super();
		plateau = new String[8][8];
		this.etatInitial();
		this.succ = new ArrayList<>();
		
	}
	
	/*
	 * constructeur par copie
	public EtatReversi(EtatReversi e,JoueurReversi joueur) {
		
		super(joueur);
		this.plateau = e.getTab();
		
	}
 */
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
							tmp = gauche(tmp, i, j);
							setEtat(tmp);
							/*
							//regarder en dessous à gauche
							if(plateau[i+1][j+1] == "   ") {
								
							}
							
							//regarder en dessous
							if(plateau[i][j+1] == "   ") {
								
							}
							
						*/
							
							}//fin if j
					
					}//fin if i
					
					
					
				}
			}
		}
	}

	//attention on ne peut regarder à gauche que si il reste des cases à parcourir : j doit etre < tab[0].length-1
	public String[][] gauche(String [][]tab,int i,int j) {
		//String gauche = "   ";
		if(j<tab[0].length-1) {
			if(tab[i][j] == "   ") {
				
				if(tab[i][j+1] != "   ") {
					tab=gauche(tab,i,j+1);
					tab[i][j] = tab[i][j+1];
				}
				


			}
			
			else if(tab[i][j]== " B ") {
				
				if(tab[i][j+1]==" B ") {
					tab= gauche(tab, i, j+1);
				}
				else if(tab[i][j+1] == " N ") {
					tab[i][j] =" N ";
				}
				else if(tab[i][j+1]=="   ") {
					tab[i][j] = " B ";
				}

				
			}
			
			else if(tab[i][j]== " N ") {
				
				if(tab[i][j+1]==" N ") {
					tab= gauche(tab, i, j+1);
				}
				else if(tab[i][j+1] == " B ") {
					tab[i][j]=" B ";
				}
				else if(tab[i][j+1]=="   ") {
					tab[i][j] = " N ";
				}

				
			}
		}

	
		return tab;
	}
	
	//on ne peut aller à droite que si j>0
	public String droite(String[][] tab,int i, int j) {
		String droite = "   ";
		if(j>0) {
			if(tab[i][j]=="   ") {
				if(tab[i][j-1]!= "   ") {
					droite = droite(tab, i, j-1);
				}
				else{
					droite ="   "; 
				}
				
			}
			if(tab[i][j]==" B ") {
				if(tab[i][j-1]==" B ") {
					droite = droite(tab, i, j-1);
				}
				else if(tab[i][j-1]==" N ") {
					droite = " N ";
				}
				else if(tab[i][j-1]=="   ") {
					droite = " B ";
				}

			}
			if(tab[i][j]==" N ") {
				if(tab[i][j-1]==" N ") {
					droite = droite(tab, i, j-1);
				}
				else if(tab[i][j-1]==" B ") {
					droite = " B ";
				}
				else if(tab[i][j-1]=="   ") {
					droite = " N ";
				}
				
			}
			
		}
		return droite;
	}
	/**
	 * Méthode principal de lancement
	 * @param args
	 * 				Arguments
	 */

	 public static void main(String[] args) {
		 
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
		 System.out.println("////////////");
		 String [][]tab=new String[er.getTab().length][er.getTab()[0].length];
		 for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 tab[i][j] = er.getTab()[i][j]; 
			 }
		 }
		 for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 //System.out.print("["+tab[i][j]+"] ");
				 tab = er.gauche(er.getTab(), i, j);
				
			 }
			 
			 //System.out.println(" ");
		 }
		 System.out.println("////////////");
		 er.setEtat(tab);
		 er.afficherTab();
	 }


}
