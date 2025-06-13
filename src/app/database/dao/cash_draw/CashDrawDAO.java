package app.database.dao.cash_draw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.cashDraw.CashDraw;

public class CashDrawDAO extends BaseDAO {
	private ArrayList<CashDraw> cashDraws = new ArrayList<CashDraw>();

	public CashDrawDAO(DBConnection connection) {
		super(connection);
	}

	@Override
	public boolean save() {
		String sql = "INSERT INTO CashDraw VALUES(?, ?, ?, ?);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(CashDraw cashDraw : this.cashDraws) {
				if(cashDraw.getId() == 0) {
					stmt.setLong(1, cashDraw.getId());
					stmt.setLong(2, cashDraw.getDate().getTimeInMillis());
					stmt.setFloat(3, cashDraw.getValue());
					stmt.setString(4, cashDraw.getCashierPdv());
					stmt.execute();
					cashDraw.setId(-1);
				}
			}
			this.cashDraws.removeIf(cashDraw -> cashDraw.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update() {
		String sql = "UPDATE CashDraws SET date = ?, value = ?, cashier_pdv = ? WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(CashDraw cashDraw : this.cashDraws) {
				if(cashDraw.getId() > 0) {
					stmt.setLong(1, cashDraw.getDate().getTimeInMillis());
					stmt.setFloat(2, cashDraw.getValue());
					stmt.setString(3, cashDraw.getCashierPdv());
					stmt.setLong(4, cashDraw.getId());
					stmt.execute();
					cashDraw.setId(-1);
				}
			}
			this.cashDraws.removeIf(cashDraw -> cashDraw.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(long[] ids) {
		String sql = "DELETE FROM CashDraws WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(long id : ids) {
				if(id > 0) {
					stmt.setLong(1, id);
					stmt.execute();
					this.cashDraws.removeIf(cashDraw -> id == cashDraw.getId());
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
		String sql = "SELECT * FROM CashDraws;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		CashDraw cashDraw;
		Calendar cal;
		this.cashDraws.clear();
		try {
			ResultSet rs = stmt.executeQuery();
			cal = Calendar.getInstance();
			cal.setTimeInMillis(rs.getLong("date"));
			while(rs.next()) {
				cashDraw = new CashDraw(
					rs.getLong("id"),
					rs.getString("cashier_pdv"),
					rs.getFloat("value"),
					cal
				);
				this.cashDraws.add(cashDraw);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS CashDraws("
				+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "date BIGINT NOT NULL,"
				+ "value FLOAT NOT NULL,"
				+ "cashier_pdv VARCHAR(2) NOT NULL);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(CashDraw cashDraw) {
		this.cashDraws.add(cashDraw);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CashDraw> getCashDraws() {
		return (ArrayList<CashDraw>) cashDraws.clone();
	}
}
