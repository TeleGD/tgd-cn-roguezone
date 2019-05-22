package games.phoenix.enemies;

import org.newdawn.slick.SlickException;

import games.phoenix.Player;

public class EnemyBlue extends Enemy{
	
	public EnemyBlue(int x, int y, Player player) throws SlickException
	{
		super(0, "blue", x, y, EnemyColor.BLUE);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
