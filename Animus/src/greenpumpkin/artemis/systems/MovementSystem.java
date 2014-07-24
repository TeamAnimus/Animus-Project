package greenpumpkin.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

////////////////////////////////////////////////////////////
//This is the Artemis-based System for Movement. This///////
//is used for anything on the map that physically moves.////
////////////////////////////////////////////////////////////

public class MovementSystem extends EntityProcessingSystem {

	@SuppressWarnings({"unchecked" })
	public MovementSystem() {
		super(Aspect.getAspectForAll(null, null));
	}

	@Override
	protected void process(Entity e) {
		
	}

}
