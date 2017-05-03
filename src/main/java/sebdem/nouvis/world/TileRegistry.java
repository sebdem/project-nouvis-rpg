package sebdem.nouvis.world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public Tile getNextLargest(int id){
		return this.get(getNextLargestId(id));
	}
	
	public int getNextLargestId(int id){
		Set<Integer> keys = register.keySet();
		int larger = id;
		
		Integer[] tileids = keys.toArray(new Integer[keys.size()]);
		Arrays.parallelSort(tileids);
		int lindx = Arrays.binarySearch(tileids, larger);
		if (lindx < tileids.length - 1){
			larger = tileids[lindx+1];
		}
		return larger;
	}

	public Tile getNextSmallest(int id){
		return this.get(getNextSmallestId(id));
	}
	public int getNextSmallestId(int id){
		Set<Integer> keys = register.keySet();
		int smaller = id;
		
		Integer[] tileids = keys.toArray(new Integer[keys.size()]);
		Arrays.parallelSort(tileids);
		int lindx = Arrays.binarySearch(tileids, smaller);
		if (lindx > 0){
			smaller = tileids[lindx-1];
		}
		return smaller;
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
