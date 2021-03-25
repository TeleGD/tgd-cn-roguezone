package games.phoenixProject.enemies;

import org.newdawn.slick.geom.Circle;

import games.phoenixProject.Player;

public class EnemyYellow extends Enemy{

	public EnemyYellow(int x, int y, Player player)
	{
		super(0, "yellow", x, y);
		this.image = playerSpriteSheet.get("Jaune");
		setContactDamage(2);
		this.speed = (float)1.4;
		radius = 8*size/25;
		this.pv = 3;
		hitbox = new Circle(posX, posY, radius);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}

}
