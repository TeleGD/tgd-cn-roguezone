package games.phoenix.enemies;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyBlue extends Enemy{
	
	public EnemyBlue(int x, int y, Player player)
	{
		super(0, "blue", x, y);
		this.image = playerSpriteSheet.get("Bleu");
		setContactDamage(2);
		this.speed = 1;
		radius = 8*size/25;
		this.pv = 3;
		hitbox = new Circle(posX, posY, radius);
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
}
