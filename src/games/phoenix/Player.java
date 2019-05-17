package games.phoenix;

import org.newdawn.slick.Color;

import app.AppPlayer;

/**
 * Classe gérant le joueur
 */
public class Player {

	/**
	 * 
	 */
	private int currentLife;
	
	private int maxLife;
	
	/**
	 * Numéro de la manette du joueur 
	 */
	private int controllerID;
	
	/**
	 * Nom du joueur
	 */
	private String name;

	/**
	 * Constructeur du joueur
	 * @param appPlayer
	 */
	public Player (AppPlayer appPlayer) {
		int controllerID = appPlayer.getControllerID ();
		String name = appPlayer.getName ();
		this.controllerID = controllerID;
		this.name = name;
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
	
	public void updateCurrentLife(int difference) 
	{
		currentLife += difference;
	}

	public void setCurrentLife(int newLife)
	{
		currentLife = newLife;
	}
	
	public int getCurrentLife()
	{
		return currentLife;
	}
	
	public void updateMaxLife(int difference) 
	{
		maxLife += difference;
	}

	public void setMaxLife(int newMaxLife)
	{
		maxLife = newMaxLife;
	}
	
	public int getMaxLife()
	{
		return maxLife;
	}
}
