package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.LightC;
import greenpumpkin.artemis.components.PositionC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class LightCycleS extends IntervalEntityProcessingSystem {//Eric
	@Mapper//Eric
	ComponentMapper<LightC> lightMap;//Eric
	//Eric
	@SuppressWarnings("unchecked")//Eric
	public LightCycleS() {//Eric
		super(Aspect.getAspectForAll(LightC.class, PositionC.class), 1 / 60f);//Eric
	}//Eric
	//Eric
	@Override//Eric
	protected void process(Entity e) {//Eric
		LightC newLight = lightMap.get(e);//Eric
		//Eric
		if (newLight.time > 0) {//Eric
			//Eric
			if( (1/3f)> newLight.currTime ) {newLight.currTime += 1 / 60f;}//Eric
			else if ((newLight.time / 2) > newLight.currTime) {//Eric
				newLight.light.setDistance(newLight.light.getDistance() + (newLight.size / 60.0f / newLight.time));//Eric
				newLight.currTime += 1 / 60f;//Eric
			}
			else if((newLight.time / 2f + (1/4f)) > newLight.currTime){newLight.currTime += 1 / 60f;}
			//Eric
			else {//Eric
				newLight.light.setDistance(newLight.light.getDistance() - (newLight.size / 60.0f / newLight.time));//Eric
				newLight.currTime += 1 / 60f;//Eric
			}//Eric
			//Eric
			if (newLight.time < newLight.currTime) {//Eric
				newLight.currTime = 0;//Eric
				newLight.light.setDistance(newLight.maxDist-newLight.size);
			}//Eric
			//Eric
		}//Eric
	}//Eric
}//Plot Twist: Daleb
