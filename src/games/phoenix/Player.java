package games.phoenix;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
	private SpriteSheet playerSpritSheet;
	private Animation animation[];
	private Ellipse headHitbox;
	private Rectangle bodyHitbox;
	private Shape hitbox;


	/**
	 * Constructeur du joueur
	 * @param appPlayer
	 */
	public Player (AppPlayer appPlayer, World world) {
		this.controllerID = appPlayer.getControllerID ();
		this.name = appPlayer.getName ();
		this.speed = 1;
		this.height = 80;
		this.width = 80;
		this.posX = (world.getHeight() - height)/2;
		this.posY = (world.getWidth() - width)/2;
		this.playerSpritSheet = new SpriteSheet(image, tw, th);
		this.animation = new Animation [4];

		try {
			animation [0] = loadAnimation(playerSpritSheet, 0, 1, 0);
			animation [1] = loadAnimation(playerSpritSheet, 0, 1, 1);
			animation [2] = loadAnimation(playerSpritSheet, 0, 1, 2);
			animation [3] = loadAnimation(playerSpritSheet, 0, 1, 3);
		} catch (SlickException e) {
			e.printStackTrace ();
		}

		this.headHitbox = new Ellipse(posX + 40, posY + 26, 26, 24);
		this.bodyHitbox = new Rectangle(posX + 30, posY + 46, 22, 32);
		this.hitbox = headHitbox.union(bodyHitbox)[0];
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
