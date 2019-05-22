package games.phoenix.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;

public class Enemy {
	
	public static enum EnemyColor {RED, BLUE, YELLOW, GREEN, PURPLE, BOSS};
	
	private final String name;
	private final int id;
	
	protected float posX;
	protected float posY;
	
	private int size;
	private int damage;
	private float speed = 2;
	private Ellipse headHitbox;
	private Rectangle bodyHitbox;
	private Shape hitbox;
	private EnemyColor interaction = EnemyColor.GREEN;
	
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
	
	public Enemy (int id, String name, int x, int y, EnemyColor i) throws SlickException {

		this.name = name;
		this.id = id;
		interaction = i;
		this.size = 100;
		
		switch (interaction) {
		case BLUE:
			setImage("mechantFinalFinalBleu");
			setContactDamage(2);
			speed = 1;
			hitbox = new Ellipse(posX, posY, size/2, size/2);
			break;
		case GREEN:
			setImage("mechantFinalFinal");
			setContactDamage(2);
			speed = (float)1.2;
			hitbox = new Ellipse(posX, posY, size/2, size/2);
			break;
		case RED:
			setImage("mechantFinalFinalRouge");
			setContactDamage(2);
			speed = (float)1.4;
			hitbox = new Ellipse(posX, posY, size/2, size/2);
			break;
		case YELLOW:
			setImage("mechantFinalFinalJaune");
			setContactDamage(2);
			speed = (float)1.6;
			hitbox = new Ellipse(posX, posY, size/2, size/2);
			break;
		case PURPLE:
			setImage("mechantFinalFinalViolet");
			setContactDamage(2);
			speed = (float) 1.8;
			hitbox = new Ellipse(posX, posY, size/2, size/2);
			break;
		case BOSS:
			setImage("gray_1");
			setContactDamage(5);
			speed = 2;
			this.headHitbox = new Ellipse(posX + 48, posY + 32, 32, 30);
			this.bodyHitbox = new Rectangle(posX + 36, posY + 55, 26, 38);
			this.hitbox = headHitbox.union(bodyHitbox)[0];
			break;
		}
		this.setPosition(x,y);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		
		float x = this.getBehavior().influencedMove()[0];
		float y = this.getBehavior().influencedMove()[1];
		move(x,y);
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */

		context.drawImage( getSprite(), posX, posY, posX+size, posY+size, 0, 0, getSprite().getWidth(), getSprite().getHeight() );
		context.setColor(Color.green);
		context.draw(hitbox);
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
		
		hitbox.setLocation(posX, posY);
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
		this.sprite = AppLoader.loadPicture(World.IMAGES + "/enemies/"+img+".png");
	}
	
	public Image getSprite() {
		return sprite;
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