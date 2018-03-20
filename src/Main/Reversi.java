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
		
		/*TEST*/
		
		
		/*TEST*/
		/////////////////////////////////
		Scanner sc  = new Scanner(System.in);
	    String s =  "";
	    s = sc.nextLine();
	    System.out.println(s);
	    
	    
		
		////////////////////////////////
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reversi r = new Reversi();
		r.tour();

	}

}
