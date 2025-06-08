package app.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	private Connection connection;

	public DBConnection(String url, String driverPackage, String user, String password) {
		try {
			Class.forName(driverPackage);
			Connection conn = DriverManager.getConnection(url, user, password);
			this.setConnection(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Problem connecting to database!");
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement genPreparedStatement(String sql) {
		try {
			return this.getConnection().prepareStatement(sql);
		} catch (Exception e) {
			return null;
		}
	}
}
