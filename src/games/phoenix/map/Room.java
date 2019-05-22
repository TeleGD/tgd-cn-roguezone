package games.phoenix.map;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.Projectile;
import games.phoenix.World;
import games.phoenix.enemies.Enemy;

/**
 * 
 * @author amos
 *
 * Object de chaque salle
 */
public class Room {
	
	/*
	 * Numéro de l'image selon les portes : décomposition binaire
	 * [3 2 1 0]
	 * 0 : {}
	 * 1 : {0}
	 * 2 : {1}
	 * 3 : {0,1}
	 * 4 : {2}
	 * 5 : {0,2}
	 * 6 : {1,2}
	 * 7 : {0,1,2}
	 * 8 : {3}
	 * 9 : {0,3}
	 * 10 : {1,3}
	 * 11 : {0,1,3}
	 * 12 : {2,3}
	 * 13 : {0,2,3}
	 * 14 : {1,2,3}
	 * 15 : {0,1,2,3}
	 */
	
	private Image fond = AppLoader.loadPicture(World.IMAGES+File.separator+"rooms"+File.separator+"room_15.png");
	
	private int worldWidth;
	private int worldHeight;
	
	private int line;
	private int column;
	
	private int xMargin;
	private int yMargin;
	private int doorWidth;
	
	private int difficulty;
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Integer> doors = new ArrayList<>();
	private ArrayList<Projectile> projectiles = new ArrayList<>();

	/**
	 * Instancie l'objet Room selon sa difficulté
	 * 
	 * @param difficulty  
	 * 1 - 4 : salle avec des ennemies (nombre et niveau des ennemies)  
	 * 0 : salle de départ  
	 * -1 : salle d'item  
	 * -2 : salle de boss  
	 * -3 : sortie  
	 * @param doors  
	 * 	- 0 haut  
	 * 	- 1 bas  
	 * 	- 2 gauche  
	 * 	- 3 bas  
	 */
	public Room(World world, int difficulty, int line, int column) {
		this.difficulty = difficulty;
		this.player = world.getPlayer();
		
		this.doors = new ArrayList<>();
		
		this.worldWidth = world.getWidth();
		this.worldHeight = world.getHeight();
		
		this.xMargin = 104 * worldWidth / 1920;
		this.yMargin = 104 * worldHeight / 1080;
		
		this.doorWidth = 148 * worldWidth / 1920;
		
		this.line = line;
		this.column = column;
		
		init();
	}
	
	public void addDoor(int door) {
		this.doors.add(door);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		player.update(container, game, delta);
		checkPlayerPos();
		for (Enemy enemy : enemies) {
			enemy.update(container, game, delta);
		}
		for (Projectile projectile : projectiles) {
			projectile.update(container, game, delta);
		}

		//TODO collisions
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fond, 0, 0, worldWidth, worldHeight, 0, 0, fond.getWidth(), fond.getHeight());
		context.drawString(difficulty+"", worldWidth/2, worldHeight/2);
		for (Enemy enemy : enemies) {
			enemy.render(container, game, context);
		}
		player.render(container, game, context);
		for (Projectile projectile : projectiles) {
			projectile.render(container, game, context);
		}
	}
	
	private void checkPlayerPos() {
		float pos[] = player.getPos();
		boolean inside = true;
		
		if (doors.contains(0) && pos[1] <= yMargin && pos[0]>worldWidth/2-doorWidth/2 && pos[0]<worldWidth/2+doorWidth/2 ) {
			player.changeRoom(line-1,column,0);
		}
		if (doors.contains(1) && pos[1] >= worldHeight-yMargin && pos[0]>worldWidth/2-doorWidth/2 && pos[0]<worldWidth/2+doorWidth/2 ) {
			player.changeRoom(line+1,column,1);
		}
		if (doors.contains(2) && pos[0] <= xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			player.changeRoom(line,column-1,2);
		}
		if (doors.contains(2) && pos[0] >= worldWidth-xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			player.changeRoom(line,column+1,3);
		}
		
		if (pos[0] < xMargin || pos[0] > worldWidth-xMargin || pos[1] < yMargin || pos[1] > worldHeight-yMargin) {
			inside = false;
		}
		player.confirmMove(inside);
		
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
			
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case -1:
			
			break;
		case -2:
			
			break;
		case -3:
			
			break;
		default:
			
		}
	}
	
	public void addProjectile(Projectile p){
		this.projectiles.add(p);
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
