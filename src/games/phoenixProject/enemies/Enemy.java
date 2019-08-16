package games.phoenixProject.enemies;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import games.phoenixProject.Player;
import games.phoenixProject.World;

public abstract class Enemy {

	protected final String name;
	protected final int id;

	protected float posX;
	protected float posY;
	protected int radius;
	protected int size;
	protected int pv;
	protected int damage;
	protected float speed = 2;
	protected Ellipse headHitbox;
	protected Rectangle bodyHitbox;
	protected Shape hitbox;
	protected Image image;
	protected static HashMap<String, Image> playerSpriteSheet = new HashMap<String, Image>();

	static {
		playerSpriteSheet.put("Bleu", AppLoader.loadPicture(World.IMAGES + "/enemies/mechantFinalFinalBleu.png"));
		playerSpriteSheet.put("Rouge", AppLoader.loadPicture(World.IMAGES + "/enemies/mechantFinalFinalRouge.png"));
		playerSpriteSheet.put("Vert", AppLoader.loadPicture(World.IMAGES + "/enemies/mechantFinalFinal.png"));
		playerSpriteSheet.put("Jaune", AppLoader.loadPicture(World.IMAGES + "/enemies/mechantFinalFinalJaune.png"));
		playerSpriteSheet.put("Violet", AppLoader.loadPicture(World.IMAGES + "/enemies/mechantFinalFinalViolet.png"));
		playerSpriteSheet.put("Boss", AppLoader.loadPicture(World.IMAGES + "/enemies/gray_1.png"));
	}

	protected EnemyBehavior behavior;

	/**
	 *
	 * @param id : id du type d'ennemis
	 * @param name : nom du type d'ennemis
	 * @param img : arborescence menant à l'image de l'ennemis
	 * @throws SlickException :  si l'image n'a pas été trouvée ou pas bien affectée
	 */

	public Enemy (int id, String name, int x, int y) {

		this.name = name;
		this.id = id;
		this.size = 100;
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

		context.drawImage( image, posX-size/2, posY-size/2, posX+size/2, posY+size/2, 0, 0, image.getWidth(), image.getHeight() );
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

	public Shape getHitbox() {
		return hitbox;
	}

	public int getRadius() {
		return radius;
	}

	public void setBehavior(EnemyBehavior b)
	{
		this.behavior = b;
	}

	public EnemyBehavior getBehavior()
	{
		return behavior;
	}

	public void setPv(int newpv) {
		pv = newpv;
	}

	public int getPv() {
		return pv;
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

		hitbox.setCenterX(posX);
		hitbox.setCenterY(posY);
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

	public Image getSprite() {
		return image;
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

	public boolean isDead() {
		return pv<=0;
	}

}
