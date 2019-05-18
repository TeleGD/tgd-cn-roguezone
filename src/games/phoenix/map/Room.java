package games.phoenix.map;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;
import games.phoenix.Enemy;
import games.phoenix.World;

/**
 * 
 * @author amos
 *
 * Object de chaque salle
 */
public class Room {
	
	private int difficulty;
	private Image fond = AppLoader.loadPicture(World.IMAGES+File.separator+"rooms"+File.separator+"room_15.png");
	
	private ArrayList<Enemy> enemies = new ArrayList<>();
	
	private ArrayList<Rectangle> doors = new ArrayList<>();

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
		
		init();
		getDoors(doors);
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
	
	private void getDoors(ArrayList<Integer> doors) {
		
	}
	
}
