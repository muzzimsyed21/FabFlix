package sources;

import java.util.ArrayList;

public class Star {
	
	public String name, dob, picture, id;
	public ArrayList<Movie> movies;
	
	public Star(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public Star(String name, String dob, String picURL, String id, ArrayList<Movie> movies) {
		this.name = name;
		this.dob = dob;
		this.picture = picURL;
		this.id = id;
		this.movies = movies;
	}
}
