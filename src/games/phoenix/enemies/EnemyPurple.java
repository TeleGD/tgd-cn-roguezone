package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.SlickException;

import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;
import games.phoenix.enemies.Enemy.EnemyColor;

public class EnemyPurple extends Enemy{
	
	public EnemyPurple(int x, int y, Player player) throws SlickException
	{
		super(0, "purple", x, y, EnemyColor.PURPLE);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
