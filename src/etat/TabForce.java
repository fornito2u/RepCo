package etat;

public class TabForce 
{
	private int[][] tabForce;
	
	public TabForce()
	{
		tabForce = new int[8][8];
		
		// Ligne 0 et 7
		for(int f = 0; f < 8; f= f+ 7)
		{
			tabForce[f][0] = 500;
			tabForce[f][1] = -150;
			tabForce[f][2] = 30;
			tabForce[f][3] = 10;
			tabForce[f][4] = 10;
			tabForce[f][5] = 30;
			tabForce[f][6] = -150;
			tabForce[f][7] = 500;
		}
		// Ligne 1 et 6
		for(int e = 1; e < 7; e+=5)
		{
			tabForce[e][0] = -150;
			tabForce[e][1] = -250;
			tabForce[e][2] = 0;
			tabForce[e][3] = 0;
			tabForce[e][4] = 0;
			tabForce[e][5] = 0;
			tabForce[e][6] = -250;
			tabForce[e][7] = -150;
		}
		// Ligne 2 et 5
		for(int k = 2; k < 6; k+=3)
		{
			tabForce[k][0] = 30;
			tabForce[k][1] = 0;
			tabForce[k][2] = 1;
			tabForce[k][3] = 2;
			tabForce[k][4] = 2;
			tabForce[k][5] = 1;
			tabForce[k][6] = 0;
			tabForce[k][7] = 30;
		}
		// Ligne 3 et 4
		for(int i = 3; i<5;i++)
		{
			tabForce[i][0] = 10;
			tabForce[i][1] = 0;
			tabForce[i][2] = 2;
			tabForce[i][3] = 16;
			tabForce[i][4] = 16;
			tabForce[i][5] = 2;
			tabForce[i][6] = 0;
			tabForce[i][7] = 10;
		}
	}
	
	public int[][] getTabForce()
	{
		return tabForce;
	}
	
	public int getValueInTabForce(int i, int j)
	{
		return tabForce[i][j];
	}
	
	public static void main(String [] args)
	{
		TabForce t = new TabForce();
		for(int i = 0; i<8;i++)
		{
			for(int j = 0; j<8;j++)
			{
				if(t.getValueInTabForce(i, j) < 0)
				{
					System.out.print(t.getValueInTabForce(i, j)+" | ");
				}
				if(t.getValueInTabForce(i, j) > 99)
				{
					System.out.print(t.getValueInTabForce(i, j)+"  | ");
				}
				if(t.getValueInTabForce(i, j) < 100 && t.getValueInTabForce(i, j) > 9)
				{
					System.out.print(t.getValueInTabForce(i, j)+"   | ");
				}
				if(t.getValueInTabForce(i, j) < 10 && t.getValueInTabForce(i, j) >= 0)
				{
					System.out.print(t.getValueInTabForce(i, j)+"    | ");
				}
			}
			System.out.println("\n ----------------------------------------------------");
		}
	}
	
}
