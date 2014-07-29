package greenpumpkin.artemis.systems;

import java.util.HashMap;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.entities.LightFactory;
import box2dLight.Light;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;

////////////////////////////////////////////////////////////
//This is the Artemis-based System for rendering lights.////
//This instantiates, renders, and destroys lights.//////////
////////////////////////////////////////////////////////////

public class LightS extends IntervalEntitySystem {
	@Mapper ComponentMapper<LightC> lightMap;
	@Mapper ComponentMapper<PositionC> positionMap;

	HashMap<Integer, Light> lightIndex = new HashMap<Integer, Light>();

	@SuppressWarnings("unchecked")
	public LightS() {
		super(Aspect.getAspectForAll(LightC.class, PositionC.class), 1/60.0f);
	}

	protected void initialize(){
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(0.0f, 0.0f, 0.6f, 1.0f),  AnimusWorld.lightDistance*4, 6f, 1f).addToWorld();
		LightFactory.createPoint(world, AnimusWorld.rayHandler,  AnimusWorld.numRays, new Color(1.0f, 1.0f, 0.8f, 1.0f),  AnimusWorld.lightDistance*2, 35f, 11f).addToWorld();
		//the real list of lights will be created with a for loop where the numbers come from a JSON file, in a different system.
	}

	@Override
	protected void inserted(Entity e) {
		LightC newLight = lightMap.get(e);
		lightIndex.put(e.getId(), newLight.light);
	}
	
	@Override
	protected void removed(Entity e) {
		if (lightIndex.containsKey(e.getId())) {
			lightIndex.get(e.getId()).remove();
		}
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0, s = entities.size(); s > i; i++) {
			change(entities.get(i));
		}
		 AnimusWorld.rayHandler.setCombinedMatrix(AnimusWorld.camera.combined);
		 AnimusWorld.rayHandler.render();
	}

	private void change(Entity e) {
		LightC newLight = lightMap.get(e);
		PositionC newPos = positionMap.get(e);
		newLight.light.setPosition(newPos.x, newPos.y);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
