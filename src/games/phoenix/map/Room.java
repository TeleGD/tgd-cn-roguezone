package games.phoenix.map;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;
import games.phoenix.enemies.Enemy;

/**
 * 
 * @author amos
 *
 * Object de chaque salle
 */
public class Room {
	
	private Image fond = AppLoader.loadPicture(World.IMAGES+File.separator+"rooms"+File.separator+"room_15.png");
	
	private int worldWidth;
	private int worldHeight;
	
	private int xMargin;
	private int yMargin;
	private int doorWidth;
	
	private int difficulty;
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Integer> doors = new ArrayList<>();

	/**
	 * Instancie l'objet Room selon sa difficulté
	 * 
	 * 1 - 4 : salle avec des ennemies (nombre et niveau des ennemies)
	 * 0 : salle de départ
	 * -1 : salle spéciale //TODO à définir
	 * @param difficulty
	 * @param doors 
	 * 	- 0 haut
	 * 	- 1 bas
	 * 	- 2 gauche
	 * 	- 3 bas
	 */
	public Room(World world, int difficulty, ArrayList<Integer> doors) {
		this.difficulty = difficulty;
		this.doors = doors;
		this.player = world.getPlayer();
		
		this.worldWidth = world.getWidth();
		this.worldHeight = world.getHeight();
		
		this.xMargin = 64 * worldWidth / 1920;
		this.yMargin = 64 * worldHeight / 1080;
		
		this.doorWidth = 148 * worldWidth / 1920;
		
		init();
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		for (Enemy enemy : enemies) {
			enemy.update(container, game, delta);
		}
		checkPlayerPos();
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fond, 0, 0, worldWidth, worldHeight, 0, 0, fond.getWidth(), fond.getHeight());
		
		for (Enemy enemy : enemies) {
			enemy.render(container, game, context);
		}
	}
	
	private void checkPlayerPos() {
		float pos[] = player.getPos();
		
		if (doors.contains(0) && pos[1] <= yMargin && pos[0]>worldWidth/2-doorWidth/2 && pos[0]<worldWidth/2+doorWidth/2 ) {
			//TODO
		}
		
		if (doors.contains(1) && pos[1] >= worldHeight-yMargin && pos[0]>worldWidth/2-doorWidth/2 && pos[0]<worldWidth/2+doorWidth/2 ) {
			//TODO
		}
		if (doors.contains(2) && pos[0] <= xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			//TODO
		}
		if (doors.contains(2) && pos[0] >= worldWidth-xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			//TODO
		}
		
		if (pos[0] < xMargin || pos[0] > worldWidth-xMargin || pos[1] < yMargin || pos[1] > worldHeight-yMargin) {
			// TODO en dehors de l'écran
		}
		
	}
	
	/**
	 * Fonction initialisant la salle selon sa difficulté :
	 *  - ennemies
	 *  - décors
	 *  - obstacles
	 */
	private void init() {
		switch (difficulty) {
		case 0:
			
		}
	}
	
}
