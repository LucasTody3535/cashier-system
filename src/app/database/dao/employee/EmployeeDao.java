package app.database.dao.employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.employee.Employee;

public class EmployeeDao extends BaseDAO {
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public EmployeeDao(DBConnection connection) {
		super(connection);
	}

	@Override
	public boolean save() {
		String sql = "INSERT INTO Employees VALUES(?, ?, ?);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for (Employee employee : this.employees) {
				if (employee.getId() == 0) {
					stmt.setLong(1, employee.getId());
					stmt.setString(2, employee.getName());
					stmt.setString(3, employee.getIdCard());
					;
					stmt.execute();
					employee.setId(-1);
				}
			}
			this.employees.removeIf(employee -> employee.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update() {
		String sql = "UPDATE Employees SET name = ?, id_card = ? WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for (Employee employee : this.employees) {
				if (employee.getId() > 0) {
					stmt.setString(1, employee.getName());
					stmt.setString(2, employee.getIdCard());
					stmt.setLong(3, employee.getId());
					stmt.execute();
					employee.setId(-1);
				}
			}
			this.employees.removeIf(employee -> employee.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(long[] ids) {
		String sql = "DELETE FROM Employees WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for (long id : ids) {
				if (id > 0) {
					stmt.setLong(1, id);
					stmt.execute();
					this.employees.removeIf(employee -> id == employee.getId());
				}
			}
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void search() {
		String sql = "SELECT * FROM Employees;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		Employee employee;
		this.employees.clear();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("id_card"));
				this.employees.add(employee);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS Employees(" + "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "name VARCHAR(255) NOT NULL UNIQUE," + "id_card VARCHAR(20) NOT NULL);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
