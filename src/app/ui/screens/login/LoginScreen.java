package app.ui.screens.login;

import javax.swing.JFrame;

import app.database.connection.DBConnection;
import app.interfaces.navigatable_screen.NavigatableScreen;

public class LoginScreen implements NavigatableScreen {
	private DBConnection connection;
	private JFrame frame;

	public LoginScreen(DBConnection connection) {
		this.setConnection(connection);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
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
