package app.database.dao.base;

import java.sql.PreparedStatement;

import app.database.connection.DBConnection;

public abstract class BaseDAO {
	private PreparedStatement query;
	private DBConnection connection;

	public BaseDAO(DBConnection connection) {
		this.setConnection(connection);
	}

	public abstract boolean save();

	public abstract boolean update();

	public abstract boolean delete(long ids[]);

	public abstract void search();
	
	protected abstract void createTable();

	protected PreparedStatement getQuery() {
		return query;
	}

	protected void setQuery(PreparedStatement query) {
		this.query = query;
	}

	protected DBConnection getConnection() {
		return connection;
	}

	protected void setConnection(DBConnection connection) {
		this.connection = connection;
	}
}
