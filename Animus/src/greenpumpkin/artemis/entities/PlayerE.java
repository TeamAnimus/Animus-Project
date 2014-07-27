package greenpumpkin.artemis.entities;

import greenpumpkin.artemis.components.PlayerC;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;

////////////////////////////////////////////////////////////
//This the class for the Protagonist. This might be/////////
//the largest entity, so it is a little important.//////////
////////////////////////////////////////////////////////////


public class PlayerE {
	
	public static Entity create(World world, float x, float y) {
		Entity e = world.createEntity();

		PositionC position = new PositionC();
		position.x = x;
		position.y = y;
		e.addComponent(position);
		
		VelocityC velocity = new VelocityC();
		velocity.velX = 0;
		velocity.velY = 0;
		e.addComponent(velocity);
		
		e.addComponent(new PlayerC());
		
		world.getManager(GroupManager.class).add(e, "Ben");
		
		return e;
	}
}
