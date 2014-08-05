package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.PlayerC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class ControllerInputS extends EntityProcessingSystem implements InputProcessor {
	@Mapper ComponentMapper<VelocityC> velMap;
	
	private boolean up, down, left, right;
	private float jumpTime;
	
	@SuppressWarnings("unchecked")
	public ControllerInputS() {
		super(Aspect.getAspectForAll(PlayerC.class));
	}

	@Override
	 protected void initialize() {
	  Gdx.input.setInputProcessor(this);
	 }
	
	@Override
	protected void process(Entity e) {
		VelocityC velocity = velMap.get(e);
		
		 if(jumpTime<42)
			 jumpTime++;

		 if((jumpTime>40 || velocity.velY==0) && up) {
			 jumpTime=0;
		 }
		
		 if(up && jumpTime<20) {
			 velocity.velY = 8; //jump, will keep at jump velocity until frame 20 
		 }
		 
		 else {
			 velocity.velY-=0.6; //gravity
		 }
		 
		 if(left){
			 velocity.velX-=0.5;
		 }
		 
		 else if(right){
			 velocity.velX+=0.5;
		 }
		 
		 else velocity.velX-=(velocity.velX/8);
		 
		 if(down){
			 velocity.velY-=(velocity.velY/2);
		 }
		 
		 if(velocity.velY<-15)
			 velocity.velY=-15;
		 if(velocity.velX>4)
			 velocity.velX=4;
		 if(velocity.velX<-4)
			 velocity.velX=-4;
		 

		 //System.out.println("Velocity of the x axis: " + velocity.velX);
		 //System.out.println("Velocity of the y axis: " + velocity.velY);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.A) { left = true; }
		else if(keycode == Input.Keys.D) { right = true; }
		else if(keycode == Input.Keys.W) { up = true; }
		else if(keycode == Input.Keys.S) { down = true; }
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.A) { left = false; }
		else if(keycode == Input.Keys.D) { right = false; }
		else if(keycode == Input.Keys.W) { up = false; }
		else if(keycode == Input.Keys.S) { down = false; }
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
