package games.phoenix.map;

import games.phoenix.World;

public class Map {

	private Room[][] map;
	private int size;
	private World world;
	
	public Map(int size, World world) {
		
		map = new Room[size][size];
		this.size = size;
		
		
	}
	
	
	
	private void generate() {
		
		boolean treasure = false;
		boolean boss = false;
		
		// Génération de 3 chemins principaux
		for (int i=0; i<3 ; i++) {
			int X = size/2;
			int Y = size/2;
			
			for (int j=0; j<0.8*size; j++) {
				
				if (map[X][Y]==null) {
					map[X][Y] = new Room(world,(int) (Math.random()));
				}
				
				
			}
			
		}
		
	}
	
}
