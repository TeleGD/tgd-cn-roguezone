import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

import app.AppGame;

public final class Main {

	public static final void main (String [] arguments) {
		String title = "Phoenix-truc";
		int width = 1280;
		int height = 720;
		boolean fullscreen = false;
		String request = "Voulez-vous jouer en plein écran ?";
		Object [] options = {
			"Oui",
			"Non"
		};
		int returnValue = JOptionPane.showOptionDialog (
			null,
			request,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options [0]
		);
		if (returnValue != -1) {
			if (returnValue == 0) {
				DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ().getDisplayMode ();
				width = display.getWidth ();
				height = display.getHeight ();
				fullscreen = true;
			}
			new AppGame (title, width, height, fullscreen) {

				@Override
				public void init () {
					this.addState (new pages.Welcome (AppGame.PAGES_WELCOME));
					this.addState (new pages.Games (AppGame.PAGES_GAMES));
					this.addState (new pages.Players (AppGame.PAGES_PLAYERS));
					this.addState (new pages.Pause (AppGame.PAGES_PAUSE));
					this.addState (new games.phoenix.World (AppGame.GAMES_PHOENIX_WORLD));
					this.addState (new games.zone.World (AppGame.GAMES_ZONE_WORLD));
				}

			};
		}
	}

}
