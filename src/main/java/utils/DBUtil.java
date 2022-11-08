package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * class handle create connection and close it.
 * @author DuyTQFX11834
 *
 */
public class DBUtil {
	private static String DRIVER, SERVER_NAME, DB_NAME, USER_NAME, PASSWORD;
	static final String FILE_NAME = "/properties/DBConfig.properties";
	
	/**
	 * method create connection.
	 * @return Connection connection
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		Properties properties = new Properties();

		properties.load(DBUtil.class.getResourceAsStream(FILE_NAME));

		DRIVER = properties.getProperty("driver");
		SERVER_NAME = properties.getProperty("server");
		DB_NAME = properties.getProperty("dbname");
		USER_NAME = properties.getProperty("username");
		PASSWORD = properties.getProperty("password");
		Class.forName(DRIVER);

		return DriverManager.getConnection(SERVER_NAME + ";databaseName=" + DB_NAME, USER_NAME, PASSWORD);
	}
	
	/**
	 * method to close connection
	 * @param connection Connection
	 * @throws SQLException
	 */
	public static void closeConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
