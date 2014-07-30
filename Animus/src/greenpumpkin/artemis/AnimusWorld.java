package greenpumpkin.artemis;

import com.artemis.World;
import com.badlogic.gdx.graphics.OrthographicCamera;

import box2dLight.RayHandler;

public class AnimusWorld extends World {
	public static final int numRays = 32; //how many rays are emitted for shadow casting
	public static final float lightDistance = 12f; // distance light goes
	public static RayHandler rayHandler; //the main object of light2d, heavily important
	public static OrthographicCamera camera;
	
	public static void initRayHandler() {
		rayHandler = new RayHandler(null);
		rayHandler.setCombinedMatrix(camera.combined);
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 1f);
		rayHandler.setCulling(true);
		rayHandler.setBlurNum(1);
		rayHandler.setShadows(true); 
	}
	
	public static void initCamera() {
		camera = new OrthographicCamera(32,18);
		camera.position.set(16, 9, 0);
		camera.update(true);
	}

}
