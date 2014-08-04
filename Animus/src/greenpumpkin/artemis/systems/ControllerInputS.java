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
		 if(up==true && jumpTime<20){
			 velocity.velY = 2; //jump, will keep at jump velocity until frame 20 
		 }
		 else {
			 velocity.velY-=0.2; //gravity
		 }
		 
		 System.out.println(velocity.velY);

		 jumpTime++;

		 if(jumpTime >60) {
			 jumpTime=0;
		 }
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
