package games.phoenix.enemies;

import org.newdawn.slick.geom.Ellipse;

import games.phoenix.Player;

public class EnemyBlue extends Enemy{
	
	public EnemyBlue(int x, int y, Player player)
	{
		super(0, "blue", x, y);
		this.image = playerSpriteSheet.get("Bleu");
		setContactDamage(2);
		this.speed = 1;
		this.pv = 3;
		this.hitbox = new Ellipse(posX, posY, size/2, size/2);
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
		setBehavior(new EnemyBehavior(this, player));
	}
}
