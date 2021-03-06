package games.phoenixProject.enemies;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import games.phoenixProject.Player;

public class EnemyBoss extends Enemy{

	public EnemyBoss(int x, int y, Player player)
	{
		super(0, "boss", x, y);
		this.image = playerSpriteSheet.get("Boss");
		setContactDamage(5);
		speed = 2;
		pv = 3;
		radius = 31;
		this.headHitbox = new Ellipse(posX + 48, posY + 32, 32, 30);
		this.bodyHitbox = new Rectangle(posX + 36, posY + 55, 26, 38);
		this.hitbox = headHitbox.union(bodyHitbox)[0];
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
