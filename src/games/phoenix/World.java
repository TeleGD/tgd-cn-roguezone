package games.phoenix;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppInput;
import app.AppPlayer;
import app.AppWorld;
import games.phoenix.enemies.EnemyBehavior;
import games.phoenix.enemies.EnemyTest;

public class World extends AppWorld {

	public static String IMAGES = "/images/phoenix";

	private Player player;
	private EnemyTest enemy;
	private String log;
	private int height;
	private int width;

	public World (int ID) {
		super (ID);
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
		super.init (container, game);

		this.height = container.getHeight();
		this.width = container.getWidth();
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size ();
		this.player = new Player(appGame.appPlayers.get(0), this);
		try {
			this.enemy = new EnemyTest();
			this.enemy.setBehavior(new EnemyBehavior(enemy, player));
			this.enemy.getBehavior().setPlayerInfluence(EnemyBehavior.interacting.STRAIGHT);
		}
		catch (Throwable t)
		{
			
		}
		// for (int i = 0; i < n; i++) {
		// 	this.player = new Player (appGame.appPlayers.get (i), this);
		// }
		this.log = "";
		System.out.println ("PLAY");
	}

	@Override
	public void stop (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		System.out.println ("STOP");
	}

	@Override
	public void resume (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
		System.out.println ("RESUME");
	}

	@Override
	public void pause (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		System.out.println ("PAUSE");
	}

	@Override
	public void poll (GameContainer container, StateBasedGame game, Input user) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.poll (container, game, user);
		AppInput input = (AppInput) user;
		this.log = "";
		
		String name = player.getName ();
		int controllerID = player.getControllerID ();
		for (int i = 0, l = input.getControlCount (controllerID); i < l; i++) {
			if (input.isControlPressed (1 << i, controllerID)) {
				this.log += "(" + name + ").isControlPressed: " + i + "\n";
			}
		}
		for (int i = 0, l = input.getButtonCount (controllerID); i < l; i++) {
			if (input.isButtonPressed (1 << i, controllerID)) {
				this.log += "(" + name + ").isButtonPressed: " + i + "\n";
			}
		}
		for (int i = 0, l = input.getAxisCount (controllerID); i < l; i++) {
			float j = input.getAxisValue (i, controllerID);
			if (j <= -.5f || j >= .5f) {
				this.log += "(" + name + ").getAxisValue: " + i + " -> " + j + "\n";
			}
		}
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.update (container, game, delta);
		
		player.update(container, game, delta);
		enemy.update(container, game, delta);
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.render (container, game, context);
		if (this.log.length () != 0) {
			System.out.print (this.log);
		}

		player.render(container, game, context);
		enemy.render(container, game, context);
	}

	/**
	 * Accesseur de la hauteur en pixel de l'écran
	 * @return
	 */
	public int getHeight(){
		return this.height;
	}

	/**
	 * Accesseur de la largeur en pixel de l'écran
	 */
	public int getWidth(){
		return this.width;
	}
}
