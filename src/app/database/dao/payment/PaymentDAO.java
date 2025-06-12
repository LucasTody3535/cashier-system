package app.database.dao.payment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.payment.Payment;

public class PaymentDAO extends BaseDAO {
	private ArrayList<Payment> payments = new ArrayList<Payment>();

	public PaymentDAO(DBConnection connection) {
		super(connection);
		this.createTable();
	}

	@Override
	public boolean save() {
		String sql = "INSERT INTO Payments VALUES(?, ?, ?);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(Payment payment : this.payments) {
				if(payment.getId() == 0) {
					stmt.setLong(1, payment.getId());
					stmt.setLong(2, payment.getDate().getTimeInMillis());
					stmt.setFloat(3, payment.getInvoice().getId());
					stmt.execute();
					payment.setId(-1);
				}
			}
			this.payments.removeIf(payment -> payment.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update() {
		String sql = "UPDATE Payments SET date = ? WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(Payment payment : this.payments) {
				if(payment.getId() > 0) {
					stmt.setLong(1, payment.getDate().getTimeInMillis());
					stmt.setLong(2, payment.getId());
					stmt.execute();
					payment.setId(-1);
				}
			}
			this.payments.removeIf(payment -> payment.getId() == -1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(long[] ids) {
		return false;
	}

	@Override
	public void search() {
		
	}

	@Override
	protected void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS Payments("
				+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "date BIGINT NOT NULL,"
				+ "invoice_id INTEGER NOT NULL UNIQUE,"
				+ "FOREIGN KEY (invoice_id) REFERENCES Invoices(id));";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Payment payment) {
		this.payments.add(payment);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Payment> getPayments() {
		return (ArrayList<Payment>) payments.clone();
	}
}
