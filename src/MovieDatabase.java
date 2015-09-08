import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {
	
	private Connection connection = null; 	
	private Statement statement = null; 
	private PreparedStatement pStatement = null; 
	private ResultSet result = null; 
	
	public MovieDatabase(){
			
		try {
		
			this.setConnection(); 
			this.statement = this.connection.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void setConnection(){ //throws SQLException 
		
		Scanner dbCreds = new Scanner(System.in);
		
		//Database URL
		final String DB_URL = "jdbc:mysql://localhost/moviedb";
		
		//Get Database credentials
		System.out.println("Enter DB Username");
		final String USER = dbCreds.next(); 
		
		System.out.println("Enter DB Password");
		final String PASS = dbCreds.next();
		
		try {
			
			System.out.println("Connecting to database...");
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}catch(Exception e){
		   //Handle errors for Class.forName
		    //e.printStackTrace();
		
		}finally{
			//close resources
		}
		
	}
	
	public void showMenu(){
		
		try {
			
			Scanner user_input = new Scanner(System.in);; 
			
			String menu = 
					"What would you like to do? " +"\n"
					+ "{1} Print out a movie with a given star name" + "\n"
					+ "{2} Insert a new star into database" + "\n"
					+ "{3} Insert a customer into the database" + "\n"
					+ "{4} Delete a customer from the datasbase" + "\n"
					+ "{5} Print out metadata for database" + "\n"
					+ "{6} Enter a SQL command" + "\n"
					+ "{E} Exit Menu" + "\n"
					+ "{T} Terminate Program" + "\n"; 
			
			boolean menuOn = true;
			
			while (menuOn){
				
				System.out.println(menu); 
				String choice = user_input.next(); 
//				choice = choice.toUpperCase(); 
				
				if (choice.equals("1")){
					
					System.out.println("Print Movie");
					this.printMovie(); 
				}
				
				else if (choice.equals("2")){
					
					System.out.println("Insert Star");
					this.insertStar();
				}
				
				else if (choice.equals("3")){
					System.out.println("Insert Customer"); 
					this.insertCustomer(); 
				}
				
				else if (choice.equals("4")){
					System.out.println("Delete Customer");
					this.deleteCustomer();
				}
				
				else if (choice.equals("5")){
					System.out.println("Print Metadata"); 
					this.printMetadata(); 
				}
				
				else if (choice.equals("6")){
					System.out.println("Enter SQL Command"); 
					this.enterQuery(); 
				}
				
				else if (choice.equals("E")){
					
					//close connection 
					System.out.println("Exiting Menu...");
					user_input.close();
					this.closeResources(); 
					this.setConnection(); 
				}
				
				else if (choice.equals("T") ){
					
					System.out.println("Terminating program...");
					user_input.close();
					this.closeResources();
					System.out.println("Program Terminated!"); 
					break; 
				}
				
				else {
					
					System.out.println("Erroneous Input!");
				}
			}
			
		}catch(Exception e){
			   //Handle errors for Class.forName
			    e.printStackTrace();
			}finally{
				 
				this.closeResources();
			}
		
	}

	public void printMovie() throws NumberFormatException, SQLException {
		
		Scanner scanner = new Scanner(System.in); 		
		System.out.println("Type an ID or a Star's first and/or last name to see their movies"); //type an ID or first and/or last name 
		String user_input = scanner.nextLine(); 
		
		if (user_input.matches(".*\\d.*")){ // by ID
			
			System.out.println("You chose to query by ID");
			pStatement = this.connection.prepareStatement("SELECT M.* "
					+ "FROM stars_in_movies SM, stars S, movies M "
					+ "WHERE SM.star_id = S.id "
					+ "AND SM.movie_id = M.id "
					+ "AND S.id = '"+Integer.parseInt(user_input)+"'");
		}
		
		else if (!user_input.contains(" ")){ //only first name 
			
			System.out.println("You chose to query by first name only");
			pStatement = this.connection.prepareStatement("SELECT M.* "
					+ "FROM stars_in_movies SM, stars S, movies M "
					+ "WHERE SM.star_id = S.id "
					+ "AND SM.movie_id = M.id "
					+ "AND S.first_name = '"+user_input+"'");
			
		}
		
		else if (user_input.contains(" ")){ //by First and Last Name 

			System.out.println("You chose to query by first and last name");
			pStatement = this.connection.prepareStatement("SELECT M.* "
			+ "FROM stars_in_movies SM, stars S, movies M "
			+ "WHERE SM.star_id = S.id "
			+ "AND SM.movie_id = M.id "
			+ "AND S.first_name = '"+user_input.split("\\s+")[0]+"' "
			+ "AND S.last_name = '"+user_input.split("\\s+")[1]+"'");					
		}
			
		this.result = pStatement.executeQuery(); 
		int rowCount = this.result.last() ? this.result.getRow() : 0;
		this.result.beforeFirst(); 
		
		if (!this.result.next()){
			
			System.out.println("No Results!");
		}
		
		else {
			
			this.result.beforeFirst();
			while (this.result.next()){
				
				System.out.println("ID: "+this.result.getInt(1)+" Title "+this.result.getString(2)+" Year: "
				+ this.result.getString(3)+" Director: "+this.result.getString(4)+ "\n"
				+ "Banner_Url: "+this.result.getString(5)+"\n"
				+ "Trailer_Url: "+this.result.getString(6)); 				
			}
		}
		
		return; 
			 
	}
	
	public void insertStar() throws SQLException{
		
		Scanner scanner = new Scanner(System.in); 		
		System.out.println("Type a customer name to insert into database"); 
		String user_input = scanner.nextLine(); 

		if (user_input.contains(" ")){ //Inserting First and Last name 
			
			System.out.println("Inserting Star's first and last name to database");
			pStatement = this.connection.prepareStatement("INSERT INTO stars(first_name, last_name) "
					+ "VALUES('"+user_input.split("\\s+")[0]+"', '"+user_input.split("\\s+")[1]+"')");  
		}
		
		else{ //Inserting Last Name 
			
			System.out.println("Inserting Star's last name to database");
			pStatement = this.connection.prepareStatement("INSERT INTO stars(first_name, last_name) "
					+ "VALUES('', '"+user_input+"')"); 	
			
		}
		
		pStatement.executeUpdate(); 
		return; 
		
	}
	
	public void insertCustomer() throws SQLException{

		Scanner scanner = new Scanner(System.in); 		
		System.out.println("Type a star name to insert into database"); 
		String user_input = scanner.nextLine(); 
		
		if (user_input.contains(" ")){ //Inserting customer with first/last name 
			
			pStatement = this.connection.prepareStatement("SELECT first_name, last_name, id "
					+ "FROM creditcards "
					+ "WHERE first_name = '"+user_input.split("\\s+")[0]+"' AND last_name = '"+user_input.split("\\s+")[1]+"';"); 
			
			this.result = pStatement.executeQuery(); 
			
			if(!this.result.next()){
				
				System.out.println("Customer's credit card does not exist");
			}
			
			else{
				
				this.result.beforeFirst();
				this.result.next(); 
				pStatement = this.connection.prepareStatement("INSERT INTO customers(first_name, last_name, cc_id, address, email, password) "
						+ "VALUES('"+this.result.getString(1)+"', '"+this.result.getString(2)+"', '"+this.result.getInt(3)+"' , '', '' ,'')"); 
				
				pStatement.executeUpdate();
			}
			
		}
		
		else{ //inserting customer with just last name 
			
			pStatement = this.connection.prepareStatement("SELECT first_name, last_name, id "
					+ "FROM creditcards "
					+ "WHERE last_name = '"+user_input.split("\\s+")[0]+"';"); 	

			this.result = pStatement.executeQuery(); 
			
			if(!this.result.next()){
				
				System.out.println("Customer's credit card does not exist");
			}
			
			else{
				
				this.result.beforeFirst();
				this.result.next(); 
				
				pStatement = this.connection.prepareStatement("INSERT INTO customers(first_name, last_name, cc_id, address, email, password) "
						+ "VALUES('', '"+this.result.getString(2)+"', '"+this.result.getInt(3)+"' , '', '' , '')"); 
				
				pStatement.executeUpdate();
				
			}
			
		}
		
		return; 
	}
	
	public void deleteCustomer() throws SQLException{
	
		Scanner scanner = new Scanner(System.in); 		
		System.out.println("Type a star name to delete from database"); 
		String user_input = scanner.nextLine(); 
		
		if (user_input.contains(" ")){ //first/last name input

			pStatement = this.connection.prepareStatement("SELECT * "
					+ "FROM customers "
					+ "WHERE first_name = '"+user_input.split("\\s+")[0]+"' AND last_name = '"+user_input.split("\\s+")[1]+"';"); 
			this.result = pStatement.executeQuery();

			if(!this.result.next()){
				
				System.out.println("The customer does not exist!");
			}
			
			else{
				
				pStatement = this.connection.prepareStatement("DELETE FROM customers "
						+ "WHERE first_name = '"+user_input.split("\\s+")[0]+"' AND last_name = '"+user_input.split("\\s+")[1]+"';"); 
				
				pStatement.executeUpdate();
				
			}
			
		}
		
		else{ //last name input 

			pStatement = this.connection.prepareStatement("SELECT * "
					+ "FROM customers "
					+ "WHERE last_name = '"+user_input.split("\\s+")[0]+"';"); 	

			this.result = pStatement.executeQuery(); 
			
			if(!this.result.next()){
				
				System.out.println("The customer does not exist");
			}
			
			else{
				
				pStatement = this.connection.prepareStatement("DELETE FROM customers "
						+ "WHERE last_name = '"+user_input.split("\\s+")[0]+"';"); 	
				
				pStatement.executeUpdate(); 
				
			}
			
		}
		
	}
	
	public void printMetadata() throws SQLException{
		
	}
	
	public void enterQuery() throws SQLException{
		
	}
	
	public void closeResources(){
		
		try {
			
			System.out.println("Closing connection and resources...");
			
			if (connection != null){
				
				this.connection.close(); 
				this.connection = null; 
			}
			
			if (statement != null){
				
				this.statement.close(); 
				this.statement = null; 
			}
			
			if (result != null){
				
				this.result.close();
				this.result = null; 
			}		
			
		}catch(Exception e){
		    e.printStackTrace();
		}    
	}
	
	public static void main(String[] args) {
		
		MovieDatabase fabflix = new MovieDatabase(); 
		fabflix.showMenu(); 

	}

}
