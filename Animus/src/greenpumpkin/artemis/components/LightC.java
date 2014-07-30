package greenpumpkin.artemis.components;

import com.artemis.Component;
import box2dLight.Light;

//may need to extend all the forms of light

public class LightC extends Component {
	public Light light;
	public float maxDist = 0;
	public float size = 0;
	public float time = 0;
	public float currTime = 0;
}
