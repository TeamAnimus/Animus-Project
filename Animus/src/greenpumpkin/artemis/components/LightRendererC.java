package greenpumpkin.artemis.components;

import box2dLight.RayHandler;
import com.badlogic.gdx.physics.box2d.World;

public class LightRendererC {
	public int numRays = 8; //how many rays are emitted for shadow casting
	public float lightDistance = 16f; // distance light goes
	public World world; /** our box2D world, controls physics **/
	public RayHandler rayHandler; //the main object of light2d, heavily important
}
