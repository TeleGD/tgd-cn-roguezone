package games.phoenix.enemies;

import org.newdawn.slick.SlickException;

import games.phoenix.Player;

public class EnemyYellow extends Enemy{
	
	public EnemyYellow(int x, int y, Player player) throws SlickException
	{
		super(0, "yellow", x, y,EnemyColor.YELLOW);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
