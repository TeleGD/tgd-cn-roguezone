package games.phoenix.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.phoenix.Player;

public class Enemy {
	
	private final String name;
	private final int id;
	
	protected float posX;
	protected float posY;
	
	private int damage;
	private float speed = 2;
	
	protected static Image playerSpriteSheet[] = new Image[4];
	
	private Image sprite;

	private EnemyBehavior behavior;
	
	/**
	 * 
	 * @param id : id du type d'ennemis
	 * @param name : nom du type d'ennemis
	 * @param img : arborescence menant à l'image de l'ennemis
	 * @throws SlickException :  si l'image n'a pas été trouvée ou pas bien affectée
	 */
	public Enemy (int id, String name) throws SlickException {
		this.name = name;
		this.id = id;
		
		this.setPosition(0,0);
		this.setContactDamage(0);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		
	}
	
	
	/**
	 * mets l'ennemis à la position souhaitée
	 * @param x : abscisse de l'ennemis
	 * @param y : ordonnée de l'ennemis
	 */
	public void setPosition(float x, float y)
	{
		this.posX = x;
		this.posY = y;		
	}
	
	public void setBehavior(EnemyBehavior b)
	{
		this.behavior = b;
	}
	
	public EnemyBehavior getBehavior()
	{
		return behavior;
	}
	
	
	/**
	 * met la position X à jour
	 * @param x
	 */
	public void setPosX(float x)
	{
		posX = x;
	}
	
	/**
	 * permet de bouger l'ennemis dans un direction donnée  
	 * @param vx
	 * @param vy
	 */
	public void move(float vx, float vy)
	{
		posX += vx;
		posY += vy;
	}
	
	/**
	 * met la position y à jour
	 * @param y
	 */
	public void setPosY(float y)
	{
		posY = y;
	}
	
	/**
	 * mets la puissance (les dégâts infligés par l'ennemis) à jour.
	 * @param dmg : les dégâts
	 */
	public void setContactDamage(int dmg)
	{
		damage = dmg;
	}
	
	/**
	 * inflige des dégâts au joueur p
	 * @param p : joueur à qui infliger les dégâts
	 */
	public void attack(Player p)
	{
		p.updateCurrentLife(-damage) ;
	}
	
	/**
	 * renvoie le nom de l'ennemis
	 * @return nom de l'ennemis en String
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * renvoie l'id de l'ennemis
	 * @return id de l'ennemis en int
	 */
	
	public int getId()
	{
		return id;
	}
	
	/**
	 * met l'image de l'ennmis à jour 
	 * @param img : arborescence de l'image
	 * @throws SlickException
	 */
	
	public void setImage(String img) throws SlickException
	{
		this.sprite = new Image(img);
	}
	
	public float[] getPos()
	{
		float[] pos = {posX,posY};
		return pos;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
}