package games.phoenix.enemies;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

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
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
}
