package games.phoenix.bonuses;

import app.AppLoader;
import games.phoenix.Bonus;
import games.phoenix.Player;
import games.phoenix.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


public class AntiCorps extends Bonus {

    private boolean activated, deleted;

    static{
        sprite = AppLoader.loadPicture(World.IMAGES + "./phoenix/bonus/Anticorps.png");
    }

    public AntiCorps(int caseX, int caseY){
        super (caseX, caseY);
        this.activated = false;
        this.deleted = false;
    }

    public void render (GameContainer container, StateBasedGame game, Graphics context) {
        super.render (container, game, context);
    }

    public void activate (Player player) {
        if (!isActivated ()) {
            this.activated = true;
            player.addShield(1);
            this.deleted = true;

        }
    }

    public void update (GameContainer container, StateBasedGame game, int delta) {}

    public boolean isActivated () {
        return this.activated;
    }

    public boolean isDeleted () {
        return this.deleted;
    }

}
