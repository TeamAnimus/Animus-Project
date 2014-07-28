package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.TiledC;
import greenpumpkin.artemis.entities.TiledMapFactory;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TiledS extends EntityProcessingSystem {
	@Mapper ComponentMapper<TiledC> tiledMap;
	private OrthographicCamera camera;

	@SuppressWarnings("unchecked")
	public TiledS(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(TiledC.class));
		this.camera=camera;
	}
	
	@Override
	protected void initialize(){
		TiledMapFactory.create(world, "TestMap.tmx", 10f, camera);
		System.out.println("init");
	}

	@Override
	protected void process(Entity e) {
		System.out.println("eergh");
		TiledC map = tiledMap.get(e);
		camera.update();
		map.renderer.setView(camera);
		map.renderer.render();
		System.out.println("made it to process");
	}

}
