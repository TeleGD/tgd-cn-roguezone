package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;

public class EnemyTest extends Enemy{
	
	static{
		playerSpriteSheet[0] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_0.png");
		playerSpriteSheet[1] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_1.png");
		playerSpriteSheet[2] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_2.png");
		playerSpriteSheet[3] = AppLoader.loadPicture(World.IMAGES + File.separator + "enemies" + File.separator + "gray_3.png");
	}
	
	public EnemyTest() throws SlickException
	{
		super(0, "Test");
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		
		float x = this.getBehavior().influencedMove()[0];
		float y = this.getBehavior().influencedMove()[1];
		move(x,y);
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */

		context.drawImage( playerSpriteSheet[0], posX, posY);
	}
	
	/*
	 * Fonction qui doit disparaitre à terme: c'est pour faire des test à l'arrache sur le
	 * bon fonctionnement de l'ennemis
	 */
	public void init(Enemy enemy, Player player) 
	{
		setBehavior(new EnemyBehavior(enemy, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.FLEEING);
	}
}
