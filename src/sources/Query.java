package sources;

public class Query {
	//class to return long string queries 
	
	public String login(String username, String password){
		
		return "SELECT * from customers where email= '" +username+ "' and password='" + password + "'"; 
		
	}
	
	public String genre(String genre){
		
		return "SELECT * FROM movies M, genres G, genres_in_movies GM WHERE M.id = GM.movie_id AND G.id = GM.genre_id AND G.name ='"+genre +"'"; 
	}
	
	public String title(String letter){
		
		return "SELECT * FROM movies M WHERE M.title LIKE '" + letter + "%'"; 
		
	}
}
