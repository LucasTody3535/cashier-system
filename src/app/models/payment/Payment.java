package app.models.payment;

import java.util.Calendar;

import app.models.invoice.Invoice;

public class Payment {
	private long id;
	private Calendar date;
	private Invoice invoice;
	
	public Payment(long id, Calendar date, Invoice invoice) {
		this.setId(id);
		this.setDate(date);
		this.setInvoice(invoice);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
