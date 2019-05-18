package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.SlickException;

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
	
	public EnemyBoss() throws SlickException
	{
		super(0, "boss", EnemyColor.BOSS);
	}
	
	/*
	 * Fonction qui doit disparaitre à terme: c'est pour faire des test à l'arrache sur le
	 * bon fonctionnement de l'ennemis
	 */
	public void init(Enemy enemy, Player player) 
	{
		setBehavior(new EnemyBehavior(enemy, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
