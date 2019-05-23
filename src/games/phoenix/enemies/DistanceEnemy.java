package games.phoenix.enemies;


public class DistanceEnemy extends Enemy{
	/**
	 * shootingRate : cadence de tir
	 * bulletSpeed : vitesse des projectiles
	 */
	private int shootingRate;
	private int bulletSpeed;

	/**
	 * 
	 * @param id : id de l'ennemis
	 * @param name : nom de l'ennemis
	 * @param img : arborescence de l'image
	 * @throws SlickException : si l'image n'a pas été trouvée ou pas bien affectée 
	 */
	public DistanceEnemy(int id, String name, int x, int y)
	{
		super(id,name,x,y);
	}
	
	/**
	 * renvoie la vitesse des projectiles
	 * @return
	 */
	public float getBulletSpeed()
	{
		return bulletSpeed;
	}
	
}
