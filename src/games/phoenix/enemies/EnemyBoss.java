package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.SlickException;
import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;

public class EnemyBoss extends Enemy{
	
	public EnemyBoss(int x, int y, Player player) throws SlickException
	{
		super(0, "boss", x, y, EnemyColor.BOSS);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
