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

import app.AppInput;
import app.AppLoader;
import app.AppPlayer;

/**
 * Classe gérant le joueur
 */
public class Player {

	private static Image playerSpritSheet[] = new Image[4];

	static{
		playerSpritSheet[0] = AppLoader.loadPicture(World.IMAGES + "/characters/gray_0.png");
		playerSpritSheet[1] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_1.png");
		playerSpritSheet[2] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_2.png");
		playerSpritSheet[3] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_3.png");
	}

	
	private int controllerID;
	private String name;
	private int currentLife;	
	private int maxLife;
	private int speed;
	private int delta;
	private int height;
	private int width;
	private int posX;
	private int posY;
	private Ellipse headHitbox;
	private Rectangle bodyHitbox;
	private Shape hitbox;
	private int facing;
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;

	/**
	 * Constructeur du joueur par défaut
	 * @param appPlayer
	 */
	public Player(AppPlayer appPlayer, World world) {
		this(world, appPlayer.getControllerID(), appPlayer.getName(), 100, 100, 0, 80, 80);
	}
	
	/**
	 * Constructeur à paramètre
	 */
	public Player(World world, int controllerID, String name, int currentLife, int maxLife, int speed, int height, int width){
		this.controllerID = controllerID;
		this.name = name;
		this.currentLife = currentLife;
		this.maxLife = maxLife;
		this.speed = speed;
		this.height = height;
		this.width = width;
		this.posX = (world.getWidth() - this.width)/2;
		this.posY = (world.getHeight() - this.height)/2;
		this.headHitbox = new Ellipse(posX + 40, posY + 26, 26, 24);
		this.bodyHitbox = new Rectangle(posX + 30, posY + 46, 22, 32);
		this.hitbox = headHitbox.union(bodyHitbox)[0];
		this.facing = 1;
		this.moveLeft = false;
		this.moveRight = false;
		this.moveUp = false;
		this.moveDown = false;
	}

	/**
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		AppInput input = (AppInput) container.getInput ();
		
		if(input.isControlPressed(AppInput.BUTTON_UP, controllerID)){
			moveUp = true;
			moveDown = moveLeft = moveRight = false;
			facing = 0;
		}
		else if(input.isControlPressed(AppInput.BUTTON_DOWN, controllerID)){
			moveDown = true;
			moveUp = moveLeft = moveRight = false;
			facing = 1;
		}
		else if(input.isControlPressed(AppInput.BUTTON_LEFT, controllerID)){
			moveLeft = true;
			moveUp = moveDown = moveRight = false;
			facing = 2;
		}
		else if(input.isControlPressed(AppInput.BUTTON_RIGHT, controllerID)){
			moveRight = true;
			moveUp = moveDown = moveLeft = false;
			facing = 3;
		}

		if(moveUp){

		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		//context.drawImage(AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_0.png"), 0, 0);
		context.drawImage( playerSpritSheet[facing], posX, posY);
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
	
	public int[] getPos()
	{
		int[] pos = {posX,posY};
		return pos;
	}
}
