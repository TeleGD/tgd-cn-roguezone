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
	static{
		playerSpriteSheet[0] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_0.png");
		playerSpriteSheet[1] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_1.png");
		playerSpriteSheet[2] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_2.png");
		playerSpriteSheet[3] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_3.png");
	}


	
	public EnemyBoss(int x, int y, Player player) throws SlickException
	{
		super(0, "boss", x, y, EnemyColor.BOSS);
		setBehavior(new EnemyBehavior(this, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
