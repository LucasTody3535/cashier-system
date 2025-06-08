package app.models.card;

import app.enums.bank_flag.BankFlag;

public class Card {
	private long id;
	private int agencyNumber;
	private int accountNumber;
	private BankFlag flag;
	
	public Card(long id, int agencyNumber, int accountNumber, BankFlag flag) {
		this.setId(id);
		this.setAgencyNumber(agencyNumber);
		this.setAccountNumber(accountNumber);
		this.setFlag(flag);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(int agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BankFlag getFlag() {
		return flag;
	}

	public void setFlag(BankFlag flag) {
		this.flag = flag;
	}
}
