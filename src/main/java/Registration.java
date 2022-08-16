import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;


import Model.User;
import Repositories.UserRepository;


public class Registration {

	//declare components
	private JFrame frame;
	private JTextField txtDOB;
	private JTextField txtMobile;
	private JTextField txtEmail;
	private JTextField txtName;
	private JComboBox<String> cmbGender;
	private JPasswordField txtPass;
	private JPasswordField txtPassConfirm;

	
	private String ID;
	private String name;
	private String gender;
	private String DOB;
	private String email;
	private String phone;
	private String password;
	
	
	/**
	 * Create the application.
	 */
	public Registration() {	
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 417, 470);
		frame.setTitle("Registration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblTitle = new JLabel("Librarian Registration");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		frame.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Name");
		frame.getContentPane().add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		springLayout.putConstraint(SpringLayout.WEST, lblTitle, 0, SpringLayout.WEST, lblGender);
		springLayout.putConstraint(SpringLayout.NORTH, lblGender, 26, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblGender, 80, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblGender);
		frame.getContentPane().add(lblGender);
		
		JLabel lblDOB = new JLabel("DOB");
		springLayout.putConstraint(SpringLayout.NORTH, lblDOB, 45, SpringLayout.SOUTH, lblGender);
		springLayout.putConstraint(SpringLayout.EAST, lblDOB, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblDOB);
		
		JLabel lblMobile = new JLabel("Mobile No.");
		springLayout.putConstraint(SpringLayout.NORTH, lblMobile, 26, SpringLayout.SOUTH, lblDOB);
		springLayout.putConstraint(SpringLayout.EAST, lblMobile, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblMobile);
		
		JLabel lblEmail = new JLabel("Email Address");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 26, SpringLayout.SOUTH, lblMobile);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblEmail);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -23, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomePage hp = new HomePage();
				hp.open();
				frame.dispose();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -48, SpringLayout.WEST, btnClear);
		springLayout.putConstraint(SpringLayout.NORTH, btnClear, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnClear, -182, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		JButton btnRegister = new JButton("Register");
		springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.WEST, btnRegister, 43, SpringLayout.EAST, btnClear);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRegister, -23, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtName.getText().isEmpty() || txtDOB.getText().isEmpty()|| txtEmail.getText().isEmpty() || txtMobile.getText().isEmpty() || txtPass.getPassword().length == 0 || txtPassConfirm.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "Please fill in all the fields to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);	
				} else if (txtName.getText() != null && txtDOB.getText() !=null && txtEmail.getText() !=null && txtMobile.getText() != null && txtPass.getPassword().length > 0 && txtPassConfirm.getPassword().length > 0) {	
					if (Arrays.equals(txtPass.getPassword(), txtPassConfirm.getPassword())) {
						
						ID = "0";
						name = txtName.getText();
						gender = (String) cmbGender.getSelectedItem();
						DOB = txtDOB.getText();
						email = txtEmail.getText();
						phone = txtMobile.getText();
						password = String.valueOf(txtPass.getPassword());
						
						User user = new User();
						user.setID(ID);
						user.setName(name);
						user.setGender(gender);
						user.setRole("librarian");
						user.setDOB(DOB);
						user.setEmail(email);
						user.setPhone(phone);
						user.setPassword(password);
						
						if(UserRepository.Register(user) == true) {
							JOptionPane.showMessageDialog(frame, "The registration is successful", "Registration Success!", JOptionPane.INFORMATION_MESSAGE);
							LoginPage lp = new LoginPage();
							lp.open();
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(frame, "Something went wrong.", "Register Failed...", JOptionPane.WARNING_MESSAGE);
						}
								
					} else {
						JOptionPane.showMessageDialog(frame, "The passwords are not the same, please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} 
			}
		});
		
		cmbGender = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.WEST, cmbGender, 34, SpringLayout.EAST, lblGender);
		springLayout.putConstraint(SpringLayout.SOUTH, cmbGender, 0, SpringLayout.SOUTH, lblGender);
		cmbGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		frame.getContentPane().add(cmbGender);
		
		txtDOB = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtDOB, 0, SpringLayout.WEST, cmbGender);
		springLayout.putConstraint(SpringLayout.SOUTH, txtDOB, 0, SpringLayout.SOUTH, lblDOB);
		frame.getContentPane().add(txtDOB);
		txtDOB.setColumns(10);
		
		txtMobile = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtMobile, 0, SpringLayout.WEST, cmbGender);
		springLayout.putConstraint(SpringLayout.SOUTH, txtMobile, 0, SpringLayout.SOUTH, lblMobile);
		springLayout.putConstraint(SpringLayout.EAST, txtMobile, -86, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(txtMobile);
		txtMobile.setColumns(20);
		
		txtEmail = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 0, SpringLayout.WEST, cmbGender);
		springLayout.putConstraint(SpringLayout.SOUTH, txtEmail, 0, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, txtEmail, -98, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(25);
		
		txtName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 78, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTitle, -25, SpringLayout.NORTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, txtName, 34, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, txtName, -91, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, 0, SpringLayout.SOUTH, txtName);
		frame.getContentPane().add(txtName);
		txtName.setColumns(30);
		
		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 26, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblConfirm, 20, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblConfirm, 0, SpringLayout.EAST, lblName);
		frame.getContentPane().add(lblConfirm);
		
		txtPass = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPass, -3, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, txtPass, 34, SpringLayout.EAST, lblPassword);
		txtPass.setColumns(20);
		frame.getContentPane().add(txtPass);
		
		txtPassConfirm = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, txtPassConfirm, 34, SpringLayout.EAST, lblConfirm);
		springLayout.putConstraint(SpringLayout.EAST, txtPassConfirm, -115, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtPass, 0, SpringLayout.EAST, txtPassConfirm);
		springLayout.putConstraint(SpringLayout.NORTH, txtPassConfirm, -3, SpringLayout.NORTH, lblConfirm);
		txtPassConfirm.setColumns(20);
		frame.getContentPane().add(txtPassConfirm);
	}
	
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clear() {
		txtName.setText(null);
		txtDOB.setText(null);
		txtEmail.setText(null);
		txtMobile.setText(null);
		txtPass.setText(null);
		txtPassConfirm.setText(null);
		cmbGender.setSelectedItem("Male");
	}
}
