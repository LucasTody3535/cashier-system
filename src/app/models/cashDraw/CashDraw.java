package app.models.cashDraw;

import java.util.Calendar;

public class CashDraw {
	private long id;
	private String cashierPdv;
	private float value;
	private Calendar date;

	public CashDraw(long id, String pdv, float value, Calendar date) {
		this.setId(id);
		this.setCashierPdv(pdv);
		this.setValue(value);
		this.setDate(date);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCashierPdv() {
		return this.cashierPdv;
	}

	public void setCashierPdv(String cashierPdv) {
		this.cashierPdv = cashierPdv;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}
