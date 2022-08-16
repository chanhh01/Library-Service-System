import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;

import Model.Cart;

import Repositories.BookRepository;
import Repositories.CartRepository;


public class BorrowCart {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	
	private String id;
	private String ISBN;
	private String title;
	private String category;
	private String borrowDate;
	private int duration;
	private String dueDate;
	private long dayLeft;
	private boolean status;
	private int sum =0;

	/**
	 * Create the application.
	 */
	public BorrowCart() {
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
		
		final JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 376, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 532, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		final JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(423, 11, 99, 23);
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
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BorrowBook bb = new BorrowBook();
				bb.open();
				frame.dispose();
			}
		});
		
		JButton btnCart = new JButton("Borrow Cart");
		btnCart.setBounds(390, 79, 128, 23);
		panel.add(btnCart);
		btnCart.setEnabled(false);
		
		JLabel lblSerachBooksby = new JLabel("Serach User (by ID):");
		lblSerachBooksby.setBounds(96, 113, 119, 14);
		panel.add(lblSerachBooksby);
		
		txtSearch = new JTextField();
		txtSearch.setColumns(13);
		txtSearch.setBounds(225, 110, 79, 20);
		panel.add(txtSearch);
		
		
			
		
		final JPanel BorrowInfo = new JPanel();
		BorrowInfo.setBounds(10, 144, 337, 173);
		panel.add(BorrowInfo);
		BorrowInfo.setLayout(null);
		BorrowInfo.setVisible(false);
		
		JLabel lblTitle_1 = new JLabel("Book Title: ");
		lblTitle_1.setBounds(0, 0, 79, 14);
		BorrowInfo.add(lblTitle_1);
		
		final JLabel lblOutputTitle = new JLabel("Sample Title");
		lblOutputTitle.setBounds(89, 0, 248, 14);
		BorrowInfo.add(lblOutputTitle);
		
		JLabel lblCategory = new JLabel("Book Category:");
		lblCategory.setBounds(0, 25, 93, 14);
		BorrowInfo.add(lblCategory);
		
		final JLabel lblOutputCategory = new JLabel("Sample Category");
		lblOutputCategory.setBounds(89, 25, 102, 14);
		BorrowInfo.add(lblOutputCategory);
		
		JLabel lblDurationLeft = new JLabel("Duration left:");
		lblDurationLeft.setBounds(0, 125, 93, 14);
		BorrowInfo.add(lblDurationLeft);
		
		final JLabel lblOutputDuration = new JLabel("Sample Duration");
		lblOutputDuration.setBounds(89, 125, 102, 14);
		BorrowInfo.add(lblOutputDuration);
		
		JLabel lblBorrowDate = new JLabel("Borrowed on:");
		lblBorrowDate.setBounds(0, 50, 78, 14);
		BorrowInfo.add(lblBorrowDate);
		
		final JLabel lblOutputBorrow = new JLabel("xxxx-xx-xx");
		lblOutputBorrow.setBounds(89, 50, 89, 14);
		BorrowInfo.add(lblOutputBorrow);
		
		JLabel lblDueDate = new JLabel("Due on:");
		lblDueDate.setBounds(0, 100, 78, 14);
		BorrowInfo.add(lblDueDate);
		
		final JLabel lblOutputDue = new JLabel("xxxx-xx-xx");
		lblOutputDue.setBounds(89, 100, 80, 14);
		BorrowInfo.add(lblOutputDue);
		
		final JLabel lblOutputInitialDuration = new JLabel("Sample Duration");
		lblOutputInitialDuration.setBounds(90, 75, 141, 14);
		BorrowInfo.add(lblOutputInitialDuration);
		
		JLabel lblInitialDuration = new JLabel("Loan Duration:");
		lblInitialDuration.setBounds(0, 75, 93, 14);
		BorrowInfo.add(lblInitialDuration);
		
		final JLabel lblFine = new JLabel("Fine for late return (As of today):");
		lblFine.setBounds(10, 331, 186, 14);
		panel.add(lblFine);
		lblFine.setVisible(false);
		
		final JLabel lblOutputFine = new JLabel("RM X.XX");
		lblOutputFine.setBounds(10, 351, 264, 14);
		panel.add(lblOutputFine);
		lblOutputFine.setVisible(false);
		
		JButton btnReturn = new JButton("Return ");
		btnReturn.setBounds(0, 150, 89, 23);
		BorrowInfo.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = txtSearch.getText();
				int i = table.getSelectedRow();
				ISBN = table.getValueAt(i, 0).toString(); 
				
				if (CartRepository.returnBook(id, ISBN) == true) {
						if(!CartRepository.filter(id).isEmpty()) {
						
						BorrowCart bc = new BorrowCart();
						bc.open();
						frame.dispose();
							
					} else {
						JOptionPane.showMessageDialog(frame, "This user does not have any borrowed book in cart.", "No books in cart.", JOptionPane.INFORMATION_MESSAGE);
						BorrowCart bc = new BorrowCart();
						bc.open();
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Something went wrong.", "Error.", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		final JButton btnRenew = new JButton("Renew");
		btnRenew.setBounds(102, 150, 89, 23);
		BorrowInfo.add(btnRenew);
		btnRenew.setEnabled(false);
		btnRenew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = txtSearch.getText();
				int i = table.getSelectedRow();
				ISBN = table.getValueAt(i, 0).toString(); 
				dueDate = lblOutputDue.getText();
				
				if(CartRepository.showDuration(id, ISBN, duration) != 0) {
					duration = CartRepository.showDuration(id, ISBN, duration);
				} 
				if (CartRepository.renew(id, ISBN, duration, dueDate) == true) {
					JOptionPane.showMessageDialog(frame, "Book renewed", "Renew Success!", JOptionPane.INFORMATION_MESSAGE);
					btnRenew.setEnabled(false);
					
					if(CartRepository.showDetails(id, ISBN).length != 0) {
						String [] details = CartRepository.showDetails(id, ISBN);
						title = details[0];
						borrowDate = details[1];
						category = details[2];
						duration = Integer.parseInt(details[3]);
						dueDate = details[4];
						LocalDate due = LocalDate.parse(dueDate);
						int nowDay = LocalDate.now().getDayOfMonth();
						int dueDay = LocalDate.parse(dueDate).getDayOfMonth();
						
						dayLeft = ChronoUnit.DAYS.between(
				    	        LocalDate.now().withDayOfMonth(nowDay),
				    	        due.withDayOfMonth(dueDay));
						
						lblOutputTitle.setText(title);
						lblOutputCategory.setText(category);
						lblOutputBorrow.setText(borrowDate);
						lblOutputInitialDuration.setText(duration + " days");
						lblOutputDue.setText(dueDate);
						lblOutputDuration.setText(dayLeft + " day(s)"); 
					} else {
						JOptionPane.showMessageDialog(frame, "Something went wrong", "Error.", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Something went wrong", "Error.", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton btnCalculate = new JButton("Calculate Fine");
		btnCalculate.setBounds(209, 150, 113, 23);
		BorrowInfo.add(btnCalculate);
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblFine.setVisible(true);
				lblOutputFine.setVisible(true);
				
				dueDate = lblOutputDue.getText();
				LocalDate due = LocalDate.parse(dueDate);
				int nowDay = LocalDate.now().getDayOfMonth();
				int dueDay = LocalDate.parse(dueDate).getDayOfMonth();
				
				dayLeft = ChronoUnit.DAYS.between(
		    	        LocalDate.now().withDayOfMonth(nowDay),
		    	        due.withDayOfMonth(dueDay));
				
				if(dayLeft < 0) {
					sum = (int) -(dayLeft);
					lblOutputFine.setText("The fine is RM" + sum + ".");
				} else {
					lblOutputFine.setText("There is no fine for this book in cart.");
				}
				
			}
		});
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(334, 109, 79, 23);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSearch.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in ID in order to search.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (!txtSearch.getText().isEmpty()) {
					
					id = txtSearch.getText();
					
					if(!CartRepository.filter(id).isEmpty()) {
						
						Vector<String> cname = new Vector<>();
						cname.add("ISBN");
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						scrollPane.setBounds(358, 155, 152, 210);
						panel.add(scrollPane);
						
						table = new JTable(CartRepository.filter(id), cname);
						scrollPane.setViewportView(table);
						
						table.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								id = txtSearch.getText();
								ISBN = table.getValueAt(table.getSelectedRow(), 0).toString();
								BorrowInfo.setVisible(true);
								lblFine.setVisible(false);
								lblOutputFine.setVisible(false);
								
								if(CartRepository.showDetails(id, ISBN).length != 0) {
									String [] details = CartRepository.showDetails(id, ISBN);
									title = details[0];
									borrowDate = details[1];
									category = details[2];
									duration = Integer.parseInt(details[3]);
									dueDate = details[4];
									LocalDate due = LocalDate.parse(dueDate);
									int nowDay = LocalDate.now().getDayOfMonth();
									int dueDay = LocalDate.parse(dueDate).getDayOfMonth();
									
									dayLeft = ChronoUnit.DAYS.between(
							    	        LocalDate.now().withDayOfMonth(nowDay),
							    	        due.withDayOfMonth(dueDay));
									
									lblOutputTitle.setText(title);
									lblOutputCategory.setText(category);
									lblOutputBorrow.setText(borrowDate);
									lblOutputInitialDuration.setText(duration + " days");
									lblOutputDue.setText(dueDate);
									lblOutputDuration.setText(dayLeft + " day(s)");
								} else {
									JOptionPane.showMessageDialog(frame, "Something went wrong", "Error.", JOptionPane.WARNING_MESSAGE);
								}
								
								if (CartRepository.checkRenew(id, ISBN, status) == true) {
									btnRenew.setEnabled(false);
								} else {
									if (dayLeft < 0) {
										btnRenew.setEnabled(false);
									} else {
										btnRenew.setEnabled(true);
									}
								}
							}
						});
					} else {
						JOptionPane.showMessageDialog(frame, "This user does not have any borrowed book in cart.", "Error.", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		
	}

	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowCart window = new BorrowCart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
