package greenpumpkin.artemis.systems;

import java.util.HashMap;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.PositionC;
import box2dLight.Light;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntitySystem;
import com.artemis.utils.ImmutableBag;

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
		AnimusWorld.rayHandler.updateAndRender();
	}

	private void change(Entity e) {
		LightC newLight = lightMap.get(e);
		PositionC newPos = positionMap.get(e);
		newPos.x=(float) (Math.random()*30);
		newPos.y=(float) (Math.random()*20);
		newLight.light.setPosition(newPos.x, newPos.y);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
