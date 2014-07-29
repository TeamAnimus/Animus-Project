package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.TiledC;
import greenpumpkin.artemis.entities.TiledMapFactory;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.*;

public class TiledS extends EntityProcessingSystem {
	@Mapper ComponentMapper<TiledC> tiledMap;

	@SuppressWarnings("unchecked")
	public TiledS() {
		super(Aspect.getAspectForAll(TiledC.class, PositionC.class));
	}
	
	@Override
	protected void initialize(){
		TiledMapFactory.create(world, "TestMapBack.tmx", 1/16f, AnimusWorld.camera).addToWorld();
		TiledMapFactory.create(world, "TestMap.tmx", 1/16f, AnimusWorld.camera).addToWorld();
	}

	@Override
	protected void process(Entity e) {
		TiledC map = tiledMap.get(e);
		map.renderer.setView(AnimusWorld.camera);
		map.renderer.render();
	}
}
