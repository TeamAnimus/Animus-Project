package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import greenpumpkin.artemis.components.VelocityC;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MapCollisionS extends EntityProcessingSystem {
	@Mapper ComponentMapper<PositionC> posMap;
	@Mapper ComponentMapper<VelocityC> velMap;
	@Mapper ComponentMapper<SpriteC> spriteMap;
	
	private TiledMapTileLayer collisionLayer;

	@SuppressWarnings({ "unchecked" })
	public MapCollisionS() {
		super(Aspect.getAspectForAll(VelocityC.class, SpriteC.class));
	}

	@Override
	protected void process(Entity e) {
		PositionC position = posMap.get(e);
		VelocityC velocity = velMap.get(e);
		SpriteC sprite = spriteMap.get(e);
		
		float step = sprite.sprite.getScaleY();
		System.out.println(step);
		boolean collisionX = false, collisionY = false;
		if(velocity.velX < 0) // going left
			collisionX = collidesLeft();
		else if(velocity.velX > 0) // going right
			collisionX = collidesRight();
		if(velocity.velY < 0) // going down
			collisionY = collidesBottom();
		else if(velocity.velY > 0) // going up
			collisionY = collidesTop();
		
		if(collisionX) {
			velocity.velX = 0;
		}
		
		if(collisionY) {
			velocity.velY = 0;
		}
	}

	private boolean collidesTop() {
		return false;
	}

	private boolean collidesLeft() {
		return false;
	}

	private boolean collidesBottom() {
		return false;
	}
	
	private boolean collidesRight() {
		return false;
	}
}
