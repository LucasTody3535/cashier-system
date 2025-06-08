package app.models.product;

public class Product {
	private long id;
	private String name;
	private float price;
	private String barcode;

	public Product(long id, String name, float price, String barcode) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setBarcode(barcode);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
