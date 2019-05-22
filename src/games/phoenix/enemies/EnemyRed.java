package games.phoenix.enemies;

import org.newdawn.slick.SlickException;

import games.phoenix.Player;

public class EnemyRed extends Enemy{
	
	public EnemyRed(int x, int y, Player player) throws SlickException
	{
		super(0, "red", x, y, EnemyColor.RED);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
