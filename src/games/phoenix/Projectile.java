package games.phoenix;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;

public class Projectile{

    private float posX;
    private float posY;
    private float speed;
    private float speedX;
    private float speedY;
    private int damage;
    private int range;
    private int radius;
    private Circle hitbox;
    private boolean isPiercing;

    
    /**
     * Constructeur des projectiles par défaut
     */
    public Projectile(float posX, float posY){
        this(posX, posY, 0.3f, 10, 1000, 40, false);
    }

    /**
     * Constructeur à paramètres des projectiles 
     */
    public Projectile(float posX, float posY, float speed, int damage, int range, int radius, boolean isPiercing){
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.speedX = 0;
        this.speedY = 0;
        this.damage = damage;
        this.range = range;
        this.radius = radius;
        this.hitbox = new Circle(posX, posY, (float)radius);
        this.isPiercing = isPiercing;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		AppInput input = (AppInput) container.getInput ();
		
		move(input, delta);
	}
}