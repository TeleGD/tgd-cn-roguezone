package games.phoenix.enemies;

import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyYellow extends Enemy{
	
	public EnemyYellow(int x, int y, Player player)
	{
		super(0, "yellow", x, y);
		this.image = playerSpriteSheet.get("Jaune");
		setContactDamage(2);
		this.speed = (float)1.4;
		this.pv = 3;
		this.hitbox = new Ellipse(posX, posY, size/2, size/2);
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
	
}
