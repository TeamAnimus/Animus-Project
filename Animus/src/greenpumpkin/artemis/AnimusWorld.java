package greenpumpkin.artemis;

import com.artemis.World;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import box2dLight.RayHandler;

public class AnimusWorld extends World {
	public static final int numRays = 32; //how many rays are emitted for shadow casting
	public static final float lightDistance = 12f; // distance light goes
	public static RayHandler rayHandler; //the main object of light2d, heavily important
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	
	public static void initCamera() {
		camera = new OrthographicCamera(32,18);
		camera.position.set(16, 9, 0);
		camera.update(true);
	}
	
	public static void initRayHandler() {
		RayHandler.useDiffuseLight(true);
		rayHandler = new RayHandler(null);
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.setAmbientLight(0.15f, 0.15f, 0.15f, 1f);
		rayHandler.setCulling(true);
		rayHandler.setBlurNum(1);
		rayHandler.setShadows(true); 
	}
	
	public static void initBatch() {
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}
}
