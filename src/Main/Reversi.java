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
	
	public Reversi() 
	{
		this.etat = new EtatReversi();
		etat.calculEtatSuccesseur();
	}
	
	public EtatReversi getEtat() 
	{
		return this.etat;
	}
	
	public boolean tour() 
	{
		// Vérifie qu'il éxiste un état sucesseur possible dans la liste succ (attribut de la classe EtatReversi). 
		// Si il n'y en a pas --> fin du jeu
		if(this.etat.getSuccesseur().size() != 0) 
		{
			int i = 0; 
			for(EtatReversi e : this.etat.getSuccesseur()) 
			{
				System.out.println("Possibilite "+i);
				e.afficherTab();
				System.out.println();
				i++;
			}
			System.out.println("Liste des sucesseurs : " + this.etat.getSuccesseur());
			System.out.println("Nombre de possibilite : "+i);
			System.out.println("Pour jouer inscrire le numero de la possibilitees affichee precedement choisi : ");
			Scanner sc  = new Scanner(System.in);
		    String s =  "";
		    s = sc.nextLine();
			this.etat = this.etat.getEtatSucc(Integer.parseInt(s)); // Permet d'affecter a l'etat courant le successeur choisi
			System.out.println("Plateau de jeu actuel :");
			this.etat.afficherTab();
			return true;
		}
		else
		{
			return false;
		}
		
	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		boolean b = true;
		Reversi r = new Reversi();
		System.out.println("Plateau de jeu actuel :");
		r.getEtat().afficherTab();
		while(b == true)
		{
			b = r.tour();
		}
		System.out.println("END OF THE GAME");
	}

}
