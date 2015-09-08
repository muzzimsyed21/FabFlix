package sources; 
import java.util.ArrayList;

public class User {

	public String id =null; 
	public String firstName =null; 
	public String lastName =null;
	public String username =null; 
	public String password =null; 
	public ArrayList<String> cart =null;
	
	
	public User(String id, String firstName, String lastName, String username, String password, ArrayList<String> cart) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.username = username; 
		this.password= password; 
		this.cart = cart; 
		
		if (id == null || (firstName == null && lastName == null) || username == null || password ==  null || cart == null)  {
			
			System.out.println("All credentials must be specified!"); 
		}
		else{
			
			System.out.println("User with credentials constructed");
		}
	}
	
	public User(){
		
		System.out.println("User with no credentials constructed"); 
	}
	
	public boolean notExists (){
		
		return id == null || (firstName == null && lastName == null) || username == null || password ==  null || cart == null; 
	}
}