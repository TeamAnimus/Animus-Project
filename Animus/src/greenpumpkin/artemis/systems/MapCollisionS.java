package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class MapCollisionS extends IntervalEntityProcessingSystem {
	@Mapper ComponentMapper<PositionC> posMap;
	@Mapper ComponentMapper<VelocityC> velMap;
	@Mapper ComponentMapper<SpriteC> spriteMap;
	PositionC position;
	
	@SuppressWarnings({ "unchecked" })
	public MapCollisionS() {
		super(Aspect.getAspectForAll(VelocityC.class, SpriteC.class), 1/60f);
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
			diagonalLeft = collidesDiagonal(sprite, position, "diagonalLeft");
			diagonalRight = collidesDiagonal(sprite, position, "diagonalRight");
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
		if(diagonalLeft && (position.y%1f < (1-((position.x+0.5f)%1.0f)))) {
			velocity.velY=-velocity.velX*1.3f;
			float newPosition=(float) (Math.ceil(position.y))-((position.x+0.5f)%1f);
			if((position.x+0.5f)%1f > 0.85f)
				newPosition=(float) (Math.ceil(position.y-0.1f))-((position.x+0.5f)%1f);
				//new y is old y converted to int, plus halfway point of sprite's x, modulus 1
				//presents problem of being called too early.
			if(position.y>=newPosition)
				position.y=newPosition;
		}
		else if(diagonalRight && (position.y%1f < (position.x+0.5f)%1f)) {
			velocity.velY=velocity.velX*1.3f;
			float newPosition=(float) (Math.floor(position.y))+((position.x+0.5f)%1f);
			if((position.x+0.5f)%1f > 0.85f)
				newPosition=(float) (Math.floor(position.y-0.1f))+((position.x+0.5f)%1f);
				//new y is old y converted to int, plus halfway point of sprite's x, modulus 1
				//presents problem of being called too early.
			if(position.y>=newPosition)
				position.y=newPosition;
		}
		else if(collisionY) {
			velocity.velY = 0;
			position.y = Math.round(position.y);
		}
		if(water){
			velocity.velY -= (velocity.velY/12f - 0.58f);
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

	private boolean collidesDiagonal(SpriteC sprite, PositionC position, String id) {
		if(isCellBlocked(sprite.sprite.getX()+0.5f, sprite.sprite.getY(), id))
			return true;
		if((position.x+0.5f)%1f > 0.85f)
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
