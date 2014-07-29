package greenpumpkin.screens;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.entities.LightFactory;
import greenpumpkin.artemis.systems.LightS;
import greenpumpkin.artemis.systems.TiledS;
import greenpumpkin.game.*;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

////////////////////////////////////////////////////////////
//This is the Artemis test screen. It is useful for/////////
//learning how Artemis works.///////////////////////////////
////////////////////////////////////////////////////////////

public class TestWorld implements Screen {
	private Stage stage = new Stage();
	
	Music caveTheme = Gdx.audio.newMusic(Gdx.files.internal("music/caveTheme.mp3"));
	
	private AnimusWorld world;
	
	@Override
	public void show() {
		world = new AnimusWorld();
		//camera creation
		
		AnimusWorld.initCamera();
		AnimusWorld.initRayHandler();
		
		world.setManager(new GroupManager());
		world.setSystem(new TiledS());
		world.setSystem(new LightS());
		world.initialize();

		//These lights will not be here. They are just for a test.
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(0.0f, 0.0f, 0.6f, 1.0f),  AnimusWorld.lightDistance*4, 6f, 1f).addToWorld();
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(1.0f, 1.0f, 0.8f, 1.0f),  AnimusWorld.lightDistance*2, 35f, 11f).addToWorld();
		//the real list of lights will be created with a for loop where the numbers come from a JSON file, in a different system.
		
		caveTheme.play();
		caveTheme.setLooping(true);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveCamera(delta);
		
		AnimusWorld.camera.update();
		
		world.setDelta(delta);
		world.process();
	}

	private void moveCamera(float delta) {
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().setCamera(new VirtualResolution(Animus.WIDTH, Animus.HEIGHT));
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {		
	}

	@Override
	public void dispose() {
		stage.dispose();
		caveTheme.dispose();
	}
}
