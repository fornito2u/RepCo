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
		this.setJoueur(new JoueurReversi(0));
		this.setJoueur(new JoueurReversi(1));
		plateau = new String[8][8];
		this.etatInitial();
		this.succ = new ArrayList<>();
		this.setJoueurActuel((JoueurReversi)this.getJoueur(0));
		
	}
	
	/**
	  * constructeur par copie
	  * pour les etats suivants
	  * */
	
	public EtatReversi(EtatReversi e,String[][] plateau) {
		
		super();
		this.plateau = plateau;
		this.listeJoueur = e.getListJoueur();
		this.succ = new ArrayList<>();
		//System.out.println();
		//System.out.println(e.listeJoueur);
		if(e.getJoueurActuel().getCouleur()== ((JoueurReversi) this.getJoueur(1)).getCouleur()) {
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
		for(int k =-1 ; k<plateau[0].length; k++) {
			if(k==-1) {
				System.out.print("[   ] ");
			}
			else {
				System.out.print("[ "+k+" ] ");
			}
		}
		System.out.println(" ");
		for(int i=0; i<plateau.length;i++){
			for(int j =-1; j< plateau[0].length; j++)
			{
				if(j==-1) {
					System.out.print("[ "+i+" ] ");
				}
				else {
				System.out.print("["+plateau[i][j]+"] ");
				}
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

	/**
	 * 
	 * @return une copie du tableau de l'etat courant
	 */
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
	/* inutile maintenant
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
						// On regarde la case a un cran vers le haut de i (uniquement si i est pas déjà en haut du plateau) 
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
								// Si on peut aller vers le bas (le pion actuel n'est pas collé en bas du plateau)
								// et si la case en bas n'est pas vide
								// dans ce cas soit : - la case en bas de i est noir (appelez case k) 
								//						alors on peut verifier la case en bas de k (incrémentation du compteur)
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
	*/

/**
 * 	
 * @return une liste de points ou sont présent les pions adverse
 */
	public ArrayList<Point> jetonAdverse(){
		ArrayList<Point> jeton = new ArrayList<>();
		for(int i =0 ; i<this.plateau.length;i++) {
			for(int j = 0; j< this.plateau[0].length;j++) {
				if(this.joueurActuel.getCouleur()=="noir") {//si les pions du joueur actuel sont noir
					if(this.plateau[i][j]==" B ") {
						jeton.add(new Point(i, j));
					}
				}
				else { //si les pions du joueur actuel sont blanc
					if(this.plateau[i][j]==" N ") {
						jeton.add(new Point(i,j));
						}
				}
			}
		}
		//System.out.println(jeton); //pour tester que la fonction fonctionne correctement
		return jeton;
	}
	
	
	//fonction qui va regarder pour chaque jeton adverse
	//si il est possible de poser un jeton autour et si c'est le cas 
	//on calcule l'etat successeur et
	//on l'ajoute a la liste d'etat successeur
	public void calculEtatSuccesseur() { 
		String blanc = " B ";
		String noir = " N ";
		for(Point p : this.jetonAdverse()) {
			String [][]plateau;
			plateau= copieEtat();
			int x = (int) p.getX();
			int y = (int) p.getY();
			if(this.joueurActuel.getCouleur() == "noir") { //dans le cas ou le joueur pose un pion noir
				
				
				
				if(p.getY()>0 && p.getY()<plateau[0].length && p.getX()>0 && p.getX()<plateau.length) { //on regarde le centre du plateaau sans s'occuper des extremités
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x][y-1]==noir) {//regarder si à gauche du pion blanc il y a un pion noir
						//on regarde si il est possible de poser un pion noir à droite
						if(getDroite(x,y,blanc)) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								y++;
							}
							plateau[x][y]=noir;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
						
					
					}//1
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x-1][y]==noir) {//regardre au dessus si le pion est noir
						
						if(getBas(x, y, blanc)) {
							
							while(plateau[x][y] == blanc) {
								
								plateau[x][y]= noir;
								x++;
							}
							plateau[x][y]=noir;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//2
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x][y+1]==noir) { //regarde a droite si le pion est noir
						
						if(getGauche(x, y, blanc)) {
							while(plateau[x][y] == blanc) {
								plateau[x][y]= noir;
								y--;
							}
							plateau[x][y]=noir;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//3
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x+1][y] == noir) {
						System.out.println(y);
						if(getHaut(x, y, blanc)) {
							System.out.println("regarde en dessous");
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x--;
							}
							plateau[x][y]=noir;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//4
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautgauche
					if(this.plateau[x+1][y+1]==noir) {
						while(plateau[x][y] == blanc) {
							plateau[x][y] = noir;
							x--;
							y--;
						}
						plateau[x][y]=noir;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//5
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagbasGauche
					if(this.plateau[x-1][y+1]==noir) {
						while(plateau[x][y] == blanc) {
							plateau[x][y] = noir;
							x++;
							y--;
						}
						plateau[x][y]=noir;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//6
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautDroit
					if(this.plateau[x+1][y-1]==noir) {
						while(plateau[x][y] == blanc) {
							plateau[x][y] = noir;
							x--;
							y++;
						}
						plateau[x][y]=noir;
						this.setSuccesseur(new EtatReversi(this, plateau));
						
					}//7
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagBasDroit
					if(this.plateau[x-1][y-1]==noir) {
						while(plateau[x][y] == blanc) {
							plateau[x][y] = noir;
							x++;
							y++;
						}
						plateau[x][y]=noir;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//8
				}
				
			}
			else {//si le joueur actuel a les pions blanc
				if(p.getY()>0 && p.getY()<plateau[0].length && p.getX()>0 && p.getX()<plateau.length) {
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x][y-1]==blanc) {//regarder si à gauche du pion blanc il y a un pion noir
						//on regarde si il est possible de poser un pion noir à droite
						if(getDroite(x,y,blanc)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								y++;
							}
							plateau[x][y]=blanc;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
						
					
					}//1.1
					

					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x-1][y]==blanc) {//regardre au dessus si le pion est noir
						
						if(getBas(x, y, noir)) {
							
							while(plateau[x][y] == noir) {
								
								plateau[x][y]= blanc;
								x++;
							}
							plateau[x][y]=blanc;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//2.2
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x][y+1]==blanc) { //regarde a droite si le pion est noir
						
						if(getGauche(x, y, noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y]= blanc;
								y--;
							}
							plateau[x][y]=blanc;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//3.3
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x+1][y] == blanc) {
						System.out.println(y);
						if(getHaut(x, y, noir)) {
							System.out.println("regarde en dessous");
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x--;
							}
							plateau[x][y]=blanc;
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//4.4
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautgauche
					if(this.plateau[x+1][y+1]==blanc) {
						while(plateau[x][y] == noir) {
							plateau[x][y] = blanc;
							x--;
							y--;
						}
						plateau[x][y]= blanc;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//5.5
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagbasGauche
					if(this.plateau[x-1][y+1]==blanc) {
						while(plateau[x][y] == noir) {
							plateau[x][y] = blanc;
							x++;
							y--;
						}
						plateau[x][y]=blanc;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//6.6
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautDroit
					if(this.plateau[x+1][y-1]==blanc) {
						while(plateau[x][y] == noir) {
							plateau[x][y] = blanc;
							x--;
							y++;
						}
						plateau[x][y]=blanc;
						this.setSuccesseur(new EtatReversi(this, plateau));
						
					}//7.7
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagBasDroit
					if(this.plateau[x-1][y-1]==blanc) {
						while(plateau[x][y] == noir) {
							plateau[x][y] = blanc;
							x++;
							y++;
						}
						plateau[x][y]=blanc;
						this.setSuccesseur(new EtatReversi(this, plateau));
					}//8.8

					
					
					
				}
				
			}
		}
	}

	public boolean getDroite(int i, int j,String couleur) {
		
		boolean possible = false;
		while((this.plateau[i][j]==couleur) && j<this.plateau[0].length) {
			
			j++;
		}
		if(this.plateau[i][j] == "   ") {
			
			possible = true;
		}
		return possible;
	}
	
	public boolean getGauche(int i, int j , String couleur) {
		boolean possible = false;
		while((this.plateau[i][j]==couleur) && j>0) {
			j--;
		}
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
	}
	
	public boolean getHaut(int i,int j, String couleur) {
		
		boolean possible = false;
		while((this.plateau[i][j]==couleur && i>0)) {
			
			i--;
		}
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		System.out.println(possible);
		return possible;
	}
	public boolean getBas(int i,int j, String couleur) {
		
		boolean possible = false;
		
		while((this.plateau[i][j]==couleur && i<plateau.length)) {
			i++;
			
		}
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
	}
	
	
	public boolean getDiagHautGauche(int i,int j, String couleur) {
		boolean possible = false;
		
		while(this.plateau[i][j]=="   ") {
			i--;
			j--;
		}
		
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
		
	}
	
	public boolean getDiagHautdroite(int i,int j, String couleur) {
		boolean possible = false;
		
		while(this.plateau[i][j]=="   ") {
			i--;
			j++;
		}
		
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
		
	}
	

	public boolean getDiagBasGauche(int i,int j, String couleur) {
		boolean possible = false;
		
		while(this.plateau[i][j]=="   ") {
			i++;
			j--;
		}
		
		
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
		
	}

	public boolean getDiagBasDroite(int i,int j, String couleur) {
		boolean possible = false;
		
		while(this.plateau[i][j]=="   ") {
			i++;
			j++;
		}
		
		if(this.plateau[i][j]=="   ") {
			possible = true;
		}
		return possible;
		
	}
	/**
	 * Méthode principal de lancement
	 * @param args
	 * 				Arguments
	 */

	 public static void main(String[] args) {
		 
		 EtatReversi er = new EtatReversi();
		 er.afficherTab();
		 er.calculEtatSuccesseur();
		 System.out.println();
		 for(EtatReversi e : er.getSuccesseur()) {
			 
			 e.afficherTab();
			 System.out.println();
		 }
		 
		// System.out.println("////////////");
		// String [][]tab=new String[er.getTab().length][er.getTab()[0].length];
		 
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
