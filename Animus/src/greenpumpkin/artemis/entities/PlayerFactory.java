package greenpumpkin.artemis.entities;

import greenpumpkin.artemis.components.HealthC;
import greenpumpkin.artemis.components.PlayerC;
import greenpumpkin.artemis.components.PositionC;
import greenpumpkin.artemis.components.SpriteC;
import greenpumpkin.artemis.components.VelocityC;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

////////////////////////////////////////////////////////////
//This the class for the Protagonist. This might be/////////
//the largest entity, so it is a little important.//////////
////////////////////////////////////////////////////////////


public class PlayerFactory {

	public static Entity create(World world, float x, float y) {
		Entity e = world.createEntity();

		PositionC position = new PositionC();
		position.x = x;
		position.y = y;
		e.addComponent(position);

		VelocityC velocity = new VelocityC();
		velocity.velX = 0;
		velocity.velY = 0;
		e.addComponent(velocity);
		
		HealthC health = new HealthC();
		health.health = 100;
		e.addComponent(health);

		Texture texture = new Texture(Gdx.files.internal("ProtagLeft.png"));
		
		SpriteC sprite = new SpriteC();
		sprite.sprite = new Sprite(texture);
		sprite.sprite.setScale(1/16f);
		sprite.sprite.setOrigin(0, 0);
		System.out.println(sprite.bob);
		e.addComponent(sprite);
		
		e.addComponent(new PlayerC());

		return e;
	}
}
