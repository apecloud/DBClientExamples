import java.sql.*;

public class PostgreSQLExample {
	public static void main(String[] args) {
		// connect the database
		String url = "jdbc:postgresql://172.31.21.153:30150,172.31.32.194:30150,172.31.47.15:30150/postgres?useSSL=false";
		String username = "postgres";
		String password = "fx9xflfb";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Connected to database!");

			// execute a SQL
			String query = "SELECT * FROM pg_stat_activity";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {

				// get the results
				while (resultSet.next()) {
					String pid = resultSet.getString("Pid");
					String usename = resultSet.getString("usename");
					System.out.println("Pid: " + pid + ", Usename: " + usename);
				}
					}

			// close the connection
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

