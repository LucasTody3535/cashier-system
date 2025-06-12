package app.database.dao.payment;

import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.payment.Payment;

public class PaymentDAO extends BaseDAO {
	private ArrayList<Payment> payments = new ArrayList<Payment>();

	public PaymentDAO(DBConnection connection) {
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
	public void search() {
		
	}

	@Override
	protected void createTable() {
		
	}
	
	public void add(Payment payment) {
		this.payments.add(payment);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Payment> getPayments() {
		return (ArrayList<Payment>) payments.clone();
	}
}
