package Main;
/**
 * 
 * @author Damien et Marvin
 * classe représentant un joueur
 *
 */
public abstract class Joueur  {
	
	private int id;
	/**
	 * 
	 * @param id numéro du joueur
	 */
	public Joueur(int id)
	{
		this.id = id;
	}
	/**
	 * return le numéro du joueur
	 * @return id
	 */
	public int getId()
	{
		return this.id;
	}
}
