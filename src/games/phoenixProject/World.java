package games.phoenixProject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppWorld;
import games.phoenixProject.map.Map;
import games.phoenixProject.map.Room;

public class World extends AppWorld {

	public static String IMAGES = "/images/phoenixProject";

	private Map map;
	private Player player;
	private Room activeRoom;

	private int height;
	private int width;
	private int size;

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

		this.size = 10;

		this.player = new Player(appGame.appPlayers.get(0), this);
		map = new Map(10, this);
		map = new Map(size, this);
		setActiveRoom(size/2, size/2);
	}

	@Override
	public void stop (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	@Override
	public void resume (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	@Override
	public void pause (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	@Override
	public void poll (GameContainer container, StateBasedGame game, Input user) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.poll (container, game, user);
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.update (container, game, delta);
		activeRoom.update(container, game, delta);

		/*player.update(container, game, delta);
		enemyb.update(container, game, delta);
		enemyr.update(container, game, delta);
		enemyj.update(container, game, delta);
		enemyg.update(container, game, delta);
		enemyv.update(container, game, delta);
		enemyboss.update(container, game, delta);*/
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.render (container, game, context);
		activeRoom.render(container, game, context);

		/*player.render(container, game, context);
		enemyb.render(container, game, context);
		enemyr.render(container, game, context);
		enemyj.render(container, game, context);
		enemyg.render(container, game, context);
		enemyv.render(container, game, context);
		enemyboss.render(container, game, context);*/
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
		return this.player;
	}

	public void setActiveRoom(int line, int column) {
		this.activeRoom = map.getRoom(line, column);
		player.setRoom(activeRoom);
	}
}
