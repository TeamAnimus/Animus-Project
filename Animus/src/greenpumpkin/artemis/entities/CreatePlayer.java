package greenpumpkin.artemis.entities;

import greenpumpkin.artemis.components.Player;
import greenpumpkin.artemis.components.Position;
import greenpumpkin.artemis.components.Velocity;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;

////////////////////////////////////////////////////////////
//This the class for the Protagonist. This might be/////////
//the largest entity, so it is a little important.//////////
////////////////////////////////////////////////////////////


public class CreatePlayer {
	
	public static Entity createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		
		Position position = new Position();
		position.x = x;
		position.y = y;
		e.addComponent(position);
		
		Velocity velocity = new Velocity();
		velocity.velX = 0;
		velocity.velY = 0;
		e.addComponent(velocity);
		
		e.addComponent(new Player());
		
		world.getManager(GroupManager.class).add(e, "Ben");
		
		return e;
	}
}
