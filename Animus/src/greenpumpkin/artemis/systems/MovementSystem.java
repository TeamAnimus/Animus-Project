package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.Position;
import greenpumpkin.artemis.components.Velocity;
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
	@Mapper ComponentMapper<Position> posMap;
	@Mapper ComponentMapper<Velocity> velMap;

	@SuppressWarnings({"unchecked" })
	public MovementSystem() {
		super(Aspect.getAspectForAll(Position.class, Velocity.class));
	}

	@Override
	protected void process(Entity e) {
		//These grab the components from the entities.
		Position position = posMap.get(e);
		Velocity velocity = velMap.get(e);
		
		position.addX( velocity.velX * world.getDelta() );
		position.addY( velocity.velY * world.getDelta() );

	}
}
