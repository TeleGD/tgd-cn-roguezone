package games.phoenix;

import org.newdawn.slick.SlickException;

public class DistanceEnemy extends Enemy{
	/**
	 * shootingRate : cadence de tir
	 * bulletSpeed : vitesse des projectiles
	 */
	private float shootingRate;
	private float bulletSpeed;

	/**
	 * 
	 * @param id : id de l'ennemis
	 * @param name : nom de l'ennemis
	 * @param img : arborescence de l'image
	 * @throws SlickException : si l'image n'a pas été trouvée ou pas bien affectée 
	 */
	public DistanceEnemy(int id, String name, String img) throws SlickException
	{
		super(id,name,img);
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
