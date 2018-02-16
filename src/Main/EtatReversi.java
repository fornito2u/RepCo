package Main;

public class EtatReversi extends EtatJeu {

	protected String[][] plateau;
	
	public EtatReversi(JoueurReversi joueur) {
		// TODO Auto-generated constructor stub
		super(joueur);
		plateau = new String[8][8];
		
	}
 
	public void etatInitial(){
		
	}
	
	public void setEtat(String[][] tab){
		this.plateau = tab;
	}
	
	public String[][] getTab() {
		// TODO Auto-generated method stub
		return plateau;
	}

	public void afficherTab(){
		for(int i=0; i<plateau.length;i++){
			for(int j =0; j< plateau[0].length; j++)
			{
				System.out.println(plateau[i][j]);
			}
		}
		
	}
	
	 public static void main(String[] args) {
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
	 }


}
