package games.phoenixProject.bonuses;

import app.AppLoader;
import games.phoenixProject.Bonus;
import games.phoenixProject.Player;
import games.phoenixProject.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Coke extends Bonus {

	private boolean activated, deleted;
	private int duration;
	private Player player;

	static {
		sprite = AppLoader.loadPicture(World.IMAGES + "./bonuses/Coke.png");
	}

	public Coke(int caseX, int caseY){
		super (caseX, caseY);
		this.activated = false;
		this.deleted = false;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		duration -= delta;
		if (activated && (duration <= 0)) {
			activated = false;
			this.desactivate ();
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
	}

	public void activate (Player player) {
		if (!isActivated ()) {
			this.activated = true;
			player.setSpeed (player.getSpeed () * 10f);
			this.duration = 7000;
			this.player = player;
		}
	}

	public void desactivate () {
		this.deleted = true;
		this.player.setSpeed (player.getSpeed () * 0.1f);
	}


	public boolean isActivated () {
		return this.activated;
	}

	public boolean isDeleted () {
		return this.deleted;
	}

}
