package sources;

import java.util.ArrayList;

public class Movie {
	//Movie object to hold all info about a Movie
	
	public String title, year, director, id, trailer, banner;
	public ArrayList<Star> stars;
	public ArrayList<Genre> genres;
	
	
	public Movie(String title, String year, String director, String id, String trailer, String banner,
			ArrayList<Star> stars, ArrayList<Genre> genres) {
		this.title = title;
		this.year = year;
		this.director = director;
		this.id = id;
		this.trailer = trailer;
		this.banner = banner;
		this.stars = stars;
		this.genres = genres;
	}


	public Movie(String id, String title) {
		this.id = id;
		this.title = title;

	}
}
