package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.CameraC;
import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.entities.LightFactory;
import greenpumpkin.artemis.entities.TiledMapFactory;
import box2dLight.RayHandler;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public class LightS extends EntityProcessingSystem {
	@Mapper ComponentMapper<LightC> lightMap;
	private OrthographicCamera camera;
	public int numRays = 16; //how many rays are emitted for shadow casting
	public float lightDistance = 12f; // distance light goes
	public World boxWorld; /** our box2D world, controls physics **/
	public RayHandler rayHandler; //the main object of light2d, heavily important

	@SuppressWarnings("unchecked")
	public LightS(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(LightC.class));
		this.camera=camera;
	}
	
	protected void initialize(){
		initRayHandler();
		LightFactory.createPoint(world, rayHandler, numRays, new Color(0.0f, 0.0f, 0.6f, 1.0f), lightDistance*4, 6f, 1f).addToWorld();
		LightFactory.createPoint(world, rayHandler, numRays, new Color(1.0f, 1.0f, 0.8f, 1.0f), lightDistance*2, 35f, 11f).addToWorld();
		//the real list of lights will be created with a for loop where the numbers come from a JSON file.
	}
	
	@Override
	protected void process(Entity e) {
		//LightC light = lightMap.get(e);
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.render();
	}
	void initRayHandler(){
		rayHandler = new RayHandler(boxWorld);
		rayHandler.setCombinedMatrix(camera.combined);
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f, 1f);
		rayHandler.setCulling(true);
		rayHandler.setBlurNum(1);
		rayHandler.setShadows(true); 
	}
}
