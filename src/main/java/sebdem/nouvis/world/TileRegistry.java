package sebdem.nouvis.world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import sebdem.nouvis.world.Tile.TileJson;

public class TileRegistry {
	
	private HashMap<Integer, Tile> register;
	Gson parser;
	
	public TileRegistry() throws IOException{ 
		this.register = new HashMap<Integer,Tile>();
		parser = new Gson();
		
		List<File> filesInFolder = Files.walk(Paths.get("resources/tiles/"))
                .filter((Path file)->{ return file.toString().endsWith(".json"); })
                .map(Path::toFile)
                .collect(Collectors.toList());
		filesInFolder.forEach(this::readTileFromFile);
	}
	
	public static void main(String[] args) throws IOException{
		new TileRegistry();
	}
	
	public Tile get(int id){
		return this.register.get(id);
	}
	
	public Set<Integer> getIDs(){
		return register.keySet();
	}
	
	
	public void readTileFromFile(File f){
		try (Stream<String> lines = Files.lines(f.toPath())) {
		    String cont  = lines.collect(Collectors.joining(" "));
		    System.out.println(cont);
		    TileJson tile = parser.fromJson(cont, Tile.TileJson.class);
		    Tile t = tile.createTile();
			register.put(t.id, t);
		} catch (IOException e) {
		}
	}
}
