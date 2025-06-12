package app.database.dao.cash_draw;

import java.util.ArrayList;

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
	
	public void add(CashDraw cashDraw) {
		this.cashDraws.add(cashDraw);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CashDraw> getCashDraws() {
		return (ArrayList<CashDraw>) cashDraws.clone();
	}
}
