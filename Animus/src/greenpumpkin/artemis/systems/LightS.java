package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.entities.LightFactory;
import greenpumpkin.artemis.units.AnimusWorld;
import box2dLight.RayHandler;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;

public class LightS extends EntitySystem {
	@Mapper ComponentMapper<LightC> lightMap;

	@SuppressWarnings("unchecked")
	public LightS() {
		super(Aspect.getAspectForAll(LightC.class));
	}
	
	protected void initialize(){
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(0.0f, 0.0f, 0.6f, 1.0f),  AnimusWorld.lightDistance*4, 6f, 1f).addToWorld();
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(1.0f, 1.0f, 0.8f, 1.0f),  AnimusWorld.lightDistance*2, 35f, 11f).addToWorld();
		//the real list of lights will be created with a for loop where the numbers come from a JSON file.
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		 AnimusWorld.rayHandler.setCombinedMatrix(AnimusWorld.camera.combined);
		 AnimusWorld.rayHandler.render();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
