package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.LightCycleC;
import greenpumpkin.artemis.components.PositionC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class LightCycleS extends IntervalEntityProcessingSystem {//Eric
	@Mapper//Eric
	ComponentMapper<LightC> lightMap;//Eric
	@Mapper//Eric
	ComponentMapper<LightCycleC> cycleMap;//Eric
	//Eric
	@SuppressWarnings("unchecked")//Eric
	public LightCycleS() {//Eric
		super(Aspect.getAspectForAll(LightC.class, LightCycleC.class), 1 / 60f);//Eric
	}//Eric
	//Eric
	@Override//Eric
	protected void process(Entity e) {//Eric
		LightC newLight = lightMap.get(e);//Eric
		LightCycleC cycle = cycleMap.get(e);
		//Eric
		//if((1/2f) > cycle.currTime) {cycle.currTime += 1 / 60f;}//Eric
		if ((cycle.time / 2) > cycle.currTime) {//Eric
			newLight.light.setDistance(newLight.light.getDistance() + (cycle.size / 60.0f / cycle.time));//Eric
			cycle.currTime += 1 / 60f;//Eric
		}
		//else if((cycle.time / 2f + (1/4f)) > cycle.currTime){cycle.currTime += 1 / 60f;}
		//Eric
		else {//Eric
			newLight.light.setDistance(newLight.light.getDistance() - (cycle.size / 60.0f / cycle.time));//Eric
			cycle.currTime += 1 / 60f;//Eric
		}//Eric
		//Eric
		if (cycle.time < cycle.currTime) {//Eric
			cycle.currTime = 0;//Eric
			newLight.light.setDistance(cycle.maxDist-cycle.size);
		}//Eric
		//Eric
	}//Eric
}//Plot Twist: Daleb
