package etat;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.JoueurReversi;

import java.awt.Point;
import java.awt.geom.Point2D;

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
	protected ArrayList<EtatReversi> succ;
	protected JoueurReversi joueurActuel;
	protected int poids; //valeur par evaluation eval0
	
	

	
	/**
	 * Constructeur pour l'état initial
	 * @param joueur
	 * 				Joueur actuel
	 */
	public EtatReversi() {
		// TODO Auto-generated constructor stub
		super();
		this.poids=0;
		this.setJoueur(new JoueurReversi(0));
		this.setJoueur(new JoueurReversi(1));
		plateau = new String[8][8];
		this.etatInitial();
		this.succ = new ArrayList<>();
		this.setJoueurActuel((JoueurReversi)this.getJoueur(0));
		//this.calculEtatSuccesseur();
		
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
		if(e.getJoueurActuel().getCouleur()== ((JoueurReversi) this.getJoueur(1)).getCouleur()) {
			this.setJoueurActuel((JoueurReversi) this.getJoueur(0));
		}
		else {
			this.setJoueurActuel((JoueurReversi)this.getJoueur(1));
		}
		//this.calculEtatSuccesseur();
		this.poids = eval0();
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
		//test();
	}
	private void test() {
		plateau[3][3] = " B ";
		plateau[3][4] = " B ";
		plateau[4][4] = " B ";
		plateau[4][3] = " N ";
		plateau[2][4] = " N ";
		plateau[2][2] = " N ";
		
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
	
	
	public EtatReversi getEtatSucc(int i) {
		return this.succ.get(i);
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
		boolean haut = false,
				bas = false,
				gauche = false,
				droite = false,
				hautGauche = false,
				basGauche = false,
				hautDroit = false,
				basDroit = false;
		
		String blanc = " B ";
		String noir = " N ";
		for(Point p : this.jetonAdverse()) {
			String [][]plateau;
			plateau= copieEtat();
			int x = (int) p.getX();
			int y = (int) p.getY();
			if(this.joueurActuel.getCouleur() == "noir") { //dans le cas ou le joueur pose un pion noir
				
				
				
				
				
				if(p.getY()>0 && p.getY()<plateau[0].length-1 && p.getX()>0 && p.getX()<plateau.length-1) { //on regarde uniquement le centre du plateaau 
					//on reinitialise x,y et plateau a chaque étape
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					droite = getDroite(x,y,blanc);
					haut = getHaut(x, y, blanc);
					bas = getBas(x, y, blanc);
					gauche = getGauche(x, y, blanc);
					hautDroit = getDiagHautdroite(x, y, blanc);
					hautGauche = getDiagHautGauche(x, y, blanc);
					basDroit = getDiagBasDroite(x, y, blanc);
					basGauche = getDiagBasGauche(x, y, blanc);
					
					if(this.plateau[x][y-1]==noir) {//regarder si à gauche du pion blanc il y a un pion noir

						//on regarde si il est possible de poser un pion noir à droite
						if(droite) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								y++;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//1
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x-1][y]==noir) {//regardre au dessus si le pion est noir
						
						if(bas) {
							
							while(plateau[x][y] == blanc) {
								
								plateau[x][y]= noir;
								x++;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//2
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					
					if(this.plateau[x][y+1]==noir) { //regarde a droite si le pion est noir
						
						if(gauche) {
							while(plateau[x][y] == blanc) {
								plateau[x][y]= noir;
								y--;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//3
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x+1][y] == noir) {
						
						if(haut) {
							//System.out.println("regarde en dessous");
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x--;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//4
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautgauche
					if(this.plateau[x+1][y+1]==noir) {
						if(hautGauche) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x--;
								y--;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
							
						}
					}//5
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagbasGauche
					if(this.plateau[x-1][y+1]==noir) {
						
						if(basGauche) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x++;
								y--;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
							
						}
						
					}//6
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautDroit : OK!
					if(this.plateau[x+1][y-1]==noir) {
						
						if(hautDroit) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x--;
								y++;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
							
						}
						
					}//7
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagBasDroit
					if(this.plateau[x-1][y-1]==noir) {
						if(basDroit) {
							while(plateau[x][y] == blanc) {
								plateau[x][y] = noir;
								x++;
								y++;
							}
							plateau[x][y]=noir;
							plateau=pionPosé(x, y, noir, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
							//System.out.println("ajouté!");
						}
					}//8
				}
				
			}
			else {//si le joueur actuel a les pions blanc
				if(p.getY()>0 && p.getY()<plateau[0].length-1 && p.getX()>0 && p.getX()<plateau.length-1) {
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x][y-1]==blanc) {//regarder si à gauche du pion blanc il y a un pion noir
						//on regarde si il est possible de poser un pion noir à droite
						if(getDroite(x,y,noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								y++;
							}
							plateau[x][y]=blanc;
							plateau=pionPosé(x, y, blanc, plateau);
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
							plateau=pionPosé(x, y, blanc, plateau);
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
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//3.3
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					if(this.plateau[x+1][y] == blanc) {
						
						if(getHaut(x, y, noir)) {
							
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x--;
							}
							plateau[x][y]=blanc;
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//4.4
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautgauche
					if(this.plateau[x+1][y+1]==blanc) {
						if(getDiagHautGauche(x,y,noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x--;
								y--;
							}
							plateau[x][y]= blanc;
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//5.5
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagbasGauche
					if(this.plateau[x-1][y+1]==blanc) {
						if(getDiagBasGauche(x,y,noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x++;
								y--;
							}
							plateau[x][y]=blanc;
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//6.6
					
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diaghautDroit
					if(this.plateau[x+1][y-1]==blanc) {
						if(getDiagHautdroite(x,y,noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x--;
								y++;
							}
							plateau[x][y]=blanc;
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
						
					}//7.7
					
					x = (int) p.getX();
					y = (int) p.getY();
					plateau= copieEtat();
					//diagBasDroit
					if(this.plateau[x-1][y-1]==blanc) {
						if(getDiagBasDroite(x,y,noir)) {
							while(plateau[x][y] == noir) {
								plateau[x][y] = blanc;
								x++;
								y++;
							}
							plateau[x][y]=blanc;
							plateau=pionPosé(x, y, blanc, plateau);
							this.setSuccesseur(new EtatReversi(this, plateau));
						}
					}//8.8

					
					
					
				}
				
			}
		}
	}
	
	
	public String[][] pionPosé(int x, int y , String couleur,String[][]plateau) {
		String blanc = " B ";
		String noir = " N ";
		int tmp1 = x;
		int tmp2 = y;
		
		if(couleur==noir) {
		
			//if(p.getY()>0 && p.getY()<plateau[0].length-1 && p.getX()>0 && p.getX()<plateau.length-1)
			if(x == 0) {
				if(y==0) {
					//droite
					if(plateau[x][y+1]==blanc) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2, blanc,noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//enbas
					if(plateau[x+1][y]==blanc) {
						tmp1 = x+1;
						tmp2 = y;
						if(getBas(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1++;
							}
							plateau[tmp1][tmp2]=noir;
						}
						//bas
					}
					//diagbasdroit
					if(plateau[x+1][y+1]==blanc) {
						tmp1 = x+1;
						tmp2 = y+1;
						if(getDiagBasDroite(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1++;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
						//basdroit
					}
				}
				
				else if(y==plateau[0].length-1) {
					//gauche
					if(plateau[x][y-1]==blanc) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, blanc,noir)) { 
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
							
						}
					//bas
						if(plateau[x+1][y]==blanc) {
							tmp1 = x+1;
							tmp2 = y;
							if(getBas(tmp1, tmp2, blanc, noir)) {
								while(plateau[tmp1][tmp2].equals(blanc)) {
									plateau[tmp1][tmp2]=noir;
									tmp1++;
								}
								plateau[tmp1][tmp2]=noir;
							}
							//bas
						}	
						
					//diagbasgauche
						if(plateau[x+1][y-1]==blanc) {
							tmp1 = x+1;
							tmp2 = y-1;
							if(getDiagBasGauche(tmp1, tmp2, blanc, noir)) {
								while(plateau[tmp1][tmp2].equals(blanc)) {
									plateau[tmp1][tmp2]=noir;
									tmp1++;
									tmp2--;
								}
								plateau[tmp1][tmp2]=noir;
							}
							//basgauche
						}
						
					}	
				}
				else {
					//gauche
					if(plateau[x][y-1]==blanc) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, blanc,noir)) { 
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
							
						}
					}
					//diagbasgauche
						if(plateau[x+1][y-1]==blanc) {
							tmp1 = x+1;
							tmp2 = y-1;
							if(getDiagBasGauche(tmp1, tmp2, blanc, noir)) {
								while(plateau[tmp1][tmp2].equals(blanc)) {
									plateau[tmp1][tmp2]=noir;
									tmp1++;
									tmp2--;
								}
								plateau[tmp1][tmp2]=noir;
							}
							//basgauche
						}
					
					//bas
						if(plateau[x+1][y]==blanc) {
							tmp1 = x+1;
							tmp2 = y;
							if(getBas(tmp1, tmp2, blanc, noir)) {
								while(plateau[tmp1][tmp2].equals(blanc)) {
									plateau[tmp1][tmp2]=noir;
									tmp1++;
								}
								plateau[tmp1][tmp2]=noir;
							}
							//bas
						}
					//droit
					if(plateau[x][y+1]==blanc) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2, blanc,noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//diagbasdroit
					if(plateau[x+1][y+1]==blanc) {
						tmp1 = x+1;
						tmp2 = y+1;
						if(getDiagBasDroite(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1++;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
						//basdroit
					}
					
				}
				
				
			}
			else if(x>0 && x<plateau.length-1 && y>0 && y<plateau[0].length-1 ) { //milieu du plateau
				
				if(plateau[x][y-1]==blanc) {
					tmp1 = x;
					tmp2 = y-1;
					//regarde à gauche
					if(getGauche(x, y-1, blanc,noir)) { 
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp2--;
						}
						plateau[tmp1][tmp2]=noir;
						
					}//*/
				}
					
				if(plateau[x-1][y]==blanc) {
					tmp1 = x-1;
					tmp2 = y;
					//haut
					if(getHaut(x-1, y, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1--;
						}
						plateau[tmp1][tmp2]=noir;
					}
				}
					
				if(plateau[x][y+1]==blanc) {
					//droit
					tmp1 = x;
					tmp2 = y+1;
					if(getDroite(tmp1, tmp2, blanc,noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp2++;
						}
						plateau[tmp1][tmp2]=noir;
					}
				}
				if(plateau[x+1][y]==blanc) {
					tmp1 = x+1;
					tmp2 = y;
					if(getBas(tmp1, tmp2, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1++;
						}
						plateau[tmp1][tmp2]=noir;
					}
					//bas
				}
				if(plateau[x+1][y+1]==blanc) {
					tmp1 = x+1;
					tmp2 = y+1;
					if(getDiagBasDroite(tmp1, tmp2, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1++;
							tmp2++;
						}
						plateau[tmp1][tmp2]=noir;
					}
					//basdroit
				}
				if(plateau[x-1][y+1]==blanc) {
					tmp1 = x-1;
					tmp2 = y+1;
					if(getDiagHautdroite(tmp1, tmp2, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1--;
							tmp2++;
						}
						plateau[tmp1][tmp2]=noir;
					}
					//hautdroit
				}
				if(plateau[x+1][y-1]==blanc) {
					tmp1 = x+1;
					tmp2 = y-1;
					if(getDiagBasGauche(tmp1, tmp2, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1++;
							tmp2--;
						}
						plateau[tmp1][tmp2]=noir;
					}
					//basgauche
				}
				if(plateau[x-1][y-1]==blanc) {
					//hautgauche
					tmp1 = x-1;tmp2 = y-1;
					if(getDiagHautGauche(tmp1, tmp2, blanc, noir)) {
						while(plateau[tmp1][tmp2].equals(blanc)) {
							plateau[tmp1][tmp2]=noir;
							tmp1--;
							tmp2--;
						}
						plateau[tmp1][tmp2]=noir;
					}
				}

			}
			else {
			
				//x=tab.lenght
				if(y==0) {
					//droite
					
					if(plateau[x][y+1]==blanc) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2, blanc,noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//enhaut
					if(plateau[x-1][y]==blanc) {
						tmp1 = x-1;
						tmp2 = y;
						//haut
						if(getHaut(x-1, y, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//diagbasdroit
					if(plateau[x-1][y+1]==blanc) {
						tmp1 = x-1;
						tmp2 = y+1;
						if(getDiagHautdroite(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
						//hautdroit
					}
				}
				else if(y==plateau[0].length-1) {
					//gauche
					if(plateau[x][y-1]==blanc) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, blanc,noir)) { 
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
							
						}
					}
					//haut
					if(plateau[x-1][y]==blanc) {
						tmp1 = x-1;
						tmp2 = y;
						//haut
						if(getHaut(x-1, y, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//diagbasgauche
					if(plateau[x-1][y-1]==blanc) {
						//hautgauche
						tmp1 = x-1;tmp2 = y-1;
						if(getDiagHautGauche(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}

				}else {
					//gauche
					if(plateau[x][y-1]==blanc) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, blanc,noir)) { 
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
							
						}
					}
					//diagbasgauche
					if(plateau[x-1][y-1]==blanc) {
						//hautgauche
						tmp1 = x-1;tmp2 = y-1;
						if(getDiagHautGauche(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
								tmp2--;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}

					//haut
					if(plateau[x-1][y]==blanc) {
						tmp1 = x-1;
						tmp2 = y;
						//haut
						if(getHaut(x-1, y, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//droit
					
					if(plateau[x][y+1]==blanc) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2, blanc,noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
					}
					//diagbasdroit
					if(plateau[x-1][y+1]==blanc) {
						tmp1 = x-1;
						tmp2 = y+1;
						if(getDiagHautdroite(tmp1, tmp2, blanc, noir)) {
							while(plateau[tmp1][tmp2].equals(blanc)) {
								plateau[tmp1][tmp2]=noir;
								tmp1--;
								tmp2++;
							}
							plateau[tmp1][tmp2]=noir;
						}
						//hautdroit
					}
					
				}
				
			
			
			}
				return plateau;
		}
			
		else {
			
			//////////////////////////////////////////////////////////
			/*haut du plateau*/
			
			
			
			if(x == 0) {
				if(y==0) {
					//bas
						if(plateau[x+1][y]==noir) {
							tmp1 = x+1;
							tmp2 = y;
							if(getBas(tmp1, tmp2,noir,blanc)) {
								while(plateau[tmp1][tmp2].equals(noir)) {
									plateau[tmp1][tmp2]=blanc;
									tmp1++;
								}
								plateau[tmp1][tmp2]=blanc;
							}
							//bas
						}
					//droit
					if(plateau[x][y+1]==noir) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2,noir,blanc)) {
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp2++;
							}
							plateau[tmp1][tmp2]=blanc;
						}
					}
					//diagbasdroit
					if(plateau[x+1][y+1]==noir) {
						tmp1 = x+1;
						tmp2 = y+1;
						if(getDiagBasDroite(tmp1, tmp2,noir, blanc)) {
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp1++;
								tmp2++;
							}
							plateau[tmp1][tmp2]=blanc;
						}
						//basdroit
					
					}
				
				}

				else if(y==plateau[0].length-1) {
					//gauche
					if(plateau[x][y-1]==noir) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, noir,blanc)) { 
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp2--;
							}
							plateau[tmp1][tmp2]=blanc;
							
						}
					}
					//diagbasgauche
						if(plateau[x+1][y-1]==noir) {
							tmp1 = x+1;
							tmp2 = y-1;
							if(getDiagBasGauche(tmp1, tmp2, noir,blanc)) {
								while(plateau[tmp1][tmp2].equals(noir)) {
									plateau[tmp1][tmp2]=blanc;
									tmp1++;
									tmp2--;
								}
								plateau[tmp1][tmp2]=blanc;
							}
							//basgauche
						}
					
					//bas
						if(plateau[x+1][y]==noir) {
							tmp1 = x+1;
							tmp2 = y;
							if(getBas(tmp1, tmp2,noir,blanc)) {
								while(plateau[tmp1][tmp2].equals(noir)) {
									plateau[tmp1][tmp2]=blanc;
									tmp1++;
								}
								plateau[tmp1][tmp2]=blanc;
							}
							//bas
						}
				}
				
				
				else {
					//gauche
					if(plateau[x][y-1]==noir) {
						tmp1 = x;
						tmp2 = y-1;
						//regarde à gauche
						if(getGauche(x, y-1, noir,blanc)) { 
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp2--;
							}
							plateau[tmp1][tmp2]=blanc;
							
						}
					}
					//diagbasgauche
						if(plateau[x+1][y-1]==noir) {
							tmp1 = x+1;
							tmp2 = y-1;
							if(getDiagBasGauche(tmp1, tmp2, noir,blanc)) {
								while(plateau[tmp1][tmp2].equals(noir)) {
									plateau[tmp1][tmp2]=blanc;
									tmp1++;
									tmp2--;
								}
								plateau[tmp1][tmp2]=blanc;
							}
							//basgauche
						}
					
					//bas
						if(plateau[x+1][y]==noir) {
							tmp1 = x+1;
							tmp2 = y;
							if(getBas(tmp1, tmp2,noir,blanc)) {
								while(plateau[tmp1][tmp2].equals(noir)) {
									plateau[tmp1][tmp2]=blanc;
									tmp1++;
								}
								plateau[tmp1][tmp2]=blanc;
							}
							//bas
						}
					//droit
					if(plateau[x][y+1]==noir) {
						//droit
						tmp1 = x;
						tmp2 = y+1;
						if(getDroite(tmp1, tmp2,noir,blanc)) {
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp2++;
							}
							plateau[tmp1][tmp2]=blanc;
						}
					}
					//diagbasdroit
					if(plateau[x+1][y+1]==noir) {
						tmp1 = x+1;
						tmp2 = y+1;
						if(getDiagBasDroite(tmp1, tmp2,noir, blanc)) {
							while(plateau[tmp1][tmp2].equals(noir)) {
								plateau[tmp1][tmp2]=blanc;
								tmp1++;
								tmp2++;
							}
							plateau[tmp1][tmp2]=blanc;
						}
						//basdroit
					}
					
				}
				
				
			}
			
			
			
			
			
			
			/*milieux du plateau*/
			else if(x>0 && x<plateau.length-1 && y>0 && y<plateau[0].length-1 ) {
				if(plateau[x][y-1]==noir) {
					tmp1 = x;
					tmp2 = y-1;
					//regarde à gauche
					if(getGauche(x, y-1, noir,blanc)) { 
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
						
					}//*/
				}
					
				if(plateau[x-1][y]==noir) {
					tmp1 = x-1;
					tmp2 = y;
					//haut
					if(getHaut(x-1, y, noir, blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
					
				if(plateau[x][y+1]==noir) {
					//droit
					tmp1 = x;
					tmp2 = y+1;
					if(getDroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
				if(plateau[x+1][y].equals(noir)) {
					tmp1 = x+1;
					tmp2 = y;
					if(getBas(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//bas
				}
				if(plateau[x+1][y+1]==noir) {
					tmp1 = x+1;
					tmp2 = y+1;
					if(getDiagBasDroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2++;
							tmp1++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//basdroit
				}
				if(plateau[x-1][y+1]==noir) {
					tmp1 = x-1;
					tmp2 = y+1;
					if(getDiagHautdroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//hautdroit
				}
				if(plateau[x+1][y-1]==noir) {
					tmp1 = x+1;
					tmp2 = y-1;
					if(getDiagBasGauche(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1++;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//basgauche
				}
				if(plateau[x-1][y-1]==noir) {
					//hautgauche
					tmp1 = x-1;tmp2 = y-1;
					if(getDiagHautGauche(tmp1, tmp2,noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				
				}
			}
			///////////////////////////////////////////////////////
			/*bas du plateau*/
			
			else if(x==plateau.length-1) {
			
			if(y==0) {
				
				//haut
				if(plateau[x-1][y]==noir) {
					tmp1 = x-1;
					tmp2 = y;
					//haut
					if(getHaut(x-1, y, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
				//droit
				
				if(plateau[x][y+1]==noir) {
					//droit
					tmp1 = x;
					tmp2 = y+1;
					if(getDroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
				//diagbasdroit
				if(plateau[x-1][y+1]==blanc) {
					tmp1 = x-1;
					tmp2 = y+1;
					if(getDiagHautdroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//hautdroit
				}
				
			
			}
			else if(y==plateau[0].length-1) {
				//gauche
				if(plateau[x][y-1]==noir) {
					tmp1 = x;
					tmp2 = y-1;
					//regarde à gauche
					if(getGauche(x, y-1, noir,blanc)) { 
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
						
					}
				}
				//diagbasgauche
				if(plateau[x-1][y-1]==noir) {
					//hautgauche
					tmp1 = x-1;tmp2 = y-1;
					if(getDiagHautGauche(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}

				//haut
				if(plateau[x-1][y]==noir) {
					tmp1 = x-1;
					tmp2 = y;
					//haut
					if(getHaut(x-1, y, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}

			}
			
/////////////////////////////////////////////////////////////////////////////
			else if(y>0 && y<plateau[0].length-1) {
				
				
				//gauche
				if(plateau[x][y-1]==noir) {
					tmp1 = x;
					tmp2 = y-1;
					//regarde à gauche
					if(getGauche(x, y-1, noir,blanc)) { 
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
						
					}
				}
				//diagbasgauche
				if(plateau[x-1][y-1]==noir) {
					//hautgauche
					tmp1 = x-1;tmp2 = y-1;
					if(getDiagHautGauche(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}

				//haut
				if(plateau[x-1][y]==noir) {
					tmp1 = x-1;
					tmp2 = y;
					//haut
					if(getHaut(x-1, y, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
				//droit
				
				if(plateau[x][y+1]==noir) {
					//droit
					tmp1 = x;
					tmp2 = y+1;
					if(getDroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
				}
				//diagbasdroit
				if(plateau[x-1][y+1]==blanc) {
					tmp1 = x-1;
					tmp2 = y+1;
					if(getDiagHautdroite(tmp1, tmp2, noir,blanc)) {
						while(plateau[tmp1][tmp2].equals(noir)) {
							plateau[tmp1][tmp2]=blanc;
							tmp1--;
							tmp2++;
						}
						plateau[tmp1][tmp2]=blanc;
					}
					//hautdroit
				}
				
			}
			
			
			
			////////////////////////////////////////////////////////
		}
		}
		return plateau;
	}

	public boolean getDroite(int i, int j,String couleur) {
		
		return getDroite(i, j, couleur, "   ");
	}
	public boolean getDroite(int i ,int j ,String couleur, String caseSorti) {
		boolean possible = false;
		//System.out.println("getDroite this.plateau[0].lenght = "+this.plateau[0].length);
		while((this.plateau[i][j]==couleur) && j<this.plateau[0].length-1) {
			
			j++;
		}
		if(this.plateau[i][j] == caseSorti) { //"   "
			
			possible = true;
		}
		return possible;
		
	}
	
	public boolean getGauche(int i, int j , String couleur,String caseSorti) {
		boolean possible = false;
		while((this.plateau[i][j]==couleur) && j>0) {
			j--;
		}
		if(this.plateau[i][j]==caseSorti) {
			possible = true;
		}
		return possible;
	}
	
	public boolean getGauche(int i, int j , String couleur) {
		//"   "
		return getGauche(i, j, couleur, "   ");
	}
	
	public boolean getHaut(int i,int j, String couleur,String caseSorti) {
		
		boolean possible = false;
		while((this.plateau[i][j]==couleur && i>0)) {
			
			i--;
		}
		if(this.plateau[i][j]==caseSorti) {
			possible = true;
		}
		//System.out.println(possible);
		return possible;
	}
	public boolean getHaut(int i, int j , String couleur) {
		return getHaut(i, j, couleur, "   ");
	}
	
	
	public boolean getBas(int i,int j, String couleur,String caseSorti) {
		
		boolean possible = false;
		
		while((this.plateau[i][j]==couleur && i<plateau.length-1)) {
			i++;
			
		}
		if(this.plateau[i][j]==caseSorti) {
			possible = true;
		}
		return possible;
	}
	public boolean getBas(int i, int j , String couleur) {
		return getBas(i, j, couleur, "   ");
	}

	public boolean getDiagHautGauche(int i,int j, String couleur,String caseSorti) {
		boolean possible = false;
		//System.out.println("getdiaghautgauche");
		while(this.plateau[i][j]==couleur && i>0 && j>0) {
			i--;
			j--;
		}
		
		if(this.plateau[i][j]==caseSorti) {
			possible = true;
		}
		//System.out.println("getDiagHautGauche : "+possible);
		return possible;
		
	}
	
	public boolean getDiagHautGauche(int i , int j , String couleur) {
		return getDiagHautGauche(i, j, couleur, "   ");
	}
	

	public boolean getDiagHautdroite(int i,int j, String couleur,String caseSorti) {
			
		boolean possible = false;		
		while((this.plateau[i][j]==couleur) && (i>0) && (j<this.plateau[0].length-1)) {
			
			i--;
			j++;
		}
		
		if(this.plateau[i][j].equals(caseSorti)) {
			possible = true;
		}
		//System.out.println(possible);
		return possible;
		
	}
	
	public boolean getDiagHautdroite(int i,int j, String couleur) {
		return getDiagHautdroite(i, j, couleur, "   ");
	}
	


	public boolean getDiagBasGauche(int i,int j, String couleur,String caseSorti) {
		boolean possible = false;
		
		while(this.plateau[i][j]==couleur && i<plateau.length-1 && j>0) {
			i++;
			j--;
		}
		if(this.plateau[i][j].equals(caseSorti)) {
			possible = true;
		}
		return possible;
		
	}
	
	public boolean getDiagBasGauche(int i,int j, String couleur) {
		return getDiagBasGauche(i, j, couleur, "   ");
	}


	public boolean getDiagBasDroite(int i,int j, String couleur,String caseSorti) {
		boolean possible = false;
		
		while(this.plateau[i][j]==couleur && i<plateau.length-1 && j<plateau[0].length-1) {
			i++;
			j++;
		}
		
		if(this.plateau[i][j].equals(caseSorti)) {
			possible = true;
		}
		return possible;
	}
	
	public boolean getDiagBasDroite(int i,int j, String couleur) {
		return getDiagBasDroite(i, j, couleur, "   ");
	}
	
	public boolean estUnEtatFinal() {
		return this.succ.isEmpty();
	}
	public int nombreJeton(String couleur) {
		int nbr=0;
		int large,haut;
		large = this.plateau[0].length;
		haut = this.plateau.length;
		for(int i =0 ; i<haut;i++) {
			for(int j=0; j<large; j++) {
				if(this.plateau[i][j]==couleur) {
					nbr++;
				}
			}
		}
		
		return nbr;
	}
	
	
	public int getNombreBlanc() {
		return nombreJeton(" B ");
	}
	
	
	public int getNombreNoir() {
		return nombreJeton(" N ");
	}
	
	
	public String getCouleurGagnante() {
		String gagnant=null;
		int blanc,noir;
		blanc = getNombreBlanc();
		noir = getNombreNoir();
		
		if(blanc>noir) {
			gagnant = "blanc";
		}
		else if (noir>blanc) {
			gagnant = "noir";
		}
		else {
			gagnant = "egalité";
		}
	
		return gagnant;
	}
	
	public String[][] getPlateau()
	{
		return this.plateau;
	}
	
	// Evalue l'int�ret de l'�tat actuel (Par calcul du mat�riel)
	public int eval0() {
		//int poids = 0; //return 0 si la situation est neutre
		
		if(this.getJoueurActuel().estBlanc()) {
			poids = this.getNombreBlanc()-this.getNombreNoir();
		}
		else {
			poids = this.getNombreNoir()-this.getNombreBlanc();
		}
		
		
		if(this.estUnEtatFinal()) 
		{
			if( this.getJoueurActuel().getCouleur().equals(this.getCouleurGagnante()))
			{
				//System.out.println("if"+this.getCouleurGagnante());
				poids = Integer.MAX_VALUE; //pour simuler + l'infini
			}
			else
			{
				//System.out.println("else"+this.getCouleurGagnante());
				poids = Integer.MIN_VALUE;
			}
		}
		
		
		return poids;
	}
	
	// Evalue l'int�ret de l'�tat actuel (Par calcul de force des pions)
	public int eval0V2() 
	{
		this.poids = 0;
		TabForce t = new TabForce(); // Tableau d'estimation de la force de chaque position		
			if(this.getJoueurActuel().estBlanc()) 
			{
				for(int i = 0; i < 8; i++)
				{
					for(int j = 0; j < 8; j++)
					{
						if(plateau[i][j]==" B ")
						{
							poids = poids + t.getValueInTabForce(i, j);
						}
						if(plateau[i][j]==" N ")
						{
							poids = poids - t.getValueInTabForce(i, j);
						}
					}
				}
			}
			else
			{
				for(int i = 0; i < 8; i++)
				{
					for(int j = 0; j < 8; j++)
					{
						if(plateau[i][j]==" N ")
						{
							poids = poids + t.getValueInTabForce(i, j);
						}
						if(plateau[i][j]==" B ")
						{
							poids = poids - t.getValueInTabForce(i, j);
						}
					}
				}
			}
		return poids;
	}
	
	
	public int eval0V3() 
	{
		poids = this.succ.size();
		return this.poids;
	}
	
	/**
	 * Compare deux fonctions eval0
	 * @param i Numéro de la première eval0 (entre 0 et 2 pour les fonction eval0, eval0V2 et eval0V3
	 * @param j Numéro de la première eval0 (entre 0 et 2 pour les fonction eval0, eval0V2 et eval0V3
	 * @param p Profondeur
	 * @return 1 si premier eval0 meilleur que le second, -1 pour l'inverse, 0 si egalité 
	 */
	public int compareEval0(int i, int j, int p)
	{
		int result = 0;
		int compteur = 1;
		int compteurVictoirePremierEval0 = 0;
		int compteurVictoireSecondEval0 = 0;
		int nbNoir = 0; // Nombre de pion noir pour 1 partie
		int nbBlanc = 0; // Nombre de pion blanc pour 1 partie
		EtatReversi er = new EtatReversi();
		er.calculEtatSuccesseur();
		for(int f = 0; f<1;f++)
		{
			while(!er.estUnEtatFinal())
			{
				er.calculEtatSuccesseur();
				if(f == 0)
				{
					//Noir utilise le premier eval0
					if(compteur == 1)
					{
						er = this.minimax(p, i);
						compteur = 2;
					}
					else // Blanc utilise le second eval0
					{
						er = this.minimax(p, j);
						compteur = 1;
					}
				}
				else
				{
					//Noir utilise le second eval0
					if(compteur == 1)
					{
						er = this.minimax(p, j);
						compteur = 2;
					}
					else // Blanc utilise le premier eval0
					{
						er = this.minimax(p, i);
						compteur = 1;
					}
				}
				er.calculEtatSuccesseur();
			}
			compteur = 1;
			nbNoir = er.getNombreNoir();
			nbBlanc = er.getNombreBlanc();
			if(nbNoir > nbBlanc)
			{
				if(f == 0)
				{
					compteurVictoirePremierEval0+=1;
				}
				else
				{
					compteurVictoireSecondEval0+=1;
				}
			}
			if(nbNoir<nbBlanc)
			{
				if(f==0)
				{
					compteurVictoireSecondEval0+=1;
				}
				else
				{
					compteurVictoirePremierEval0+=1;
				}
			}
		}
		
		if(compteurVictoirePremierEval0 > compteurVictoireSecondEval0)
		{
			result = 1;
		}
		if(compteurVictoirePremierEval0 < compteurVictoireSecondEval0)
		{
			result = -1;
		}
		return result;
	}
	
	public EtatReversi minimax(int c, int valEval0)
	{
		//System.out.println("minimax");
		float score = 0;
		float score_max = Integer.MIN_VALUE;
		EtatReversi e_sortie = null;

		
		this.calculEtatSuccesseur();
		//System.out.println(succ.size());
		//int i =0;
		//for(EtatReversi s : this.succ)
		for(int i=0;i<succ.size();i++)

		{
			//System.out.println(i);
			//i++;
			score=(float)evaluation(c, this, valEval0);
			//System.out.println(score);
			//System.out.println(score_max);
			if(score>=score_max)
			{
				e_sortie = succ.get(i);//s;
				score_max = score;
				//System.out.println(e_sortie);
			}
		}

		return e_sortie;
	}
	
	public EtatReversi minimax(int c, int valEval0,double alpha,double beta) {
		
		float score = 0;
		float score_max = Integer.MIN_VALUE;
		EtatReversi e_sortie = null;
		this.calculEtatSuccesseur();
		for(int i=0;i<succ.size();i++){
			score=(float)evaluation(c, this, valEval0,alpha,beta);

			if(score>=score_max)
			{
				e_sortie = succ.get(i);//s;
				score_max = score;
				//System.out.println(e_sortie);
			}
		}
		
		return e_sortie;
		
	}
	
	
	public double max(double x,double y) {
		double a;
		if(x>y) {
			a = x;
		}else {
			a = y;
		}
		return a;
	}
	
	
	public double min(double x,double y) {
	
		double a;
		
		if(x<y) {
			a = x;
		}else {
			a = y;
		}
		return a;
	}
	
	
	public double evaluation(int c, EtatReversi etat, int numeroEvaluation) {
		//System.out.println("evaluation");
		double score;
		score=0;
		if(etat.succ.isEmpty()) {
		etat.calculEtatSuccesseur();
		}
		if(etat.estUnEtatFinal()) {
			//System.out.println("estfinal");
			int nbNoir = etat.getNombreNoir();
			int nbBlanc = etat.getNombreBlanc();
			if(this.getJoueurActuel().equals(etat.getJoueurActuel())) {
			//if(this.getJoueurActuel()==etat.getJoueurActuel()) {
				//System.out.println("true");
				if(etat.getJoueurActuel().getCouleur()=="noir") {
					if(nbNoir > nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else if(nbNoir<nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
				}
				else {
					if(nbNoir > nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else if(nbNoir<nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
					
				}
			}
			else {

				if(etat.getJoueurActuel().getCouleur()=="noir") {
					if(nbNoir < nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else if(nbNoir>nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
				}
				else {
					if(nbNoir < nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else if(nbNoir > nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
					
				}
			}
			//retourner -infini , +infini , 0 en fonction du gagnant
		}
		if(c == 0) {
			switch (numeroEvaluation) {
			case 0:
				score = eval0();
				break;
				
			case 1:
				score = eval0V2();
				break;
				
			case 2 :
				score = eval0V3();
				break;

			default:
				score = 0;//eval0();
				break;
			}
			
			return score; 
		}
		//System.out.println(etat.getJoueurActuel().getId());
		if(etat.getJoueurActuel().equals(this.getJoueurActuel())) {
			//System.out.println("evaluation joueur different"+getJoueurActuel().toString());
			score = Integer.MIN_VALUE;
			for(EtatReversi e : etat.getSuccesseur()) {
				score = max(score,evaluation(c-1,e,numeroEvaluation));
			}
			return score;
			
		}else {
			//System.out.println("evaluation joueur different"+getJoueurActuel().toString());
			score = Integer.MAX_VALUE;
			for(EtatReversi e : etat.getSuccesseur()) {
				score = min(score,evaluation(c-1, e,numeroEvaluation));
			}
			return score;
			
		}
	}
	
	////////////////////////////////////////
	public double evaluation(int c, EtatReversi etat, int numeroEvaluation,double alpha,double beta) {
		//System.out.println("evaluation");
		double score;
		score=0;
		if(etat.succ.isEmpty()) {
		etat.calculEtatSuccesseur();
		}
		if(etat.estUnEtatFinal()) {
			//System.out.println("estfinal");
			int nbNoir = etat.getNombreNoir();
			int nbBlanc = etat.getNombreBlanc();
			if(this.getJoueurActuel().equals(etat.getJoueurActuel())) {
			//if(this.getJoueurActuel()==etat.getJoueurActuel()) {
				//System.out.println("true");
				if(etat.getJoueurActuel().getCouleur()=="noir") {
					if(nbNoir > nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else if(nbNoir<nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
				}
				else {
					if(nbNoir > nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else if(nbNoir<nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
					
				}
			}
			else {

				if(etat.getJoueurActuel().getCouleur()=="noir") {
					if(nbNoir < nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else if(nbNoir>nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
				}
				else {
					if(nbNoir < nbBlanc) {
						score = Integer.MIN_VALUE;
						return score;
					}
					else if(nbNoir > nbBlanc) {
						score = Integer.MAX_VALUE;
						return score;
					}
					else {
						score = 0;
						return score;
					}
					
				}
			}
			//retourner -infini , +infini , 0 en fonction du gagnant
		}
		if(c == 0) {
			switch (numeroEvaluation) {
			case 0:
				score = eval0();
				break;
				
			case 1:
				score = eval0V2();
				break;
				
			case 2 :
				score = eval0V3();
				break;

			default:
				score = 0;//eval0();
				break;
			}
			
			return score; 
		}
		//System.out.println(etat.getJoueurActuel().getId());
		if(etat.getJoueurActuel().equals(this.getJoueurActuel())) {
			//System.out.println("evaluation joueur different"+getJoueurActuel().toString());
			score = Integer.MIN_VALUE;
			for(EtatReversi e : etat.getSuccesseur()) {
				score = max(score,evaluation(c-1,e,numeroEvaluation,alpha,beta));
				if(score>=beta){
					return score;
					}
				alpha = max(alpha,score);


			}
			return score;
			
		}else {
			//System.out.println("evaluation joueur different"+getJoueurActuel().toString());
			score = Integer.MAX_VALUE;
			for(EtatReversi e : etat.getSuccesseur()) {
				score = min(score,evaluation(c-1, e,numeroEvaluation,alpha,beta));
				if(score<=alpha){
					return score;				
					}
				beta = min(beta,score);
			}
			return score;
			
		}
	}

	////////////////////////////////////////
	
	/**
	 * Méthode principal de lancement
	 * @param args
	 * 				Arguments
	 */
	 public static void main(String[] args) 
	 {
		 double alpha = Integer.MIN_VALUE;
		 double beta = Integer.MAX_VALUE;
		 
			Scanner sc  = new Scanner(System.in);
			String s = "";
			
			
		 
		EtatReversi er = new EtatReversi();

		er.calculEtatSuccesseur();
		int i =0;
		while(!er.estUnEtatFinal()) {
		
			//for(int i =0 ; i <16 ; i++) {
				if(i%2==1) {
					er = er.minimax(1,2,alpha,beta); //arg : profondeur ,eval0
				}else {
					er = er.minimax(1,0,alpha,beta);
				}
			er.afficherTab();
			//System.out.println(" ");
		
	
			//	s = sc.nextLine();
	
			
			
			
			i++;
		}
		
		String couleur;
		int blanc,noir;
		couleur = er.getCouleurGagnante();
		blanc = er.getNombreBlanc();
		noir = er.getNombreNoir();
		System.out.println("blanc : "+ blanc);
		System.out.println("noir : " + noir);
		System.out.println(couleur);
		
		//int i = er.compareEval0(1, 1, 0);
		//System.out.println("Result : "+ i);
		
		System.out.println("fini");

	 }
}
