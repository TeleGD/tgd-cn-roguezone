package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;
import games.phoenix.enemies.Enemy.EnemyColor;

public class EnemyBoss extends Enemy{
	
	public EnemyBoss(int x, int y, Player player) throws SlickException
	{
		super(0, "boss", x, y, EnemyColor.BOSS);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
