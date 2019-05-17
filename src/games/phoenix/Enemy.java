package games.phoenix;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import app.AppPlayer;

public class Enemy {

	private String name;
	private int id;
	
	private Image sprite;
	
	private float shootingRate;
	private float shootSpeed;

	public Enemy (int id, String name) throws SlickException {
		this.name = name;
		this.id = id;
		
		this.sprite = new Image("images/RogueLikeAVirgin/bossBas.png");
		
		
	}
	
	

	public String getName() {
		return this.name;
	}

}