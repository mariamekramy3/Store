
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the store application!");
		
		DatabaseConnection db = new DatabaseConnection();
		db.getConnection();

	}

}
