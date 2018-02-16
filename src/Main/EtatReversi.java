package Main;

public class EtatReversi extends EtatJeu {

	protected String[][] plateau;
	
	public EtatReversi(JoueurReversi joueur) {
		// TODO Auto-generated constructor stub
		super(joueur);
		plateau = new String[8][8];
		
	}

	
	public String[][] getTab() {
		// TODO Auto-generated method stub
		return plateau;
	}

	public void afficherTab(){
		System.out.println(plateau);
	}
	
	


}
