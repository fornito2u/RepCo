package Main;

public class Main 
{
	public static void main(String[] args)
	{
		 EtatReversi er = new EtatReversi(new JoueurReversi(1));
		 er.afficherTab();
		 System.out.println("////////////");
		 String [][]tab=new String[er.getTab().length][er.getTab()[0].length];
		 for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 tab[i][j] = er.getTab()[i][j]; 
			 }
		 }
		 for(int i = 0; i<er.getTab().length;i++) {
			 for(int j =0; j<er.getTab()[0].length;j++) {
				 //System.out.print("["+tab[i][j]+"] ");
				 tab = er.gauche(er.getTab(), i, j);
				
			 }
			 
			 //System.out.println(" ");
		 }
		 System.out.println("////////////");
		 er.setEtat(tab);
		 er.afficherTab();
	}
}
