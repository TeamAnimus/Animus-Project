package greenpumpkin.game;

import greenpumpkin.screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

////////////////////////////////////////////////////////////
//This is the main class.It sets the default settings///////
//for the game and loads the first area.////////////////////
////////////////////////////////////////////////////////////

public class Animus extends Game {
	public static final String TITLE="Game Project";
	public static final int WIDTH=1024,HEIGHT=576;
	public static final int[] fps = new int[]{30,60,120,144};

	@Override
	public void create() {
		setScreen(new TestWorld()); //This defines what screen loads first.
	}

	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Animus";
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.foregroundFPS=fps[1];
		cfg.backgroundFPS=fps[1];
		new LwjglApplication(new Animus(), cfg);
	}
}
