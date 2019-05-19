package games.phoenix;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;

public class Projectile{

    private float posX;
    private float posY;
    private float speed;
    private float speedX;
    private float speedY;
    private int direction;
    private int damage;
    private int range;
    private int radius;
    private Circle hitbox;
    private boolean isPiercing;

    
    /**
     * Constructeur des projectiles par défaut
     */
    public Projectile(float posX, float posY, int direction){
        this(posX, posY, 0.3f, direction, 10, 1000, 40, false);
    }

    /**
     * Constructeur à paramètres des projectiles 
     */
    public Projectile(float posX, float posY, float speed, int direction, int damage, int range, int radius, boolean isPiercing){
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.speedX = 0;
        this.speedY = 0;
        this.direction = direction;
        this.damage = damage;
        this.range = range;
        this.radius = radius;
        this.hitbox = new Circle(posX, posY, (float)radius);
        this.isPiercing = isPiercing;
    }

    /**
	 * Méthode appellée 60 fois par seconde par World pour modifier les attribus des projectiles
	 * @param container
	 * @param game
	 * @param delta
	 */
    public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		
		move(delta);
    }

    /**
	 * Gestion des mouvements des projectiles
	 */
    private void move(int delta){
        speedX = 0;
        speedY = 0;
        
        if(direction == 0){
            speedY = -speed;
        }

        if(direction == 1){
            speedY = speed;
        }

        if(direction == 2){
            speedX = -speed;
        }

        if(direction == 3){
            speedX = speed;
        }

        posX += speedX*delta;
        posY += speedY*delta;
    }
    
    /**
     * Méthode appellée 60 fois par seconde par World pour modifier d'afficher les projectiles
     */
    public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
        
       // context.drawImage(hitbox, posX, posY);
	}
}