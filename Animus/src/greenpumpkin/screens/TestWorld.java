package greenpumpkin.screens;

import greenpumpkin.artemis.systems.TiledS;
import greenpumpkin.game.*;
import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

////////////////////////////////////////////////////////////
//This is the Artemis test screen. It is useful for/////////
//learning how Artemis works.///////////////////////////////
////////////////////////////////////////////////////////////

public class TestWorld implements Screen {
	private Stage stage = new Stage();
	private OrthographicCamera lightCamera;
	
	Music caveTheme = Gdx.audio.newMusic(Gdx.files.internal("music/caveTheme.mp3"));
	
	private com.artemis.World world;
	
	@Override
	public void show() {
		world = new com.artemis.World();
		//camera creation
		lightCamera = new OrthographicCamera(Animus.WIDTH, Animus.HEIGHT);
		lightCamera.position.set(10, 25, 0);
		lightCamera.update(true);
		
		caveTheme.play();
		caveTheme.setLooping(true); 
		
		world.setManager(new GroupManager());
		world.setSystem(new TiledS(lightCamera));
		world.initialize();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		moveCamera(delta);
		
		lightCamera.update();
		
		world.setDelta(delta);
		System.out.println("before process");
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
