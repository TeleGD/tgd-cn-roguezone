package games.phoenix.enemies;

import org.newdawn.slick.SlickException;

import games.phoenix.Player;

public class EnemyPurple extends Enemy{
	
	public EnemyPurple(int x, int y, Player player) throws SlickException
	{
		super(0, "purple", x, y, EnemyColor.PURPLE);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
