import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import Model.Cart;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.util.Properties;

import Repositories.BookRepository;
import Repositories.CartRepository;
import Repositories.UserRepository;

public class BorrowBook {

	private JFrame frame;
	private JTextField txtSearchID;
	private JComboBox<String> cmbUserCategory;
	private JTextField txtSearchISBN;
	private JButton btnSearchISBN;
	
	public String username;
	public String userRole;
	public String userID;
	
	
	private String name;
	private String role;
	private String author;
	private String publisher;
	private String id;
	private String ISBN;
	private String title;
	private String category;
	private Boolean status;
	private int duration;
	private LocalDate borrow;
	private LocalDate due;
	private String dueDate;
	private String borrowDate;
	
	/**
	 * Create the application.
	 */
	public BorrowBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("APU Library Service System");
		frame.setBounds(100, 100, 548, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 376, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 532, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(429, 11, 93, 23);
		panel.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(btnLogOut, "Are you sure you want to log out?", "Wait!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(frame, "Return to home page.", "Logged out.", JOptionPane.INFORMATION_MESSAGE);
					HomePage hp = new HomePage();
					hp.open();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Log out cancelled.", "Action cancelled.", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		JLabel lblTitle = new JLabel("APU Library Service System");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTitle.setBounds(111, 43, 288, 26);
		panel.add(lblTitle);
		
		JButton btnBookList = new JButton("Book List");
		btnBookList.setBounds(10, 79, 113, 23);
		panel.add(btnBookList);
		btnBookList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.open();
				frame.dispose();
			}
		});
		
		JButton btnEditBook = new JButton("Edit Book List");
		btnEditBook.setBounds(127, 79, 132, 23);
		panel.add(btnEditBook);
		btnEditBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditBookList ebl = new EditBookList();
				ebl.open();
				frame.dispose();
			}
		});
		
		JButton btnBorrow = new JButton("Borrow Books");
		btnBorrow.setBounds(265, 79, 119, 23);
		panel.add(btnBorrow);
		btnBorrow.setEnabled(false);
		
		JButton btnCart = new JButton("Borrow Cart");
		btnCart.setBounds(390, 79, 128, 23);
		panel.add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BorrowCart bc = new BorrowCart();
				bc.open();
				frame.dispose();
			}
		});
		
		JLabel lblSerachUsersby = new JLabel("Serach User (by ID):");
		lblSerachUsersby.setBounds(185, 117, 132, 14);
		panel.add(lblSerachUsersby);
		
		txtSearchID = new JTextField();
		txtSearchID.setColumns(13);
		txtSearchID.setBounds(299, 114, 116, 20);
		panel.add(txtSearchID);
		
		JLabel lblUserCategory = new JLabel("User Category:");
		lblUserCategory.setBounds(10, 117, 79, 14);
		panel.add(lblUserCategory);
		
		cmbUserCategory = new JComboBox<String>();
		cmbUserCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"student", "staff"}));
		cmbUserCategory.setBounds(99, 113, 79, 22);
		panel.add(cmbUserCategory);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(425, 113, 93, 23);
		panel.add(btnSearch);
		
		final JLabel lblSearch = new JLabel("Serach (by ISBN):");
		lblSearch.setBounds(99, 171, 117, 14);
		panel.add(lblSearch);
		lblSearch.setVisible(false);
		
		txtSearchISBN = new JTextField();
		txtSearchISBN.setColumns(13);
		txtSearchISBN.setBounds(203, 168, 128, 20);
		panel.add(txtSearchISBN);
		txtSearchISBN.setVisible(false);
		
		final JPanel BookInfo = new JPanel();
		BookInfo.setBounds(121, 204, 379, 148);
		panel.add(BookInfo);
		BookInfo.setLayout(null);
		BookInfo.setVisible(false);
		
		JLabel lblBookTitle = new JLabel("Book Title: ");
		lblBookTitle.setBounds(0, 0, 66, 14);
		BookInfo.add(lblBookTitle);
		
		final JLabel lblOutputTitle = new JLabel("Sample Title");
		lblOutputTitle.setBounds(103, 0, 248, 14);
		BookInfo.add(lblOutputTitle);
		
		JLabel lblAuthor = new JLabel("Book Author:");
		lblAuthor.setBounds(0, 25, 78, 14);
		BookInfo.add(lblAuthor);
		
		final JLabel lblOutputAuthor = new JLabel("Sample Name");
		lblOutputAuthor.setBounds(104, 25, 144, 14);
		BookInfo.add(lblOutputAuthor);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setBounds(0, 50, 66, 14);
		BookInfo.add(lblPublisher);
		
		final JLabel lblOutputPublisher = new JLabel("Sample Publisher");
		lblOutputPublisher.setBounds(103, 50, 162, 14);
		BookInfo.add(lblOutputPublisher);
		BookInfo.setVisible(false);
		
		JLabel lblCategory = new JLabel("Book Category:");
		lblCategory.setBounds(0, 75, 95, 14);
		BookInfo.add(lblCategory);
		
		final JLabel lblOutputCategory = new JLabel("Sample Category");
		lblOutputCategory.setBounds(105, 75, 102, 14);
		BookInfo.add(lblOutputCategory);
		
		JLabel lblDuration = new JLabel("Borrow Duration:");
		lblDuration.setBounds(0, 100, 95, 14);
		BookInfo.add(lblDuration);
		
		final JLabel lblOutputDuration = new JLabel("Sample Duration");
		lblOutputDuration.setBounds(103, 100, 104, 14);
		BookInfo.add(lblOutputDuration);
		
		
		
		final JLabel lblUserRole = new JLabel("User role:");
		lblUserRole.setBounds(359, 142, 75, 14);
		panel.add(lblUserRole);
		lblUserRole.setVisible(false);
		
		final JLabel lblOutputRole = new JLabel("sample role");
		lblOutputRole.setBounds(440, 142, 82, 14);
		panel.add(lblOutputRole);
		lblOutputRole.setVisible(false);
		
		final JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setBounds(10, 142, 65, 14);
		panel.add(lblUserID);
		lblUserID.setVisible(false);
		
		final JLabel lblOutputID = new JLabel("sample id");
		lblOutputID.setBounds(63, 142, 46, 14);
		panel.add(lblOutputID);
		lblOutputID.setVisible(false);
		
		final JLabel lblUsername = new JLabel("Name:");
		lblUsername.setBounds(141, 142, 46, 14);
		panel.add(lblUsername);
		lblUsername.setVisible(false);
		
		final JLabel lblOutputName = new JLabel("sample name");
		lblOutputName.setBounds(181, 142, 136, 14);
		panel.add(lblOutputName);
		lblOutputName.setVisible(false);
		
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSearchID.getText().isEmpty()) {
					lblUserID.setVisible(false);
					lblOutputID.setVisible(false);
					lblUsername.setVisible(false);
					lblOutputName.setVisible(false);
					lblUserRole.setVisible(false);
					lblOutputRole.setVisible(false);
					lblSearch.setVisible(false);
					txtSearchISBN.setVisible(false);
					btnSearchISBN.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Please fill in the field to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					if (cmbUserCategory.getSelectedItem().toString().equals("student")) { //chcek if the role = student
						lblUserID.setVisible(true);
						lblOutputID.setVisible(true);
						lblUsername.setVisible(true);
						lblOutputName.setVisible(true);
						lblUserRole.setVisible(true);
						lblOutputRole.setVisible(true);
						lblSearch.setVisible(true);
						txtSearchISBN.setVisible(true);
						btnSearchISBN.setVisible(true);
						id = txtSearchID.getText();
						role = "student";
						if(UserRepository.checkStudent(id, name, role)== null) { //check whether the input id, name and role exists in the json file
							lblUserID.setVisible(false);
							lblOutputID.setVisible(false);
							lblUsername.setVisible(false);
							lblOutputName.setVisible(false);
							lblUserRole.setVisible(false);
							lblOutputRole.setVisible(false);
							lblSearch.setVisible(false);
							txtSearchISBN.setVisible(false);
							btnSearchISBN.setVisible(false);
							JOptionPane.showMessageDialog(frame, "Such user does not exist.", "Warning", JOptionPane.WARNING_MESSAGE);
							name = UserRepository.checkStudent(id, name, role);
						} else {
							lblOutputID.setText(id);
							lblOutputName.setText(name);
							lblOutputRole.setText("student");
							JOptionPane.showMessageDialog(frame, "Student found.", "Search successful", JOptionPane.INFORMATION_MESSAGE);
						}
					} else { //when the role is staff
						lblUserID.setVisible(true);
						lblOutputID.setVisible(true);
						lblUsername.setVisible(true);
						lblOutputName.setVisible(true);
						lblUserRole.setVisible(true);
						lblOutputRole.setVisible(true);
						lblSearch.setVisible(true);
						txtSearchISBN.setVisible(true);
						btnSearchISBN.setVisible(true);
						id = txtSearchID.getText();
						role = "staff";
						if(UserRepository.checkStaff(id, name, role) == null) {
							lblUserID.setVisible(false);
							lblOutputID.setVisible(false);
							lblUsername.setVisible(false);
							lblOutputName.setVisible(false);
							lblUserRole.setVisible(false);
							lblOutputRole.setVisible(false);
							lblSearch.setVisible(false);
							txtSearchISBN.setVisible(false);
							btnSearchISBN.setVisible(false);
							JOptionPane.showMessageDialog(frame, "Such ID does not exist.", "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							name = UserRepository.checkStaff(id, name, role);
							lblOutputID.setText(id);
							lblOutputName.setText(name);
							lblOutputRole.setText("staff");
							JOptionPane.showMessageDialog(frame, "Staff found.", "Search successful", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		
		btnSearchISBN = new JButton("Search");
		btnSearchISBN.setBounds(341, 167, 95, 23);
		panel.add(btnSearchISBN);
		btnSearchISBN.setVisible(false);
		btnSearchISBN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSearchISBN.getText().isEmpty()) {
					BookInfo.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Please fill in the field to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else { 
					if(lblOutputRole.getText().equals("student")) {
						ISBN = txtSearchISBN.getText();
						if(BookRepository.showDetail(ISBN) == null) {
							JOptionPane.showMessageDialog(frame, "Such book does not exist.", "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							String[] details = BookRepository.showDetail(ISBN);
							title = details[0];
							category = details[1];
							author = details[2];
							publisher = details[3];
								BookInfo.setVisible(true);
								lblOutputTitle.setText(title);
								lblOutputCategory.setText(category);
								lblOutputAuthor.setText(author);
								lblOutputPublisher.setText(publisher);
								if (lblOutputCategory.getText().equals("book")) {
									duration = 14;
									lblOutputDuration.setText(duration + " days");
								} else if (lblOutputCategory.getText().equals("study material")) {
									duration = 21;
									lblOutputDuration.setText(duration + " days");
								} else if (lblOutputCategory.getText().equals("magazine")) {
									duration = 7;
									lblOutputDuration.setText(duration + " days");
								}
								JOptionPane.showMessageDialog(frame, "Book found.", "Search successful", JOptionPane.INFORMATION_MESSAGE);
						}

					} else if(lblOutputRole.getText().equals("staff")) {
						ISBN = txtSearchISBN.getText();
							if(BookRepository.showDetail(ISBN) == null) {
								JOptionPane.showMessageDialog(frame, "Such book does not exist.", "Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							String[] details = BookRepository.showDetail(ISBN);
							title = details[0];
							category = details[1];
							author = details[2];
							publisher = details[3];
								BookInfo.setVisible(true);
								lblOutputTitle.setText(title);
								lblOutputCategory.setText(category);
								lblOutputAuthor.setText(author);
								lblOutputPublisher.setText(publisher);
								if (lblOutputCategory.getText().equals("book")) {
									duration = 14;
									lblOutputDuration.setText(duration + " days");
								} else if (lblOutputCategory.getText().equals("study material")) {
									duration = 7;
									lblOutputDuration.setText(duration + " days");
								} else if (lblOutputCategory.getText().equals("magazine")) {
									duration = 7;
									lblOutputDuration.setText(duration + " days");
								}
								JOptionPane.showMessageDialog(frame, "Book found.", "Search successful", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		
		final JButton btnConfirm = new JButton("Confirm Borrow");
		btnConfirm.setBounds(262, 125, 117, 23);
		BookInfo.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(btnConfirm, "Borrow this book?", "Wait!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (result == JOptionPane.YES_OPTION) {
					
					id = lblOutputID.getText();
					ISBN = txtSearchISBN.getText();
					title = lblOutputTitle.getText();
					borrow = LocalDate.now();
					borrowDate = borrow.toString();
					category = lblOutputCategory.getText();
					if (category.equals("book")) {
						duration = 14;
					} else if (lblOutputRole.getText().equals("student")&& category.equals("study material")) {
						duration = 21;
					} else if (lblOutputRole.getText().equals("staff")&& category.equals("study material")) {
						duration = 7;
					} else if (category.equals("magazine")) {
						duration = 7;
					}
					due = borrow.plusDays(duration);
					dueDate = due.toString();
					status = false;
					
					if (CartRepository.checkExisted(id, ISBN, title, category) == true) {
						JOptionPane.showMessageDialog(frame, "This user has borrowed this book.", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						Cart cart = new Cart();
						cart.setId(id);
						cart.setISBN(ISBN);
						cart.setTitle(title);
						cart.setBorrowDate(borrowDate);
						cart.setCategory(category);
						cart.setDuration(duration);
						cart.setDueDate(dueDate);
						cart.setStatus(status);
						
						if(CartRepository.add(cart) == true) {
							JOptionPane.showMessageDialog(frame, "The book is successfully borrowed.", "Borrow Success!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame, "Something went wrong.", "Register Failed...", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
	}

	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBook window = new BorrowBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
