package sebdem.nouvis.resource;

public class FontResource extends Resource {

	public FontResource(String name) {
		super(name);
	}

	@Override
	public String subFolder(){
		return "textures\\fonts";
	}
}
