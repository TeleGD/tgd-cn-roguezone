package games.phoenix.enemies;

import games.phoenix.Player;

public class EnemyBoss extends Enemy{
	
	public EnemyBoss(int x, int y, Player player)
	{
		super(0, "boss", x, y, EnemyColor.BOSS);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
