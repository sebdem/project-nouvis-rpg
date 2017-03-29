package sebdem.nouvis.resource;

public class TileResource extends Resource{

	
	public TileResource( String name) {
		super(name);
	}
	
	@Override
	public String subFolder(){
		return "tiles";
	}
}
