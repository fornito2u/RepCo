/**
 * 
 */
package Main;

import java.util.Scanner;

import etat.EtatJeu;
import etat.EtatReversi;

/**
 * @author Damien / Marvin
 * Classe permetant de jouer a deux joueurs humains
 */
public class Reversi 
{
	EtatReversi etat;
	int nbTour;
	
	public Reversi() 
	{
		this.etat = new EtatReversi();
		//etat.calculEtatSuccesseur();
		this.nbTour = 0;
	}
	
	public EtatReversi getEtat() 
	{
		return this.etat;
	}
	
	public boolean estUnEntier(String chaine) 
	{
		try 
		{
			Integer.parseInt(chaine);
		} 
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	public void tour() 
	{		
		int i = 0; 
		this.nbTour += 1;
		
		for(EtatReversi e : this.etat.getSuccesseur()) 
		{
			System.out.println("Possibilite "+i);
			e.afficherTab();
			System.out.println();
			i++;
			
		}
		System.out.println("Tour n°" + nbTour+ " / Joueur " + this.etat.getJoueurActuel().getCouleur());
		System.out.println("Nombre de possibilite : "+i);
		Scanner sc  = new Scanner(System.in);
		String s = "";
		int si = -1;
		while(si < 0 || si > this.etat.getSuccesseur().size()-1 || estUnEntier(s) == false)
		{
			System.out.println("Pour jouer, entrez un numéro de possibilité valide");

			
			s = sc.nextLine();
			
			
			try 
			{
				si = Integer.parseInt(s);
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Vous devez entrer un entier !");
			}
		}
		
		this.etat = this.etat.getEtatSucc(Integer.parseInt(s)); // Permet d'affecter a l'etat courant le successeur choisi
		System.out.println("----------------------------------------------------------------------------------\n"
				 + "----------------------------------------------------------------------------------\n"
				 + "----------------------------------------------------------------------------------\n"
				 + "Plateau de jeu actuel :");
		this.etat.afficherTab();
	}
	public void win() {
		String couleur;
		int blanc,noir;
		couleur = this.getEtat().getCouleurGagnante();
		blanc = getEtat().getNombreBlanc();
		noir = getEtat().getNombreNoir();
		System.out.println("blanc : "+ blanc);
		System.out.println("noir : " + noir);
		System.out.println(couleur);
		
	}
	
	public static void main(String[] args) 
	{
		Reversi r = new Reversi();
		int blanc;
		int noir;
		Scanner sc  = new Scanner(System.in);
		String s = "";
		int si = -1;
		
		StringBuilder choix = new StringBuilder("Que voulez vous faire??\n");
		choix.append("0 si vous voulez lancer le jeu à 2 joueurs humains, tour par tour,\n1 si vous voulez jouer une partie contre la machine,\n2 si vous voulez regarder jouer la machine contre elle meme");
		System.out.println(choix.toString());
		
		s = sc.nextLine();
		
		
		try 
		{
			si = Integer.parseInt(s);
		} 
		catch (NumberFormatException e)
		{
			System.out.println("Vous devez entrer un entier !");
		}
		
		
		switch (si) {
		case 0:
			
			System.out.println("----------------------------------------------------------------------------------\n"
							 + "----------------------------------------------------------------------------------\n"
							 + "----------------------------------------------------------------------------------\n"
							 + "Plateau de jeu actuel :");
			r.getEtat().afficherTab();
			r.getEtat().calculEtatSuccesseur();
			while(!r.getEtat().estUnEtatFinal()) 
			{
				r.tour();
				r.getEtat().calculEtatSuccesseur();
			}
			r.win();
			
			break;
			
		case 1:
			
			
			break;
			
		case 2:

			int i=0,j=0,p=0;
			
			System.out.println("veuillez choisir la fonction d'evaluation pour les pions noirs : ");
			System.out.println("le choix 0 utilise une fonction qui compare le nombre de jetons dans les différents états successeurs");
			System.out.println("le choix 1 se base sur un tableau de force basé sur la place des pions de chaques couleurs");
			System.out.println("le choix 2 se base sur le nombre de successeur possible ");
			s = sc.nextLine();
			try 
			{
				i = Integer.parseInt(s);
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Vous devez entrer un entier !");
			}
			
			System.out.println("veuillez choisir la fonction d'evaluation pour les pions blancs : ");
			System.out.println("le choix 0 utilise une fonction qui compare le nombre de jetons dans les différents états successeurs");
			System.out.println("le choix 1 se base sur un tableau de force basé sur la place des pions de chaques couleurs");
			System.out.println("le choix 2 se base sur le nombre de successeur possible ");
			s = sc.nextLine();
			try 
			{
				j = Integer.parseInt(s);
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Vous devez entrer un entier !");
			}
			System.out.println("veuillez choisir le nombre de coup calculé à l'avance : ");
			s = sc.nextLine();
			try 
			{
				p = Integer.parseInt(s);
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Vous devez entrer un entier !");
			}
			////////////////////////////////
			EtatReversi er = new EtatReversi();
			er.calculEtatSuccesseur();
			int k = 0;
			while(!er.estUnEtatFinal()) 
			{
					if(k%2==1) 
					{
						er = er.minimax(p,j,Integer.MIN_VALUE,Integer.MAX_VALUE); //arg : profondeur ,eval0   <-- Joue les blanc = second eval0 <-- utilise j
					}
					else 
					{
						er = er.minimax(p,i,Integer.MIN_VALUE,Integer.MAX_VALUE); // <-- Joue les noirs = premier eval0 <-- utilise i
					}
				er.afficherTab();
				System.out.println("/////////////////////////////////////////////////////////////");
				k++;
			}
			blanc = er.getNombreBlanc();
			noir = er.getNombreNoir();
			System.out.println("NOMBRE DE BLANC : "+ blanc);
			System.out.println("NOMBRE DE NOIR : "+noir);
			if(blanc < noir)
			{
				
				System.out.println("NOIR GAGNANT");
			}
			else if(blanc > noir)
			{
				System.out.println("BLANC GAGNANT");
			}else {
				System.out.println("ÉGALITÉ");
			}
			
			///////////////////////////////
			//r.getEtat().compareEval0(i, j, p);//Parametre : NuméroPremierEval0, NuméroSecondEval0, Profondeur
			break;

		default:
			break;
		}
		

		System.out.println("FIN DU JEU");
		
	}		
	
}
			
	


