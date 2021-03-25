package games.phoenixProject.enemies;

import org.newdawn.slick.geom.Circle;

import games.phoenixProject.Player;

public class EnemyPurple extends Enemy{

	public EnemyPurple(int x, int y, Player player)
	{
		super(0, "purple", x, y);
		this.image = playerSpriteSheet.get("Violet");
		setContactDamage(2);
		speed = (float) 1.8;
		radius = 8*size/25;
		pv = 3;
		hitbox = new Circle(posX, posY, radius);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
