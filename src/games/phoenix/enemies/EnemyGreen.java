package games.phoenix.enemies;

import games.phoenix.Player;

public class EnemyGreen extends Enemy{
	
	public EnemyGreen(int x, int y, Player player)
	{
		super(0, "green", x, y, EnemyColor.GREEN);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
