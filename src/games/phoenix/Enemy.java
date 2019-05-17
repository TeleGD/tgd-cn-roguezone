package games.phoenix;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Enemy {

	private final String name;
	private final int id;
	
	private int x;
	private int y;
	
	private Image sprite;
	
	public Enemy (int id, String name, String img) throws SlickException {
		this.name = name;
		this.id = id;
		this.sprite = new Image(img);
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}

	public String getName() {
		return this.name;
	}

	public int getId()
	{
		return id;
	}
	
	public void setImage(String img) throws SlickException
	{
		this.sprite = new Image(img);
	}
}