package app.database.dao.invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.enums.payment_type.PaymentType;
import app.models.invoice.Invoice;
import app.models.product.Product;

public class InvoiceDAO extends BaseDAO {
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

	public InvoiceDAO(DBConnection connection) {
		super(connection);
		this.createTable();
	}

	@Override
	public boolean save() {
		DBConnection conn = this.getConnection();
		PreparedStatement highestId = conn.genPreparedStatement("SELECT MAX(id) as id FROM Invoices;");
		PreparedStatement invoiceTableStmt = conn.genPreparedStatement("INSERT INTO Invoices VALUES(?, ?, ?, ?, ?, ?);");
		PreparedStatement invoiceRelationTableStmt = conn.genPreparedStatement("INSERT INTO ProductsIntoInvoice VALUES(?, ?, ?);");
		ResultSet result;
		long generatedId;
		try {
			for(Invoice invoice : this.invoices) {
				invoiceTableStmt.setLong(1, invoice.getId());
				invoiceTableStmt.setFloat(2, invoice.getTotal());
				invoiceTableStmt.setString(3, invoice.getDoc());
				invoiceTableStmt.setString(4, invoice.getPdv());
				invoiceTableStmt.setString(5, invoice.getSupermarket());
				invoiceTableStmt.setString(6, invoice.getPaymentType().name());
				invoiceTableStmt.execute();
				result = highestId.executeQuery();
				result.next();
				generatedId = result.getLong("id");
				for(Product product: invoice.getProducts()) {
					invoiceRelationTableStmt.setLong(1, 0);
					invoiceRelationTableStmt.setLong(2, generatedId);
					invoiceRelationTableStmt.setLong(3, product.getId());
					invoiceRelationTableStmt.execute();
				}
			}
			highestId.close();
			invoiceTableStmt.close();
			invoiceRelationTableStmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update() {
		DBConnection conn = this.getConnection();
		String sql = "UPDATE Invoices "
			+ "SET total = ?, doc = ?, pdv = ?, "
			+ "supermarket = ?, payment_type = ? "
			+ "WHERE id = ?;";
		PreparedStatement invoiceTableStmt = conn.genPreparedStatement(sql);
		try {
			for(Invoice invoice : this.invoices) {
				invoiceTableStmt.setFloat(1, invoice.getTotal());
				invoiceTableStmt.setString(2, invoice.getDoc());
				invoiceTableStmt.setString(3, invoice.getPdv());
				invoiceTableStmt.setString(4, invoice.getSupermarket());
				invoiceTableStmt.setString(5, invoice.getPaymentType().name());
				invoiceTableStmt.setLong(1, invoice.getId());
				invoiceTableStmt.execute();
			}
			invoiceTableStmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(long[] ids) {
		String sql = "DELETE FROM Invoices WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(long id : ids) {
				if(id > 0) {
					stmt.setLong(1, id);
					stmt.execute();
					this.invoices.removeIf(invoice -> id == invoice.getId());
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
		String sql = "select * from invoices;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		Invoice invoice;
		this.invoices.clear();
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				invoice = new Invoice(
					rs.getLong("id"),
					null,
					rs.getFloat("total"),
					rs.getString("doc"),
					rs.getString("pdv"),
					rs.getString("supermarket"),
					PaymentType.valueOf(PaymentType.class, rs.getString("pdv"))
				);
				this.invoices.add(invoice);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
