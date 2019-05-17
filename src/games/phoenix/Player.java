package games.phoenix;

import java.io.File;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import app.AppPlayer;

/**
 * Classe gérant le joueur
 */
public class Player {

	private static Image playerSpritSheet[] = new Image[4];

	static{
		playerSpritSheet[0] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_0.png");
		playerSpritSheet[1] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_1.png");
		playerSpritSheet[2] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_2.png");
		playerSpritSheet[3] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_3.png");
	}

	
	private int controllerID;
	private String name;
	private int currentLife;	
	private int maxLife;
	private int speed;
	private int height;
	private int width;
	private int posX;
	private int posY;
	private Ellipse headHitbox;
	private Rectangle bodyHitbox;
	private Shape hitbox;
	private int facing;


	/**
	 * Constructeur du joueur
	 * @param appPlayer
	 */
	public Player (AppPlayer appPlayer, World world) {
		this.controllerID = appPlayer.getControllerID ();
		this.name = appPlayer.getName ();
		this.currentLife = 100;
		this.maxLife = 100;
		this.speed = 1;
		this.height = 80;
		this.width = 80;
		this.posX = (world.getHeight() - height)/2;
		this.posY = (world.getWidth() - width)/2;
		this.headHitbox = new Ellipse(posX + 40, posY + 26, 26, 24);
		this.bodyHitbox = new Rectangle(posX + 30, posY + 46, 22, 32);
		this.hitbox = headHitbox.union(bodyHitbox)[0];
		this.facing = 1;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */


	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */

		playerSpritSheet[facing].draw((float)posX, (float)posY);
	}

	/**
	 * Accesseur de controllerID
	 * @return
	 */
	public int getControllerID () {
		return this.controllerID;
	}

	/**
	 * Accesseur du nom du joueur
	 * @return
	 */
	public String getName () {
		return this.name;
	}
	
	/**
	 * mettre à jour la vie actuelle  
	 * @param difference : positive , ajoute difference à la vie actuelle; négative enlève difference à la vie actuelle
	 */
	public void updateCurrentLife(int difference) 
	{
		currentLife += difference;
	}

	/**
	 * met la vie actuelle à la vie demandée
	 * @param difference : nouvelle vie actuelle
	 */
	public void setCurrentLife(int newLife)
	{
		currentLife = newLife;
	}
	
	/**
	 * renvoie la vie courrante
	 * @return
	 */
	public int getCurrentLife()
	{
		return currentLife;
	}

	/**
	 * mettre à jour la vie max
	 * @param difference : positive , ajoute difference à la vie max; négative enlève difference à la vie max
	 */
	public void updateMaxLife(int difference) 
	{
		maxLife += difference;
	}

	/**
	 * met la vie maximale à la vie demandée
	 * @param newMaxLife : nouvelle vie actuelle
	 */
	public void setMaxLife(int newMaxLife)
	{
		maxLife = newMaxLife;
	}
	
	/**
	 * renvoie la vie max
	 * @return 
	 */
	public int getMaxLife()
	{
		return maxLife;
	}
}
