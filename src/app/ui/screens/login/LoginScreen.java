package app.ui.screens.login;

import javax.swing.JFrame;

import app.database.connection.DBConnection;
import app.interfaces.navigatable_screen.NavigatableScreen;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoginScreen implements NavigatableScreen {
	private DBConnection connection;
	private JFrame frame;
	private JTextField textField;

	public LoginScreen(DBConnection connection) {
		this.setConnection(connection);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(122, 51, 180, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(132, 85, 157, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String employeeId = textField.getText();
				System.out.println(employeeId);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao cashtron 2000");
		lblNewLabel.setBounds(126, 175, 195, 14);
		frame.getContentPane().add(lblNewLabel);
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
