package app.ui.screens.login;

import app.database.connection.DBConnection;
import app.interfaces.navigatable_screen.NavigatableScreen;

public class LoginScreen implements NavigatableScreen {
	private DBConnection connection;

	public LoginScreen(DBConnection connection) {
		this.setConnection(connection);
	}

	public DBConnection getConnection() {
		return this.connection;
	}

	public void setConnection(DBConnection connection) {
		this.connection = connection;
	}

	public void login(String idCard) {

	}

	@Override
	public void next() {

	}

	@Override
	public void back() {
		return;
	}
}
