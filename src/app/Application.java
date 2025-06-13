package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.database.connection.DBConnection;
import app.ui.screens.login.LoginScreen;

import javax.swing.JSpinner;

public class Application {

	private JFrame frame;
	private JTextField textField;
	private static DBConnection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final String URL = "jdbc:mysql://127.0.0.1:3306/db";
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String USER = "";
		final String PASSWORD = "";
		Application.connection = new DBConnection(URL, DRIVER, USER, PASSWORD);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen loginScreen = new LoginScreen(null);
					loginScreen.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.WEST);
		textField.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		frame.getContentPane().add(spinner, BorderLayout.CENTER);
	}

}
