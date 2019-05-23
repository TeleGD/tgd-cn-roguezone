package games.phoenix.enemies;

import games.phoenix.Player;

public class EnemyPurple extends Enemy{
	
	public EnemyPurple(int x, int y, Player player)
	{
		super(0, "purple", x, y, EnemyColor.PURPLE);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
