import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); 
		frame.setTitle("Home Page");
		frame.setBounds(200, 200, 450, 254);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		springLayout.putConstraint(SpringLayout.NORTH, lblWelcome, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWelcome, 165, SpringLayout.WEST, frame.getContentPane());
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Monotype Corsiva", Font.BOLD, 26));
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblTitle = new JLabel("Asia Pacific University Library Service System");
		springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 6, SpringLayout.SOUTH, lblWelcome);
		springLayout.putConstraint(SpringLayout.WEST, lblTitle, 39, SpringLayout.WEST, frame.getContentPane());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblTitle);
		
		JButton btnRegister = new JButton("Register");
		springLayout.putConstraint(SpringLayout.WEST, btnRegister, 259, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnRegister, 0, SpringLayout.EAST, lblTitle);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Registration reg = new Registration();
				reg.open(); //The page related to reg which is Registration.java will be opened
				frame.dispose();
			}
		});
		
		JButton btnLogin = new JButton("Log in");
		springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 0, SpringLayout.NORTH, btnLogin);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRegister, 0, SpringLayout.SOUTH, btnLogin);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -63, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -261, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 39, SpringLayout.SOUTH, lblTitle);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 40, SpringLayout.WEST, frame.getContentPane());
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage lp = new LoginPage();
				lp.open();
				frame.dispose();
			}
		});
	}

	public void open() { //run the window
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
