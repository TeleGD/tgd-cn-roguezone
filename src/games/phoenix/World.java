package games.phoenix;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppInput;
import app.AppWorld;
import games.phoenix.enemies.EnemyBlue;
import games.phoenix.enemies.EnemyBoss;
import games.phoenix.enemies.EnemyGreen;
import games.phoenix.enemies.EnemyPurple;
import games.phoenix.enemies.EnemyRed;
import games.phoenix.enemies.EnemyTest;
import games.phoenix.enemies.EnemyYellow;

public class World extends AppWorld {

	public static String IMAGES = "/images/phoenix";

	private Player player;
	private EnemyBlue enemyb;
	private EnemyYellow enemyj;
	private EnemyRed enemyr;
	private EnemyGreen enemyg;
	private EnemyPurple enemyv;
	private EnemyBoss enemyboss;
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
			this.enemyb = new EnemyBlue(0,0);
			this.enemyb.init(enemyb,player);
			this.enemyr = new EnemyRed(0,0);
			this.enemyr.init(enemyr,player);
			this.enemyj = new EnemyYellow(0,0);
			this.enemyj.init(enemyj,player);
			this.enemyg = new EnemyGreen(0,0);
			this.enemyg.init(enemyg,player);
			this.enemyv = new EnemyPurple(0,0);
			this.enemyv.init(enemyv,player);
			this.enemyboss = new EnemyBoss(0,0);
			this.enemyboss.init(enemyboss,player);
			
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
		enemyb.update(container, game, delta);
		enemyr.update(container, game, delta);
		enemyj.update(container, game, delta);
		enemyg.update(container, game, delta);
		enemyv.update(container, game, delta);
		enemyboss.update(container, game, delta);
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.render (container, game, context);
		if (this.log.length () != 0) {
			System.out.print (this.log);
		}

		player.render(container, game, context);
		enemyb.render(container, game, context);
		enemyr.render(container, game, context);
		enemyj.render(container, game, context);
		enemyg.render(container, game, context);
		enemyv.render(container, game, context);
		enemyboss.render(container, game, context);
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

	public Player getPlayer() {
		return player;
	}
}
