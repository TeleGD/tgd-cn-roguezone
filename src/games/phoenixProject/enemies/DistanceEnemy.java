package games.phoenixProject.enemies;

import org.newdawn.slick.geom.Circle;

import games.phoenixProject.Player;

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
	public DistanceEnemy(int id, String name, int x, int y, Player player)
	{
		super(id,name,x,y);
		this.image = playerSpriteSheet.get("Rouge");
		setContactDamage(2);
		speed = 5;
		radius = 8*size/25;
		pv = 12;
		hitbox = new Circle(posX, posY, radius);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.BOUNCE);
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
