package app.database.dao.payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.enums.payment_type.PaymentType;
import app.models.invoice.Invoice;
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
		String sql = "DELETE FROM Payments WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(long id : ids) {
				if(id > 0) {
					stmt.setLong(1, id);
					stmt.execute();
					this.payments.removeIf(payment -> id == payment.getId());
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
		String sql = "SELECT payments.id as p_id, payments.date, "
				+ "invoices.id as i_id, invoices.total, invoices.doc, invoices.pdv, invoices.supermarket, invoices.payment_type "
				+ "FROM Payments INNER JOIN Invoices ON payments.invoice_id = invoices.id;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		Payment payment;
		Invoice invoice;
		this.payments.clear();
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getLong("date"));
				invoice = new Invoice(
					rs.getLong("i_id"),
					null,
					rs.getFloat("total"),
					rs.getString("doc"),
					rs.getString("pdv"),
					rs.getString("supermarket"),
					PaymentType.valueOf(PaymentType.class, rs.getString("payment_type"))
				);
				payment = new Payment(rs.getLong("p_id"), cal, invoice);
				this.payments.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
