package sebdem.nouvis.resource;

public class SpriteResource extends Resource {

	
	protected String subfolder = "";

	public SpriteResource( String name) {
		super(name);
	}
	public SpriteResource(String subfolder, String name) {
		super(name);
		this.subfolder = subfolder;
	}

	@Override
	public String subFolder(){
		if (this.subfolder != "")
			return "textures\\sprites\\"+subfolder;
		return "textures\\sprites";
	}
}
