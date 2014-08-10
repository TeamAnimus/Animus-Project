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
		
		boolean water = false, diagonalLeft = false, diagonalRight = false, collisionX = false, collisionY = false;
		
		//THESE ALL CHECK FOR COLLISIONS
		water = collidesWater(sprite, "water");
		if(velocity.velY < 0) { // going down, checks for diagonals on bottom
			diagonalLeft = collidesDiagonal(sprite, "diagonalLeft");
			diagonalRight = collidesDiagonal(sprite, "diagonalRight");
			if(!diagonalLeft || !diagonalRight)
			collisionY = collidesBottom(sprite, "blocked");
		}
		else if(velocity.velY > 0) // going up
			collisionY = collidesTop(sprite, "blocked");
		if(velocity.velX < 0) // going left
			collisionX = collidesLeft(sprite, "blocked");
		else if(velocity.velX > 0) // going right
			collisionX = collidesRight(sprite, "blocked");
		
		//THESE DO THE VELOCITY MANIPULATION AFTER THE FACT.
		if(collisionX) {
			velocity.velX = 0;
		}
		if(diagonalLeft && -((position.x+0.5f)%1.0f)>=(position.y%1.0f))
			velocity.velY=velocity.velX;
		else if(diagonalRight){
			velocity.velY=velocity.velX;
			float newPosition=(float) (Math.floor(position.y+0.02f))+((position.x+0.5f)%1f);;
			if((position.x+0.5f)%1f > 0.5f)
				newPosition =(float) (Math.floor(position.y-0.02f))+((position.x+0.5f)%1f);
            if((position.x+0.51f)%1f>=position.y%1f)
                position.y=newPosition;
			System.out.println(((position.x+0.5f)%1.0f) + " and " + ((position.y)%1.0f));
		}
		else if(collisionY) {
			velocity.velY = 0;
			position.y = Math.round(position.y);
		}
		if(water){
			velocity.velY -= (velocity.velY/6f - 0.4f);
			velocity.velX -= (velocity.velX/6f);
		}
	}

	private boolean collidesLeft(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX(), sprite.sprite.getY() + 1, id))
			return true;
		if(isCellBlocked(sprite.sprite.getX(), sprite.sprite.getY() + 0.4f, id))
			return true;
		return false;
	}
	
	private boolean collidesRight(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX() + 1, sprite.sprite.getY() + 1, id))
			return true;
		//if(isCellBlocked(sprite.sprite.getX() + 1, sprite.sprite.getY()+0.2f, id))
			//return true;
		return false;
	}

	private boolean collidesBottom(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX()+0.1f, sprite.sprite.getY(), id))
			return true;
		if(isCellBlocked(sprite.sprite.getX()+0.9f, sprite.sprite.getY(), id))
			return true;
		return false;
	}

	private boolean collidesDiagonal(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX()+0.5f, sprite.sprite.getY(), id))
			return true;
		if(isCellBlocked(sprite.sprite.getX()+0.5f, sprite.sprite.getY()-0.1f, id))
			return true;
		return false;
	}
	
	private boolean collidesTop(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX()+0.1f, sprite.sprite.getY() + 2, id))
			return true;
		if(isCellBlocked(sprite.sprite.getX()+0.9f, sprite.sprite.getY() + 2, id))
			return true;
		return false;
	}
	
	private boolean collidesWater(SpriteC sprite, String id) {
		if(isCellBlocked(sprite.sprite.getX()+0.5f, sprite.sprite.getY(), id))
			return true;
		return false;
	}
	
	private boolean isCellBlocked(float x, float y, String id) {
		Boolean blocked = false;
		Cell cell = AnimusWorld.collisionLayer.getCell((int) (x), (int) (y));
		if(cell != null){
			if(cell.getTile() != null){
				if(cell.getTile().getProperties().containsKey(id)){
					blocked = true;
				}
			}
		}
		//if cell is not empty and the tile of the cell is not empty and the cell's properties says its blocked
		return blocked;
	}
}
