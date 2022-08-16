import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;

import Repositories.BookRepository;

public class MainPage{

	private JFrame frame;
	private JTextField txtISBN;
	private JTable table;

	private String ISBN;
	/**
	 * Create the application.
	 */
	public MainPage() {
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
		SpringLayout springLayout_1 = new SpringLayout();
		frame.getContentPane().setLayout(springLayout_1);
		
		final JButton btnLogOut = new JButton("Log Out");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnLogOut, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.WEST, btnLogOut, 403, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.EAST, btnLogOut, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnLogOut);
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
		
		JButton btnBookList = new JButton("Book List");
		springLayout_1.putConstraint(SpringLayout.WEST, btnBookList, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.SOUTH, btnBookList, -278, SpringLayout.SOUTH, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.EAST, btnBookList, 123, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnBookList);
		btnBookList.setEnabled(false);
		
		
		JButton btnEditBook = new JButton("Edit Book List");
		springLayout_1.putConstraint(SpringLayout.WEST, btnEditBook, 4, SpringLayout.EAST, btnBookList);
		springLayout_1.putConstraint(SpringLayout.SOUTH, btnEditBook, -278, SpringLayout.SOUTH, frame.getContentPane());
		btnEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditBookList ebl = new EditBookList();
				ebl.open();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnEditBook);
		
		JButton btnBorrow = new JButton("Borrow Books");
		springLayout_1.putConstraint(SpringLayout.SOUTH, btnBorrow, -278, SpringLayout.SOUTH, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.EAST, btnEditBook, -6, SpringLayout.WEST, btnBorrow);
		frame.getContentPane().add(btnBorrow);
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BorrowBook bb = new BorrowBook();
				bb.open();
				frame.dispose();
			}
		});
		
		
		JButton btnCart = new JButton("Borrow Cart");
		springLayout_1.putConstraint(SpringLayout.SOUTH, btnCart, -278, SpringLayout.SOUTH, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.WEST, btnBorrow, -125, SpringLayout.WEST, btnCart);
		springLayout_1.putConstraint(SpringLayout.EAST, btnBorrow, -6, SpringLayout.WEST, btnCart);
		springLayout_1.putConstraint(SpringLayout.WEST, btnCart, 390, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.EAST, btnCart, -14, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BorrowCart bc = new BorrowCart();
				bc.open();
				frame.dispose();
			}
		});
		
		
		JLabel lblTitle = new JLabel("APU Library Service System");
		springLayout_1.putConstraint(SpringLayout.WEST, lblTitle, 117, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.SOUTH, lblTitle, -7, SpringLayout.NORTH, btnBookList);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
		frame.getContentPane().add(lblTitle);
		
		final JPanel panel = new JPanel();
		springLayout_1.putConstraint(SpringLayout.NORTH, panel, 37, SpringLayout.SOUTH, btnBookList);
		springLayout_1.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Vector<String> cname = new Vector<>(); //declare a new vector for column name
				cname.add("ISBN");
				cname.add("Title");
				cname.add("category");
				cname.add("author");
				cname.add("publisher");
				
				table = new JTable(BookRepository.display(), cname); //the vectors of strings returned by BookRepository.display() is added to the rows of the table
				table.setBounds(0, 0, 512, 231);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 512, 231);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel.add(scrollPane);
			}
		});
			
		
		
		JLabel lblSearch = new JLabel("Serach Books (by ISBN):");
		springLayout_1.putConstraint(SpringLayout.WEST, lblSearch, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout_1.putConstraint(SpringLayout.SOUTH, lblSearch, -7, SpringLayout.NORTH, panel);
		frame.getContentPane().add(lblSearch);
		
		txtISBN = new JTextField();
		springLayout_1.putConstraint(SpringLayout.WEST, txtISBN, 10, SpringLayout.EAST, lblSearch);
		springLayout_1.putConstraint(SpringLayout.SOUTH, txtISBN, -6, SpringLayout.NORTH, panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		springLayout_1.putConstraint(SpringLayout.EAST, txtISBN, 177, SpringLayout.EAST, lblSearch);
		frame.getContentPane().add(txtISBN);
		txtISBN.setColumns(13);
		
		JButton btnSearch = new JButton("Search");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnSearch, 6, SpringLayout.SOUTH, btnCart);
		springLayout_1.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, btnCart);
		frame.getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtISBN.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in ISBN in order to search.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (!txtISBN.getText().isEmpty()) {
					
					ISBN = txtISBN.getText();
					
					if(BookRepository.Filter(ISBN) == true) {
						Vector<String> cname = new Vector<>(); //declare a vector for column names
						cname.add("ISBN");
						cname.add("Title");
						cname.add("category");
						cname.add("author");
						cname.add("publisher");
						
						table = new JTable(BookRepository.display(ISBN), cname); //the vectors of strings returned by BookRepository.display(ISBN) is added to the rows of the table
						table.setBounds(0, 0, 512, 231);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(0, 0, 512, 231);
						scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
						scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						panel.add(scrollPane);
					} else {
						JOptionPane.showMessageDialog(frame, "Such ISBN does not exist in the library.", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, btnBorrow);
		springLayout_1.putConstraint(SpringLayout.EAST, btnCancel, -6, SpringLayout.WEST, btnSearch);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.open();
				frame.dispose();
			}
		});
		
	}

	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
