package games.phoenixProject.map;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;
import games.phoenixProject.Player;
import games.phoenixProject.Projectile;
import games.phoenixProject.World;
import games.phoenixProject.enemies.*;

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

	private Image fond = AppLoader.loadPicture(World.IMAGES+"/rooms/room_15.png");

	public static int worldWidth;
	public static int worldHeight;

	private int line;
	private int column;

	public static int xMargin;
	public static int yMargin;
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
		collisions();
		for (int i=enemies.size()-1; i>=0 ; i--) {
			if (!enemies.get(i).isDead()) enemies.get(i).update(container, game, delta);
			if (enemies.get(i).isDead()) enemies.remove(i);
		}
		for (int i=projectiles.size()-1; i>=0 ; i--) {
			if (!projectiles.get(i).isDestroyed()) projectiles.get(i).update(container, game, delta);
			if (projectiles.get(i).getPos()[0]-projectiles.get(i).getRadius()<xMargin
				|| projectiles.get(i).getPos()[0]+projectiles.get(i).getRadius()>worldWidth-xMargin
				|| projectiles.get(i).getPos()[1]-projectiles.get(i).getRadius()<yMargin
				|| projectiles.get(i).getPos()[1]+projectiles.get(i).getRadius()>worldHeight-yMargin ) {
				projectiles.get(i).destroy();
			}
			if (projectiles.get(i).isDestroyed()) projectiles.remove(i);
		}
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
			projectiles = new ArrayList<>();
		}
		if (doors.contains(1) && pos[1] >= worldHeight-yMargin && pos[0]>worldWidth/2-doorWidth/2 && pos[0]<worldWidth/2+doorWidth/2 ) {
			player.changeRoom(line+1,column,1);
			projectiles = new ArrayList<>();
		}
		if (doors.contains(2) && pos[0] <= xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			player.changeRoom(line,column-1,2);
			projectiles = new ArrayList<>();
		}
		if (doors.contains(3) && pos[0] >= worldWidth-xMargin && pos[1]>worldHeight/2-doorWidth/2 && pos[1]<worldHeight/2+doorWidth/2 ) {
			player.changeRoom(line,column+1,3);
			projectiles = new ArrayList<>();
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
		Random r = new Random();
		int nbEnnemies;
		int color;
		Integer[] pos = new Integer[2];
		ArrayList<Integer[]> possiblePos = new ArrayList<>();

		possiblePos.add(new Integer[] {xMargin+100,yMargin+100});
		possiblePos.add(new Integer[] {worldWidth-xMargin-100,worldHeight-yMargin-100});
		possiblePos.add(new Integer[] {worldWidth-xMargin-100,yMargin+100});
		possiblePos.add(new Integer[] {xMargin+100,worldHeight-yMargin-100});

		switch (difficulty) {
		case 0:

			break;
		case 1:
			// {1,2} ennemies Bleu / Vert
			nbEnnemies = r.nextInt(2)+1;
			for (int i=0; i<nbEnnemies; i++) {
				color = r.nextInt(2);
				pos = possiblePos.remove(r.nextInt(possiblePos.size()));
				enemies.add(color==0?new EnemyBlue(pos[0],pos[1],player):new EnemyGreen(pos[0],pos[1],player));
			}
			break;
		case 2:
			// 2 ennemies Vert / Jaune
			nbEnnemies = 2;
			for (int i=0; i<nbEnnemies; i++) {
				color = r.nextInt(2);
				pos = possiblePos.remove(r.nextInt(possiblePos.size()));
				enemies.add(color==0?new EnemyGreen(pos[0],pos[1],player):new EnemyYellow(pos[0],pos[1],player));
			}
			break;
		case 3:
			// {2,3} ennemies Jaune / Rouge
			nbEnnemies = r.nextInt(2)+2;
			for (int i=0; i<nbEnnemies; i++) {
				color = r.nextInt(2);
				pos = possiblePos.remove(r.nextInt(possiblePos.size()));
				enemies.add(color==0?new EnemyYellow(pos[0],pos[1],player):new EnemyRed(pos[0],pos[1],player));
			}
			break;
		case 4:
			// 3 ennemies Rouge / Violet
			nbEnnemies = 3;
			for (int i=0; i<nbEnnemies; i++) {
				color = r.nextInt(2);
				pos = possiblePos.remove(r.nextInt(possiblePos.size()));
				enemies.add(color==0?new EnemyRed(pos[0],pos[1],player):new EnemyPurple(pos[0],pos[1],player));
			}
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

	public void collisions() {
		for (Enemy e: enemies){
			if(e.getHitbox().intersects(player.getHitbox()) || e.getHitbox().contains(player.getHitbox())) {
				e.attack(player);
			}
			for (Projectile p: projectiles) {
				if(e.getHitbox().intersects(p.getHitbox()) || e.getHitbox().contains(p.getHitbox())) {
					e.setPv(e.getPv()-p.getDamage());
					p.destroy();
				}
			}
		}
	}

	public void addProjectile(Projectile p){
		this.projectiles.add(p);
	}

	public int getDifficulty() {
		return difficulty;
	}
}
