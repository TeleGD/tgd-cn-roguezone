package games.phoenix.enemies;

import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyPurple extends Enemy{
	
	public EnemyPurple(int x, int y, Player player)
	{
		super(0, "purple", x, y);
		this.image = playerSpriteSheet.get("Violet");
		setContactDamage(2);
		speed = (float) 1.8;
		pv = 3;
		hitbox = new Ellipse(posX, posY, size/2, size/2);
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
}
