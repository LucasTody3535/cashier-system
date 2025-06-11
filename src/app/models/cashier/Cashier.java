package app.models.cashier;

import app.enums.cashier_state.CashierState;
import app.models.employee.Employee;

public class Cashier {
	private long id;
	private float vault;
	private Employee owner;
	private boolean isVaultOpen;
	private CashierState state;
	private String pdv;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getVault() {
		return this.vault;
	}

	public void setVault(float vault) {
		this.vault = vault;
	}

	public Employee getOwner() {
		return this.owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public boolean isVaultOpen() {
		return this.isVaultOpen;
	}

	private void setVaultOpen(boolean isVaultOpen) {
		this.isVaultOpen = isVaultOpen;
	}

	public CashierState getState() {
		return state;
	}

	public void setState(CashierState state) {
		this.state = state;
	}

	public String getPdv() {
		return this.pdv;
	}

	public void setPdv(String pdv) {
		this.pdv = pdv;
	}

	public boolean shouldDoCashout() {
		return this.vault > 1000;
	}

	public void openCashier() {
		this.setState(CashierState.OPENED);
	}

	public void closeCashier() {
		this.setState(CashierState.CLOSED);
	}

	public void toggleVault() {
		this.setVaultOpen(!this.isVaultOpen());
	}
}
