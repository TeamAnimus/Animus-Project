package greenpumpkin.artemis.entities;

import greenpumpkin.artemis.components.LightC;
import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;

public class LightFactory {
	
	public static Entity createPoint(World world, RayHandler rayHandler, int numRays, Color color, float lightDistance, float x, float y) {
		Entity e = world.createEntity();
		
		LightC light = new LightC();
		light.light = new PointLight(rayHandler, numRays, color, lightDistance, x, y);
		e.addComponent(light);
		
		return e;
	}
	
	public static Entity createCone(World world, RayHandler rayHandler, int numRays, Color color, float lightDistance, float posX, float posY, float angle, float size) {
		Entity e = world.createEntity();
		
		LightC light = new LightC();
		light.light = new ConeLight(rayHandler, numRays, color, lightDistance*3, posX, posY, angle, size);
		e.addComponent(light);
		
		return e;
	}
}
