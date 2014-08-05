package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;

public class BatchRendererS extends EntitySystem {
	@Mapper ComponentMapper<SpriteC> spriteMap;
	@Mapper ComponentMapper<PositionC> posMap;

	@SuppressWarnings("unchecked")
	public BatchRendererS() {
		super(Aspect.getAspectForAll(SpriteC.class, PositionC.class));
	}
	
	protected void initialize() {
	}

	protected void process(Entity e) {
	    SpriteC sprite = spriteMap.get(e);
	    PositionC position = posMap.get(e);

		sprite.sprite.setPosition(position.x, position.y);
	    sprite.sprite.draw(AnimusWorld.batch);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		AnimusWorld.batch.setProjectionMatrix(AnimusWorld.camera.combined);
		AnimusWorld.batch.begin();
		for (int i = 0, s = entities.size(); s > i; i++) {
			process(entities.get(i));
		}
		AnimusWorld.batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
