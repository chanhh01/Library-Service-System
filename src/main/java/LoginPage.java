import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Repositories.UserRepository;

public class LoginPage {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtPass;

	private String email;
	private String pass;
	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 351, 241);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblTitle = new JLabel("Librarian Log In");
		springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTitle, 80, SpringLayout.WEST, frame.getContentPane());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		frame.getContentPane().add(lblTitle);
		
		JLabel lblEmail = new JLabel("Email Address");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 31, SpringLayout.SOUTH, lblTitle);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, -18, SpringLayout.WEST, txtEmail);
		springLayout.putConstraint(SpringLayout.EAST, txtEmail, -49, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 28, SpringLayout.SOUTH, lblTitle);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, -216, SpringLayout.EAST, frame.getContentPane());
		txtEmail.setColumns(25);
		frame.getContentPane().add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 35, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -17, SpringLayout.SOUTH, frame.getContentPane());
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage hp = new HomePage();
				hp.open();
				frame.dispose();
			}
		});
		
		JButton btnLogin = new JButton("Log in");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 60, SpringLayout.SOUTH, txtEmail);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -17, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -79, SpringLayout.WEST, btnLogin);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, -124, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -27, SpringLayout.EAST, frame.getContentPane());
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEmail.getText().isEmpty()|| txtPass.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "Please fill in all the fields to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (!txtEmail.getText().isEmpty() && txtPass.getPassword().length != 0) {
					
					email = txtEmail.getText();
					pass = String.valueOf(txtPass.getPassword());
					
					if (UserRepository.login(email, pass) == true) {
						JOptionPane.showMessageDialog(frame, "The log in is successful", "Log in Success!", JOptionPane.INFORMATION_MESSAGE);
						BorrowBook bb = new BorrowBook();
						bb.open();
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "Invalid email or password, please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		txtPass = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, txtPass, 119, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtPass, -65, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 3, SpringLayout.NORTH, txtPass);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -18, SpringLayout.WEST, txtPass);
		springLayout.putConstraint(SpringLayout.NORTH, txtPass, 16, SpringLayout.SOUTH, txtEmail);
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 27, SpringLayout.SOUTH, txtPass);
		txtPass.setColumns(20);
		frame.getContentPane().add(txtPass);
	}
	
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
