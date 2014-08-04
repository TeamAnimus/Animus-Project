package greenpumpkin.artemis.entities;

import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.TiledC;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledMapFactory {
	
	public static Entity create(World world, String mapFile, float mapSize, OrthographicCamera camera) {
		Entity e = world.createEntity();
		
		TiledC map = new TiledC();
		map.tiledMap= new TmxMapLoader().load(mapFile);
		map.renderer = new OrthogonalTiledMapRenderer(map.tiledMap, mapSize);
		map.renderer.setMap(map.tiledMap);
		map.renderer.setView(camera);
		e.addComponent(map);
		
		PositionC pos = new PositionC();
		pos.x=0;
		pos.y=0;
		e.addComponent(pos);		
		
		world.getManager(GroupManager.class).add(e, "Map");
		
		return e;
	}
}
