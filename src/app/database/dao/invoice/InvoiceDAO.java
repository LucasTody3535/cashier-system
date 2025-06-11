package app.database.dao.invoice;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.invoice.Invoice;

public class InvoiceDAO extends BaseDAO {
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

	public InvoiceDAO(DBConnection connection) {
		super(connection);
		this.createTable();
	}

	@Override
	public boolean save() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete(long[] ids) {
		return false;
	}

	@Override
	public void search() {}

	@Override
	protected void createTable() {
		String sql;
		DBConnection conn = this.getConnection();
		PreparedStatement stmt;
		try {
			sql = "CREATE TABLE IF NOT EXISTS Invoices("
					+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
					+ "total FLOAT NOT NULL,"
					+ "doc VARCHAR(255) NOT NULL,"
					+ "pdv VARCHAR(255) NOT NULL,"
					+ "supermarket VARCHAR(255) NOT NULL,"
					+ "payment_type ENUM('MONEY', 'CREDIT_CARD', 'DEBIT', 'PIX', 'POS') NOT NULL"
				  + ");";
			stmt = conn.genPreparedStatement(sql);
			stmt.execute();
			stmt.close();
			sql = "CREATE TABLE IF NOT EXISTS ProductsIntoInvoice("
					+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
					+ "invoice_id INTEGER NOT NULL,"
					+ "product_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (invoice_id) REFERENCES Invoices(id),"
					+ "FOREIGN KEY (product_id) REFERENCES Products(id)"
					+ ");";
			stmt = conn.genPreparedStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(Invoice invoice) {
		this.invoices.add(invoice);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Invoice> getInvoices() {
		return (ArrayList<Invoice>) this.invoices.clone();
	}
}
