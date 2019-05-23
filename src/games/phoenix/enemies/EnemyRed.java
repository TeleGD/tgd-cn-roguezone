package games.phoenix.enemies;

import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyRed extends Enemy{
	
	public EnemyRed(int x, int y, Player player)
	{
		super(0, "red", x, y);
		this.image = playerSpriteSheet.get("Rouge");
		setContactDamage(2);
		speed = (float)1.6;
		pv = 3;
		hitbox = new Ellipse(posX, posY, size/2, size/2);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.BOUNCE);
	}
}
