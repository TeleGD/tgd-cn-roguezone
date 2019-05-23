package games.phoenix.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.StateBasedGame;

import games.phoenix.Player;

public class EnemyRed extends Enemy{
	
	public EnemyRed(int x, int y, Player player)
	{
		super(0, "red", x, y);
		this.image = playerSpriteSheet.get("Rouge");
		setContactDamage(2);
		speed = 5;
		radius = 8*size/25;
		pv = 12;
		hitbox = new Circle(posX, posY, radius);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.BOUNCE);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		if (pv < 4) {
			getBehavior().setPlayerInfluence(EnemyBehavior.interacting.FLEEING);
		}
	}
}
