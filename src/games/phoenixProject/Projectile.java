package games.phoenixProject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Projectile {

	private float posX;
	private float posY;
	private float speedX;
	private float speedY;
	private int damage;
	private int range;
	private int radius;
	private Circle hitbox;
	//private boolean isPiercing;
	private boolean destroyed;

	/**
	 * Constructeur à paramètres des projectiles
	 */
	public Projectile(float posX, float posY, float speed, int direction, int damage, int range, int radius){
		this.posX = posX;
		this.posY = posY;
		this.speedX = direction<=1?0:(direction==2?-speed:speed);
		this.speedY = direction<=1?(direction==0?-speed:speed):0;
		this.damage = damage;
		// range (temps) = range (ditance) / speed (vitesse)
		this.range = (int) (range/speed);
		this.radius = radius;
		this.hitbox = new Circle(posX, posY, (float)radius);
		this.destroyed = false;
	}

	/**
	 * Méthode appellée 60 fois par seconde par World pour modifier les attribus des projectiles
	 * @param container
	 * @param game
	 * @param delta
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		posX += speedX*delta;
		posY += speedY*delta;
		range -= delta;

		hitbox.setLocation(posX, posY);

		if (range<=0) destroyed = true;
	}

	/**
	 * Méthode appellée 60 fois par seconde par World pour modifier d'afficher les projectiles
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		context.setColor(Color.white);
		context.fill(hitbox);
	}

	public int getDamage() {
		return damage;
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void destroy() {
		this.destroyed = true;
	}

	public int getRadius() {
		return radius;
	}

	public float[] getPos() {
		float pos[] = {posX,posY};
		return pos;
	}
}
