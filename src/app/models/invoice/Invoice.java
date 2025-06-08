package app.models.invoice;

import java.util.ArrayList;

import app.enums.payment_type.PaymentType;
import app.models.product.Product;

public class Invoice {
	private long id;
	private ArrayList<Product> products;
	private float total;
	private String doc;
	private String pdv;
	private String supermarket;
	private PaymentType paymentType;
	
	public Invoice(long id, ArrayList<Product> products, float total, String doc, String pdv, String supermarket, PaymentType paymentType) {
		this.setId(id);
		this.setProducts(products);
		this.setTotal(total);
		this.setDoc(doc);
		this.setPdv(pdv);
		this.setSupermarket(supermarket);
		this.setPaymentType(paymentType);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getPdv() {
		return pdv;
	}

	public void setPdv(String pdv) {
		this.pdv = pdv;
	}

	public String getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(String supermarket) {
		this.supermarket = supermarket;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
