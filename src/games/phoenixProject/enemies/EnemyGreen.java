package games.phoenixProject.enemies;

import org.newdawn.slick.geom.Circle;

import games.phoenixProject.Player;

public class EnemyGreen extends Enemy{

	public EnemyGreen(int x, int y, Player player)
	{
		super(0, "green", x, y);
		this.image = playerSpriteSheet.get("Vert");
		setContactDamage(2);
		this.speed = (float)1.2;
		radius = 8*size/25;
		this.pv = 3;
		hitbox = new Circle(posX, posY, radius);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
