package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
//import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class MapCollisionS extends EntityProcessingSystem {
	@Mapper ComponentMapper<PositionC> posMap;
	@Mapper ComponentMapper<VelocityC> velMap;
	@Mapper ComponentMapper<SpriteC> spriteMap;
	PositionC position;
	
	@SuppressWarnings({ "unchecked" })
	public MapCollisionS() {
		super(Aspect.getAspectForAll(VelocityC.class, SpriteC.class));
	}

	@Override
	protected void process(Entity e) {
		PositionC position = posMap.get(e);
		VelocityC velocity = velMap.get(e);
		SpriteC sprite = spriteMap.get(e);
		
		boolean diagonalLeft = false, diagonalRight = false, collisionX = false, collisionY = false;
		if(velocity.velX < 0) // going left
			collisionX = collidesLeft(sprite);
		else if(velocity.velX > 0) // going right
			collisionX = collidesRight(sprite);
		if(velocity.velY < 0) // going down
			collisionY = collidesBottom(sprite);
		else if(velocity.velY > 0) // going up
			collisionY = collidesTop(sprite);
		
		if(collisionX) {
			velocity.velX = 0;
		}
		
		if(collisionY) {
			velocity.velY = 0;
			System.out.println("yep.");
		}
		else
			System.out.println("nope.");
	}

	private boolean collidesLeft(SpriteC sprite) {
		if(isCellBlocked(sprite.sprite.getX(), sprite.sprite.getY() + 1))
			return true;
		if(isCellBlocked(sprite.sprite.getX(), sprite.sprite.getY() + 0.2f))
			return true;
		return false;
	}
	
	private boolean collidesRight(SpriteC sprite) {
		if(isCellBlocked(sprite.sprite.getX() + 1, sprite.sprite.getY() + 1))
			return true;
		if(isCellBlocked(sprite.sprite.getX() + 1, sprite.sprite.getY()+0.2f))
			return true;
		return false;
	}

	private boolean collidesBottom(SpriteC sprite) {
		if(isCellBlocked(sprite.sprite.getX()+0.1f, sprite.sprite.getY()))
			return true;
		if(isCellBlocked(sprite.sprite.getX()+0.9f, sprite.sprite.getY()))
			return true;
		return false;
	}
	
	private boolean collidesTop(SpriteC sprite) {
		if(isCellBlocked(sprite.sprite.getX()+0.1f, sprite.sprite.getY() + 2))
			return true;
		if(isCellBlocked(sprite.sprite.getX()+0.9f, sprite.sprite.getY() + 2))
			return true;
		return false;
	}
	
	private boolean isCellBlocked(float x, float y) {
		System.out.println("check.");
		Boolean blocked = false;
		Cell cell = AnimusWorld.collisionLayer.getCell((int) (x), (int) (y));
		if(cell != null){
			System.out.println("1");
			if(cell.getTile() != null){
				System.out.println("2");
				if(cell.getTile().getProperties().containsKey("blocked")){
					blocked = true;
					System.out.println("3");
				}
			}
		}
		//if cell is not empty and the tile of the cell is not empty and the cell's properties says its blocked
		return blocked;
	}
}
