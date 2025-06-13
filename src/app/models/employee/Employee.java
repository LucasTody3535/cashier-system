package app.models.employee;

public class Employee {
	private long id;
	private String name;
	private String idCard;

	public Employee(long id, String name, String idCard) {
		this.setId(id);
		this.setName(name);
		this.setIdCard(idCard);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}