package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.entities.LightFactory;
import box2dLight.RayHandler;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LightS extends EntitySystem {
	@Mapper ComponentMapper<LightC> lightMap;
	private OrthographicCamera camera;
	public final int numRays = 16; //how many rays are emitted for shadow casting
	public final float lightDistance = 12f; // distance light goes
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
	protected void processEntities(ImmutableBag<Entity> entities) {
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.render();
	}
	
	void initRayHandler(){
		rayHandler = new RayHandler(null);
		rayHandler.setCombinedMatrix(camera.combined);
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f, 1f);
		rayHandler.setCulling(true);
		rayHandler.setBlurNum(1);
		rayHandler.setShadows(true); 
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
