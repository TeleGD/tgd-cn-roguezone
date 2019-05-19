package games.phoenix;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppLoader;
import app.AppPlayer;
import games.phoenix.map.Room;

/**
 * Classe gérant le joueur
 */
public class Player {

	private static Image playerSpritSheet[] = new Image[4];

	static{
		playerSpritSheet[0] = AppLoader.loadPicture(World.IMAGES + "/characters/gray_0.png");
		playerSpritSheet[1] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_1.png");
		playerSpritSheet[2] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_2.png");
		playerSpritSheet[3] = AppLoader.loadPicture(World.IMAGES + File.separator + "characters" + File.separator + "gray_3.png");
	}

	
	private int controllerID;
	private String name;
	private int currentLife;	
	private int maxLife;
	private int currentShield;
	private int maxShield;
	private float speed;
	private float speedX;
	private float speedY;
	private int height;
	private int width;
	private float oldPosX;
	private float oldPosY;
	private float posX;
	private float posY;
	private Ellipse headHitbox;
	private Rectangle bodyHitbox;
	private Shape hitbox;
	private int facing;
	private int fireRate;
	private int delay;
	private Room room;

	/**
	 * Constructeur du joueur par défaut
	 * @param appPlayer
	 */
	public Player(AppPlayer appPlayer, World world) {
		this(world, appPlayer.getControllerID(), appPlayer.getName(), 100, 100, 0,100,0.3f, 0f, 0f, 80, 80, 50);
	}
	
	/**
	 * Constructeur à paramètres du joueur
	 */
	public Player(World world, int controllerID, String name, int currentLife, int maxLife,int currentShield,int maxShield ,float speed, float speedX, float speedY, int height, int width, int fireRate){
		this.controllerID = controllerID;
		this.name = name;
		this.currentLife = currentLife;
		this.maxLife = maxLife;
		this.currentLife = currentLife;
		this.maxShield = maxShield;
		this.speed = speed;
		this.height = height;
		this.width = width;
		this.posX = (world.getWidth() - this.width)/2;
		this.posY = (world.getHeight() - this.height)/2;
		this.headHitbox = new Ellipse(posX + 40, posY + 26, 26, 24);
		this.bodyHitbox = new Rectangle(posX + 30, posY + 46, 22, 32);
		this.hitbox = headHitbox.union(bodyHitbox)[0];
		this.facing = 1;
		this.fireRate = fireRate;
		this.delay = 0;
		this.room = new Room(world, 2);  
	}

	/**
	 * Méthode appellée 60 fois par seconde par World pour modifier les attribus du joueur
	 * @param container
	 * @param game
	 * @param delta
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		AppInput input = (AppInput) container.getInput ();
		
		move(input, delta);

		if(delay > 0){
			delay--;
		}
		else{
			if(input.isButtonPressed(AppInput.BUTTON_A, controllerID)){
				facing = 1;
				System.out.println("LE BOUTTON A");
			}
			
			if(input.isButtonPressed(AppInput.BUTTON_B, controllerID)){
				facing = 3;
			}
			
			if(input.isButtonPressed(AppInput.BUTTON_X, controllerID)){
				facing = 2;
			}

			if(input.isButtonPressed(AppInput.BUTTON_Y, controllerID)){
				facing = 0;
			}

			room.addProjectile(new Projectile(posX, posY, facing));
			delay = fireRate;
		}
	}
	
	/**
	 * Méthode appellée 60 fois par seconde par World pour modifier d'afficher le joueur
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		context.drawImage(playerSpritSheet[facing], posX, posY);
	}

	/**
	 * Gestion des mouvements du joueur
	 */
	private void move(AppInput input, int delta){
		speedX = input.getAxisValue(AppInput.AXIS_XL, controllerID) * speed;
		speedY = input.getAxisValue(AppInput.AXIS_YR, controllerID) * speed;

		if (speedY < 0 && Math.abs(speedY) >Math.abs(speedX)) facing = 0;
		if (speedY > 0 && Math.abs(speedX) < Math.abs(speedY)) facing = 1;
		if (speedX < 0 && Math.abs(speedX) >= Math.abs(speedY)) facing = 2;
		if (speedX > 0 && Math.abs(speedY) <= Math.abs(speedX)) facing = 3;
		if (speedX < 0.05f && speedX > -0.05f && speedY < 0.05f && speedY > -0.05f) {
			facing = 1;
			speedX = speedY = 0;
		}

		posX = oldPosX + speedX*delta;
		posY = oldPosY + speedY*delta;
	}
	
	public void confirmMove(boolean confirm) {
		if (confirm) {
			oldPosX = posX;
			oldPosY = posY;
			
			hitbox.setLocation(posX, posY);
		} else {
			posX = oldPosX;
			posY = oldPosY;
		}
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
	 * ajout de vie
	 * @param life: nombre de vie à rajouter
	 */
	public void addLife(int life){
		if(this.getCurrentLife() + life > this.getMaxLife()){
			this.setCurrentLife(this.getMaxLife());
		}
		else{
			this.setCurrentLife(this.getCurrentLife() + life);
		}
	}

	/**
	 * ajout d'une valeur bouclier
	 * @param shield : nombre de point de bouclier à rajouter
	 */
	public void addShield(int shield){
		if(this.getCurrentShield() + shield > this.getMaxShield()){
			this.setCurrentShield(this.getMaxShield());
		}
		else{
			this.setCurrentShield(this.getCurrentShield() + shield);
		}
	}

	/**
	 * met la valeur du bouclier à la valeur demandée
	 * @param newShield : nouvelle valeur du shield
	 */
	public void setCurrentShield(int newShield) {
		this.currentShield = newShield;
	}


	/**
	 * renvoie la valeur du bouclier actuel
	 */
	public int getCurrentShield(){
		return this.currentShield;
	}


	/**
	 * place la valeur du bouclier max à la valeur donnée
	 * @param maxShield : valeur max à donner au shield
	 */
	public void setMaxShield(int maxShield){
		this.maxShield = maxShield;
	}

	/**
	 * renvoie la valeur du bouclier max actuel
	 */
	public int getMaxShield(){
		return this.maxShield;
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
	
	public float[] getPos()
	{
		float[] pos = {posX,posY};
		return pos;
	}

	public int getHeight(){
		return this.height;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getWidth(){
		return this.width;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public Shape getHitbox(){
		return this.hitbox;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}
}
