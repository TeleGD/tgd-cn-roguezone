package games.phoenix.map;

import java.util.Random;

import games.phoenix.World;

public class Map {

	private Room[][] map;
	private int size;
	private World world;
	
	public Map(int size, World world) {
		this.world = world;
		map = new Room[size][size];
		this.size = size;
		
		generate();
		createDoors();
		printMap();
	}
	
	private void printMap() {
		for (int i=0 ; i<map.length ; i++) {
			for (int j = 0 ; j<map[0].length ; j++) {
				if (map[i][j] != null) {
					System.out.print(map[i][j].getDifficulty()<0?"":" ");
					System.out.print(map[i][j].getDifficulty());
					System.out.print(" ");
				} else {
					System.out.print("   ");
				}
		    }
		    System.out.println();
		}
	}

	private void generate() {
		Random r = new Random();
		int rand = 0;
		
		// Génération de 3 chemins principaux
		for (int i=0; i<3 ; i++) {
			int X = size/2;
			int Y = size/2;
			
			if (map[X][Y]==null) {
				map[X][Y] = new Room(world,0,X,Y);
			}
	
			for (int j=0; j<(int)(0.8*size); j++) {
				// Génération de la salle dont la difficulté dépend de la distance au centre
				if (map[X][Y]==null) {
					map[X][Y] = new Room(world,(int)(j/(0.2*size)+1),X,Y);
				}
				
				// Calcul de la position de la salle suivante (attention aux bords de la matrice)
				if (X==0 && Y==0) rand = r.nextInt(2)==0?1:3;
				else if (X==size-1 && Y==0) rand = r.nextInt(2)+1;
				else if (X==0 && Y==size-1) rand = r.nextInt(2)*3;
				else if (X==size-1 && Y==size-1) rand = r.nextInt(2)*2;
				else if (X==0) rand = r.nextInt(3)+1;
				else if (X==size-1) rand = (new int[] {0,2,3})[r.nextInt(3)];
				else if (Y==0) rand = (new int[] {0,1,3})[r.nextInt(3)];
				else if (Y==size-1) rand = r.nextInt(3);
				else rand = r.nextInt(4);
				
				if (rand==0) Y-=1;
				if (rand==1) Y+=1;
				if (rand==2) X-=1;
				if (rand==3) X+=1;
			}
			map[X][Y] = new Room(world, -i-1,X,Y);
		}
	}
	
	private void createDoors() {
		for (int i=0 ; i<map.length ; i++) {
			for (int j = 0 ; j<map[0].length ; j++) {
				if (map[i][j] != null) {
					if (i>0 && map[i-1][j] != null) map[i][j].addDoor(0);
					if (i<size-1 && map[i+1][j] != null) map[i][j].addDoor(1);
					if (j>0 && map[i][j-1] != null) map[i][j].addDoor(2);
					if (j<size-1 && map[i][j+1] != null) map[i][j].addDoor(3);
				}
		    }
		}
	}
	
}
