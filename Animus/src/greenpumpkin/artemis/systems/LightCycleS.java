package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.LightCycleC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class LightCycleS extends IntervalEntityProcessingSystem {
	@Mapper
	ComponentMapper<LightC> lightMap;
	@Mapper
	ComponentMapper<LightCycleC> cycleMap;
	
	@SuppressWarnings("unchecked")
	public LightCycleS() {
		super(Aspect.getAspectForAll(LightC.class, LightCycleC.class), 1 / 30f);
	}
	
	@Override
	protected void process(Entity e) {
		LightC newLight = lightMap.get(e);
		LightCycleC cycle = cycleMap.get(e);
		
		
		if ((cycle.time / 8) > cycle.currTime) {
			increaseSlow(newLight, cycle);
		}
		else if((cycle.time / 8*3) > cycle.currTime){
			increaseFast(newLight, cycle);
		}
		else if((cycle.time / 2) > cycle.currTime){
			increaseSlow(newLight, cycle);
		}
		else if((cycle.time / 8*5) > cycle.currTime){
			decreaseSlow(newLight, cycle);
		}
		else if((cycle.time / 8*7) > cycle.currTime){
			decreaseFast(newLight, cycle);
		}
		else if (cycle.time > cycle.currTime) {
			decreaseSlow(newLight, cycle);
		}
		else {
			System.out.println("difference: " + (newLight.light.getDistance() - (cycle.maxDist-cycle.size)));
			cycle.currTime = 0;
			newLight.light.setDistance(cycle.maxDist-cycle.size);
		}
	}
	
	protected void increaseFast(LightC newLight, LightCycleC cycle) {
		newLight.light.setDistance(newLight.light.getDistance() + (cycle.size*2f / 30.0f / cycle.time));
		cycle.currTime += 1 / 30f;
	}
	protected void increaseSlow(LightC newLight, LightCycleC cycle) {
		newLight.light.setDistance(newLight.light.getDistance() + (cycle.size/2f / 30.0f / cycle.time));
		cycle.currTime += 1 / 30f;
	}
	protected void decreaseFast(LightC newLight, LightCycleC cycle) {
		newLight.light.setDistance(newLight.light.getDistance() - (cycle.size*2f / 30.0f / cycle.time));
		cycle.currTime += 1 / 30f;
	}
	protected void decreaseSlow(LightC newLight, LightCycleC cycle) {
		newLight.light.setDistance(newLight.light.getDistance() - (cycle.size/2f / 30.0f / cycle.time));
		cycle.currTime += 1 / 30f;
	}
}
