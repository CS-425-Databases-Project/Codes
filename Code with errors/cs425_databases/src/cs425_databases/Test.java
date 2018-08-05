package cs425_databases;

public class Test {
	
	private static SQLInterface database = new SQLInterface();

	public static void main(String[] args) {
		database.sqlCode("select * from product");

	}

}
