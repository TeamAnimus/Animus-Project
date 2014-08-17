package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.components.PositionC;
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
		super(Aspect.getAspectForAll(TiledC.class, PositionC.class));
		this.camera=camera;
	}
	
	@Override
	protected void initialize(){
		TiledMapFactory.create(world, "TestMapBack.tmx", 1/16f, camera).addToWorld();
		TiledMapFactory.create(world, "TestMap.tmx", 1/16f, camera).addToWorld();
	}

	@Override
	protected void process(Entity e) {
		TiledC map = tiledMap.get(e);
		camera.update();
		map.renderer.setView(camera);
		map.renderer.render();
	}
}
