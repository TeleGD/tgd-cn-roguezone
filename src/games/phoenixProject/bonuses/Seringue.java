package games.phoenixProject.bonuses;

import app.AppLoader;
import games.phoenixProject.Bonus;
import games.phoenixProject.Player;

import games.phoenixProject.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.state.StateBasedGame;

public class Seringue extends Bonus {

	private boolean activated, deleted;

	static{
		sprite = AppLoader.loadPicture(World.IMAGES + "./bonuses/Seringue.png");
	}

	public Seringue(int caseX, int caseY){
		super (caseX, caseY);
		this.activated = false;
		this.deleted = false;
	}

	public void activate (Player player) {
		if (!isActivated ()) {
			this.activated = true;
			player.addLife (1);
			this.deleted = true;
		}
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
	}

	public boolean isActivated () {
		return this.activated;
	}

	public boolean isDeleted () {
		return this.deleted;
	}

}
