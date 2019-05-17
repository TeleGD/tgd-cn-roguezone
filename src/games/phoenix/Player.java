package games.phoenix;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import app.AppPlayer;

/**
 * Classe gérant le joueur
 */
public class Player {

	private int controllerID;
	private String name;
	private int posX;
	private int posY;
	private int speed;
	private int height;
	private int width;
	private Animation animation[];

	/**
	 * Constructeur du joueur
	 * @param appPlayer
	 */
	public Player (AppPlayer appPlayer, World world) {
		this.controllerID = appPlayer.getControllerID ();
		this.name = appPlayer.getName ();
		this.posX = world.getHeight();
		this.posY = world.getWidth();
		this.speed = 1;
		this.height = 80;
		this.width = 80;
		this.animation = new Animation [4]; 
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
}
