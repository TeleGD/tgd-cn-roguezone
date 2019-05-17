package games.phoenix;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import app.AppPlayer;

/**
 * Classe gérant le joueur
 */
public class Player {

	private int currentLife;	
	private int maxLife;
	
	/**
	 * Numéro de la manette du joueur 
	 */
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
