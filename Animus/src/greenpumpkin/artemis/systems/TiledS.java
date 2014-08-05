package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.TiledC;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.artemis.systems.*;

public class TiledS extends VoidEntitySystem {
	@Mapper ComponentMapper<TiledC> tiledMap;
	
	@Override
	protected void processSystem() {
		AnimusWorld.backRenderer.setView(AnimusWorld.camera);
		AnimusWorld.backRenderer.render();
		AnimusWorld.frontRenderer.setView(AnimusWorld.camera);
		AnimusWorld.frontRenderer.render();
	}
}
