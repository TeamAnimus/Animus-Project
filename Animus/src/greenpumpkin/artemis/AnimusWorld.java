package greenpumpkin.artemis;

import com.artemis.World;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import box2dLight.RayHandler;

public class AnimusWorld extends World {
	private static TiledMap foregroundMap;
	private static TiledMap backgroundMap;
	private static float mapSize = 1/16f;
	public static final int numRays = 16; //how many rays are emitted for shadow casting
	public static final float lightDistance = 12f; // distance light goes
	public static RayHandler rayHandler; //the main object of light2d, heavily important
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static OrthogonalTiledMapRenderer frontRenderer;
	public static OrthogonalTiledMapRenderer backRenderer;
	public static TiledMapTileLayer collisionLayer;
	
	public static void init() {
		initCamera();
		initRayHandler();
		initBatch();	
	}
	
	private static void initCamera() {
		camera = new OrthographicCamera(32,18);
		camera.position.set(16, 9, 0);
		camera.update(true);
	}
	
	private static void initRayHandler() {
		RayHandler.useDiffuseLight(true);
		rayHandler = new RayHandler(null);
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.setAmbientLight(0.15f, 0.15f, 0.15f, 1f);
		rayHandler.setCulling(true);
		rayHandler.setBlurNum(1);
		rayHandler.setShadows(true);
	}
	
	private static void initBatch() {
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}
	
	public static void setMaps(String frontMap, String backMap) {
		setForegroundMap(frontMap);
		setBackgroundMap(backMap);
	}
	
	public static void setForegroundMap(String mapFile) {
		foregroundMap = new TmxMapLoader().load(mapFile);
		frontRenderer = new OrthogonalTiledMapRenderer(AnimusWorld.foregroundMap, mapSize);
		frontRenderer.setView(camera);
		collisionLayer = (TiledMapTileLayer) foregroundMap.getLayers().get(1);
	}
	public static void setBackgroundMap(String mapFile) {
		backgroundMap = new TmxMapLoader().load(mapFile);
		backRenderer = new OrthogonalTiledMapRenderer(AnimusWorld.backgroundMap, mapSize);
		backRenderer.setView(camera);
	}
	
	public static void flushRayHandler() {
		rayHandler.removeAll();
	}
}
