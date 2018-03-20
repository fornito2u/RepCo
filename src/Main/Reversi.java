/**
 * 
 */
package Main;

import java.util.Scanner;

import etat.EtatReversi;

/**
 * @author damien
 *classe permetant de jouer à deux joueurs humains
 */
public class Reversi {
	EtatReversi etat;
	
	public Reversi() {

		this.etat = new EtatReversi();
		etat.calculEtatSuccesseur();
		
		
	}
	
	public EtatReversi getEtat() {
		return this.etat;
	}
	
	public void tour() {
		
		int i = 0; 
		for(EtatReversi e : etat.getSuccesseur()) {
			System.out.println("possibilité "+i);
			e.afficherTab();
			System.out.println();
			i++;
		}
		System.out.println("Nombre de possibilité : "+i);
		System.out.println("pour jouer inscrire le numéro de la possibilitées affichée précedement choisi : ");
		
		/////////////a modifier////////
		Scanner sc  = new Scanner(System.in);
	    //String s =  "";
	   // s = sc.nextLine();
	    //System.out.println(s);
	    
	    //////////////
		
		
		this.etat = this.etat.getEtatSucc(1); //pour affecter à l'etat courant le successeur choisi
		etat.afficherTab();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reversi r = new Reversi();
		r.getEtat().afficherTab();
	
		
			r.tour();
		

	}

}
