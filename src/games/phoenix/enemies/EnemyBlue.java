package games.phoenix.enemies;

import games.phoenix.Player;

public class EnemyBlue extends Enemy{
	
	public EnemyBlue(int x, int y, Player player)
	{
		super(0, "blue", x, y, EnemyColor.BLUE);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
