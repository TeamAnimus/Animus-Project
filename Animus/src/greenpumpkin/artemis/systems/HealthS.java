package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.HealthC;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////

public class HealthS extends EntityProcessingSystem {
	@Mapper ComponentMapper <HealthC> healthMap;
	
	public HealthS() {
		super(Aspect.getAspectForAll(HealthC.class));
	}

	@Override
	protected void process(Entity e) {
		HealthC health = healthMap.get(e);
	} 

}
