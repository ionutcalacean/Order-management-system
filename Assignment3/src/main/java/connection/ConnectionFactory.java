package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	private static final  Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/ordermanagement?serverTimezone=UTC";
	private static final String USER="root";
	private static final String PASS="root";
	
	private static ConnectionFactory singleInstance=new ConnectionFactory();
	
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * creeaza conexiunea cu baza de date
	 * @return Connection acea conexiune
	 */
	private Connection createConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(DBURL, USER, PASS);
			System.out.println("Connection succeded");
		}catch(SQLException e)
		{
			LOGGER.log(Level.WARNING, "Error while trying to conenct to database");
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * getter penttru conexiune
	 * @return Connection, acea conexiune efectuata care e single instance
	 */
	public static Connection getConnection()
	{
		return singleInstance.createConnection();
	}
	/**
	 * se inchide conexiunea
	 * @param conn conexiunea
	 */
	public static void close(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING,"Error while trying to close connection to database");
				e.printStackTrace();
			}
		}
	}
	/**
	 * se inchide statementul
	 * @param statement statementul care se doreste a fi inchis
	 */
	public static void close(Statement statement)
	{
		if(statement!=null)
		{
			try {
				statement.close();
			}catch(SQLException e)
			{
				LOGGER.log(Level.WARNING, "Error while trying to close statement");
				e.printStackTrace();
			}
		}
	}
	/**
	 * se inchide setul de resultate
	 * @param resultSet-ul care se dorestre a fi inchis
	 */
	public static void close(ResultSet resultSet)
	{
		if(resultSet!=null)
		{
			try {
				resultSet.close();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING, "Error while trying to close result set");
			}
		}
	}
}
