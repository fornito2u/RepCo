/**
 * 
 */
package Main;

import java.util.Scanner;

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
		etat.calculEtatSuccesseur();
		this.nbTour = 0;
	}
	
	public EtatReversi getEtat() 
	{
		return this.etat;
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
		System.out.println("Pour jouer inscrire le numero de la possibilitees affichee precedement choisi : ");
		Scanner sc  = new Scanner(System.in);
		String s =  "";
		s = sc.nextLine();
		this.etat = this.etat.getEtatSucc(Integer.parseInt(s)); // Permet d'affecter a l'etat courant le successeur choisi
		System.out.println("----------------------------------------------------------------------------------\n"
				 + "----------------------------------------------------------------------------------\n"
				 + "----------------------------------------------------------------------------------\n"
				 + "Plateau de jeu actuel :");
		this.etat.afficherTab();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Reversi r = new Reversi();
		System.out.println("----------------------------------------------------------------------------------\n"
						 + "----------------------------------------------------------------------------------\n"
						 + "----------------------------------------------------------------------------------\n"
						 + "Plateau de jeu actuel :");
		r.getEtat().afficherTab();
		r.getEtat().calculEtatSuccesseur();
		while(!r.getEtat().getSuccesseur().isEmpty()) 
		{
			r.tour();
			r.getEtat().calculEtatSuccesseur();
		}
		System.out.println("END OF THE GAME");
	}		
}
			
	


