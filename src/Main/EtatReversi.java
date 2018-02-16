package Main;

public class EtatReversi extends EtatJeu {

	protected String[][] plateau;
	
	public EtatReversi(JoueurReversi joueur) {
		// TODO Auto-generated constructor stub
		super(joueur);
		plateau = new String[8][8];
		this.etatInitial();
		
	}
 
	public void etatInitial(){
		for(int i=0; i<plateau.length;i++){
			for(int j =0; j< plateau[0].length; j++)
			{
				plateau[i][j] = "    ";
			}
		}
		plateau[3][3] = " B  ";
		plateau[3][4] = " N  ";
		plateau[4][4] = " B  ";
		plateau[4][3] = " N  ";
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
				System.out.print("["+plateau[i][j]+"] ");
			}
			System.out.println(" ");
		}
		
	}
	
	
	
	 public static void main(String[] args) {
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
	 }


}
