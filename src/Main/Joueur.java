package Main;

public abstract class Joueur {
	
	private int id;
	
	public Joueur(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
}
