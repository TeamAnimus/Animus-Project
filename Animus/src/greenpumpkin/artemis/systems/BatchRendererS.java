package greenpumpkin.artemis.systems;

import greenpumpkin.artemis.AnimusWorld;
import greenpumpkin.artemis.components.SpriteC;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;

public class BatchRendererS extends EntitySystem {
	@Mapper ComponentMapper<SpriteC> spriteMap;

	@SuppressWarnings("unchecked")
	public BatchRendererS() {
		super(Aspect.getAspectForAll(SpriteC.class));
	}

	protected void process(Entity e) {
	    SpriteC sprite = spriteMap.get(e);
		//AnimusWorld.batch.draw(sprite.sprite.getTexture(), sprite.sprite.getX(), sprite.sprite.getY());
	    sprite.sprite.draw(AnimusWorld.batch);
	    System.out.println("process is called.");
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		AnimusWorld.batch.begin();
		AnimusWorld.batch.setProjectionMatrix(AnimusWorld.camera.combined);
		for (int i = 0, s = entities.size(); s > i; i++) {
			process(entities.get(i));
			System.out.println(entities.get(i).toString());
		}
		AnimusWorld.batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return false;
	}

}
