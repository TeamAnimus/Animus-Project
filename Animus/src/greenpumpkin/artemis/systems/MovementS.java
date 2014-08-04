package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

////////////////////////////////////////////////////////////
//This is the Artemis-based System for Movement. This///////
//is used for anything on the map that physically moves.////
////////////////////////////////////////////////////////////

public class MovementS extends EntityProcessingSystem {
	@Mapper ComponentMapper<PositionC> posMap;
	@Mapper ComponentMapper<VelocityC> velMap;
	@Mapper ComponentMapper<SpriteC> spriteMap;

	@SuppressWarnings({"unchecked" })
	public MovementS() {
		super(Aspect.getAspectForAll(PositionC.class, VelocityC.class));
	}

	@Override
	protected void process(Entity e) {
		//These grab the components from the entities.
		PositionC position = posMap.get(e);
		VelocityC velocity = velMap.get(e);
		
		position.addX( velocity.velX * world.getDelta() );
		position.addY( velocity.velY * world.getDelta() );
		
		if(position.y<2)
			position.y=2;
		
		if(spriteMap.has(e)){
			SpriteC sprite = spriteMap.get(e);
			sprite.sprite.setPosition(position.x, position.y);
		}
		System.out.println("Movement system has beel called.");
		

	}
}
