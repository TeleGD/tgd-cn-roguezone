package games.phoenix.enemies;

import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyGreen extends Enemy{
	
	public EnemyGreen(int x, int y, Player player)
	{
		super(0, "green", x, y);
		this.image = playerSpriteSheet.get("Vert");
		setContactDamage(2);
		this.speed = (float)1.2;
		this.pv = 3;
		this.hitbox = new Ellipse(posX, posY, size/2, size/2);
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
}
