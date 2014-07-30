package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.PositionC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class LightCycleS extends IntervalEntityProcessingSystem {
	@Mapper ComponentMapper<LightC> lightMap;

	@SuppressWarnings("unchecked")
	public LightCycleS() {
		super(Aspect.getAspectForAll(LightC.class, PositionC.class), 1/60f);
	}
	
	@Override
	protected void process(Entity e) {
		LightC newLight = lightMap.get(e);
		if( (newLight.time/2) > newLight.currTime ) {
			newLight.light.setDistance(newLight.light.getDistance() + (5 / (60 / newLight.time)));
			newLight.currTime+=1/60f;
		}
		else {
			newLight.light.setDistance(newLight.light.getDistance() - (5 / 60 / newLight.time));
			newLight.currTime+=1/60f;
		}
		if( newLight.time == newLight.currTime ){
			newLight.currTime=0;
		}
	}
}
