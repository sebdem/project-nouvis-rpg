package sebdem.nouvis.resource;

import java.io.File;
import java.nio.file.Paths;

public class Resource {

	private String base;

	protected String name;
	
	public Resource(String name){
		this.name = name;
		this.base = "resources";
	}
	
	public String subFolder(){
		return "";
	}
	public String name(){
		return this.name;
	}
	public String base(){
		return this.base;
	}
	
	public String fullPath(){
		String sub = subFolder();
		if (sub != "")
			return   base() + "\\" + subFolder() + "\\" + name();
		return  base() + "\\" + name();
	}
	
	public File load(){
		File f =  Paths.get(fullPath()).toFile();
		//System.out.println("Loading File Resource: '" + fullPath() + "' -> " + f.exists());

		return f;
	}
}
