import java.sql.*;

public class MySQLExample {
	public static void main(String[] args) {
		// connect to database
		String url = "jdbc:mysql://172.31.21.153:32639,172.31.32.194:32639,172.31.47.15:32639/mysql?useSSL=false";
		String username = "root";
		String password = "77zkv2w8";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Connected to database!");

			// execute a SQL
			String query = "SELECT * FROM user";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {

				// get the results
				while (resultSet.next()) {
					String host = resultSet.getString("Host");
					String user = resultSet.getString("User");
					System.out.println("Host: " + host + ", User: " + user);
				}
					}

			// close the connection
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

