package app.database.dao.invoice;

import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.invoice.Invoice;

public class InvoiceDAO extends BaseDAO {
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

	public InvoiceDAO(DBConnection connection) {
		super(connection);
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
	protected void createTable() {}

	public void add(Invoice invoice) {
		this.invoices.add(invoice);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Invoice> getInvoices() {
		return (ArrayList<Invoice>) this.invoices.clone();
	}
}
