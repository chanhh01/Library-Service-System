import java.awt.*;
import javax.swing.*;

import Repositories.BookRepository;
import Model.Book;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class EditBookList {

	private JFrame frame;
	private JTable table;
	private JTextField txtSearchISBN;
	private JTextField txtEditISBN;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtPublisher;
	private JComboBox<String> cmbCategory;

	private String ISBN;
	private String title;
	private String category;
	private String author;
	private String publisher;
	/**
	 * Create the application.
	 */
	public EditBookList() {
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
		btnLogOut.setBounds(403, 11, 119, 23);
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
		btnEditBook.setEnabled(false);
		
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
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BorrowCart bc = new BorrowCart();
				bc.open();
				frame.dispose();
			}
		});
		
		JLabel lblSearch = new JLabel("Serach (by ISBN):");
		lblSearch.setBounds(10, 113, 117, 14);
		panel.add(lblSearch);
		
		txtSearchISBN = new JTextField();
		txtSearchISBN.setColumns(13);
		txtSearchISBN.setBounds(137, 110, 122, 20);
		panel.add(txtSearchISBN);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(403, 109, 93, 23);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtSearchISBN.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in ISBN in order to search.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (!txtSearchISBN.getText().isEmpty()) {
					
					ISBN = txtSearchISBN.getText();
					
					if(BookRepository.Filter(ISBN) == true) {
						Vector<String> cname = new Vector<>();
						cname.add("ISBN");
						cname.add("Title");
						cname.add("category");
						cname.add("author");
						cname.add("publisher");
						
						table = new JTable(BookRepository.display(ISBN), cname);
						table.setBounds(10, 138, 374, 227);
						
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 138, 374, 227);
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
		btnCancel.setBounds(290, 109, 109, 23);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditBookList ebl = new EditBookList();
				ebl.open();
				frame.dispose();
			}
		});
		
		txtEditISBN = new JTextField();
		txtEditISBN.setBounds(390, 141, 132, 20);
		panel.add(txtEditISBN);
		txtEditISBN.setColumns(13);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(50);
		txtTitle.setBounds(390, 172, 132, 20);
		panel.add(txtTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(30);
		txtAuthor.setBounds(390, 236, 132, 20);
		panel.add(txtAuthor);
		
		txtPublisher = new JTextField();
		txtPublisher.setColumns(50);
		txtPublisher.setBounds(390, 267, 132, 20);
		panel.add(txtPublisher);
		
		cmbCategory = new JComboBox<String>();
		cmbCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"magazine", "study materials", "book"}));
		cmbCategory.setBounds(390, 203, 132, 22);
		panel.add(cmbCategory);
		
		JButton btnAddRecord = new JButton("Add Record");
		btnAddRecord.setBounds(413, 328, 109, 23);
		panel.add(btnAddRecord);
		btnAddRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtEditISBN.getText().isEmpty()||txtTitle.getText().isEmpty()||txtAuthor.getText().isEmpty()||txtPublisher.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in all the fields to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (txtEditISBN.getText().length()!=13){
					JOptionPane.showMessageDialog(frame, "Invalid ISBN number, ISBN should have 13 numbers.", "Invalid ISBN", JOptionPane.WARNING_MESSAGE);
				} else {
					ISBN = txtEditISBN.getText();
					title = txtTitle.getText();
					category = (String)cmbCategory.getSelectedItem();
					author = txtAuthor.getText();
					publisher = txtPublisher.getText();
					
					Book book = new Book();
					book.setISBN(ISBN);
					book.setTitle(title);
					book.setCategory(category);
					book.setAuthor(author);
					book.setPublisher(publisher);
					
					if (BookRepository.insert(book)==true) {
						
						JOptionPane.showMessageDialog(frame, "New book successfully added", "Edit Success!", JOptionPane.INFORMATION_MESSAGE);
						
						EditBookList ebl = new EditBookList();
						ebl.open();
						frame.dispose();
						
					} else {
						JOptionPane.showMessageDialog(frame, "Something went wrong.", "Edit Failed...", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Vector<String> cname = new Vector<>();
				cname.add("ISBN");
				cname.add("Title");
				cname.add("category");
				cname.add("author");
				cname.add("publisher");
				
				table = new JTable(BookRepository.display(), cname);
				table.setBounds(10, 138, 374, 227);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 138, 374, 227);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel.add(scrollPane);
				
			}
		});
		
	}
	
	public void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBookList window = new EditBookList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
