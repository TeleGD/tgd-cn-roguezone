package games.phoenix.enemies;

import java.io.File;

import org.newdawn.slick.SlickException;
import app.AppLoader;
import games.phoenix.Player;
import games.phoenix.World;

public class EnemyTest extends Enemy{
	
	public EnemyTest(int x, int y) throws SlickException
	{
		super(0, "Test", x, y, EnemyColor.PURPLE);
	}
	
	/*
	 * Fonction qui doit disparaitre à terme: c'est pour faire des test à l'arrache sur le
	 * bon fonctionnement de l'ennemis
	 */
	public void init(Enemy enemy, Player player) 
	{
		setBehavior(new EnemyBehavior(enemy, player));
		getBehavior().setPlayerInfluence(EnemyBehavior.interacting.COMING);
	}
}
