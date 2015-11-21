package navata.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.xswingx.PromptSupport;

import com.sun.xml.internal.ws.addressing.policy.AddressingPolicyMapConfigurator;

import navata.build.Constant;
import navata.dao.DaoDatabase;
import navata.dto.DtoCustomer;
import navata.dto.DtoProvider;

public class Main extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldSearch;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable tableCustomer;
	private DaoDatabase dao;
	private DefaultTableModel modelCustomer;
	private DefaultTableModel modelProvider;
	private JTabbedPane tabbedPane;

	private JPopupMenu popupMenuCustomer;
	private JMenuItem menuItemAddCustomer;
	private JMenuItem menuItemEditCustomer;
	private JMenuItem menuItemDeleteCustomer;

	private JPopupMenu popupMenuProvider;
	private JMenuItem menuItemAddProvider;
	private JMenuItem menuItemEditProvider;
	private JMenuItem menuItemDeleteProvider;

	private JTextField textField;
	private JTextField textFieldProviderSearch;
	private JTable tableProvider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

					Main frame = new Main();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// public void define() {
	// // init Customer Code
	// fieldId.setText("KH" + dao.customerCode());
	// }

	public void init() {
		dao = new DaoDatabase();

		modelCustomer = new DefaultTableModel(null, Constant.ARRAY.CUSTOMER.getArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		modelProvider = new DefaultTableModel(null, Constant.ARRAY.PROVIDER.getArray()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

	}

	public DefaultTableModel setModelCustomer(DefaultTableModel model, List<DtoCustomer> list) {
		// DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);
		for (DtoCustomer dto : list) {
			model.addRow(dto.toArray());
		}
		model.fireTableDataChanged();
		return model;
	}

	public DefaultTableModel setModelProvider(DefaultTableModel model, List<DtoProvider> list) {
		// DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);
		for (DtoProvider dto : list) {
			model.addRow(dto.toArray());
		}
		model.fireTableDataChanged();
		return model;
	}

	public void resetTabPanel() {
		switch (tabbedPane.getSelectedIndex()) {

		case 3:

			// table.setModel(setModel(model, dao.listCustomer()));

			searchCustomer(textFieldSearch.getText());

			break;
		case 4:

			searchProvider(textFieldProviderSearch.getText());
			break;
		default:
			break;
		}
	}

	public void searchCustomer(String input) {
		tableCustomer.setModel(setModelCustomer(modelCustomer, dao.searchCustomer(input)));

	}

	public void searchProvider(String input) {
		// tableProvider.setModel(setModelCustomer(model,
		// dao.searchProvider(input)));

		tableProvider.setModel(setModelProvider(modelProvider, dao.searchProvider(input)));
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		super("JTable Popup Menu Example");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				resetTabPanel();
			}
		});

		setBackground(new Color(173, 216, 230));
		getContentPane().setBackground(new Color(173, 216, 230));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/navata/images/cart_48.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1272, 636);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(194, 220, 250));
		getContentPane().add(panel, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				resetTabPanel();
			}
		});

		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		tabbedPane.setForeground(new Color(11, 135, 201));

		JPanel panel_input = new JPanel();
		tabbedPane.addTab("Nhập kho", new ImageIcon(Main.class.getResource("/navata/images/delivery.png")), panel_input,
				null);

		JPanel panel_output = new JPanel();
		tabbedPane.addTab("Xuất kho", new ImageIcon(Main.class.getResource("/navata/images/bill_of_document.png")),
				panel_output, null);

		JPanel panel_total = new JPanel();
		tabbedPane.addTab("Tồn kho", new ImageIcon(Main.class.getResource("/navata/images/address_book.png")),
				panel_total, null);

		JPanel panel_customer = new JPanel();
		panel_customer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		tabbedPane.addTab("Khách hàng", new ImageIcon(Main.class.getResource("/navata/images/user_group_01.png")),
				panel_customer, null);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_1.setBackground(new Color(230, 230, 250));

		JScrollPane scrollPane = new JScrollPane();

		init();

		tableCustomer = new JTable();

		final TableCellRenderer tcrOs = tableCustomer.getTableHeader().getDefaultRenderer();
		tableCustomer.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				// lbl.setBorder(BorderFactory.createCompoundBorder(lbl.getBorder(),
				// BorderFactory.createEmptyBorder(0, 5, 0, 0)));
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				// if (isSelected) {
				// lbl.setForeground(Color.red);
				// lbl.setFont(new Font("Arial", Font.BOLD, 20));
				// } else {
				// lbl.setForeground(Color.darkGray);
				lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
				lbl.setForeground(new Color(11, 135, 201));
				// }

				return lbl;
			}
		});

		tableCustomer.setRowHeight(30);

		// ---------- start poup menu
		// -------------------------------------------
		// constructs the popup menu
		popupMenuCustomer = new JPopupMenu();
		menuItemAddCustomer = new JMenuItem("Thêm khách hàng");
		menuItemEditCustomer = new JMenuItem("Chỉnh sửa thông tin");
		menuItemDeleteCustomer = new JMenuItem("Xoá khách hàng");

		menuItemAddCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_add_user_32.png")));
		menuItemEditCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_edit_user_32.png")));
		menuItemDeleteCustomer
				.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_delete_user_32.png")));

		menuItemAddCustomer.addActionListener(this);
		menuItemEditCustomer.addActionListener(this);
		menuItemDeleteCustomer.addActionListener(this);

		popupMenuCustomer.add(menuItemAddCustomer);
		popupMenuCustomer.add(menuItemEditCustomer);
		popupMenuCustomer.add(menuItemDeleteCustomer);

		// sets the popup menu for the table
		tableCustomer.setComponentPopupMenu(popupMenuCustomer);

		tableCustomer.addMouseListener(new TableMouseListener(tableCustomer));

		// ---------- finish poup menu
		// -------------------------------------------

		scrollPane.setViewportView(tableCustomer);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBorder(
				new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(245, 245, 245)));
		PromptSupport.setPrompt("Nhập tên, mã hoặc SĐT khách hàng", textFieldSearch);

		JButton btnSearchCustomer = new JButton("Tìm kiếm");
		btnSearchCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {
					String input = textFieldSearch.getText();
					searchCustomer(input);

				}
			}
		});
		btnSearchCustomer.setForeground(new Color(11, 135, 201));
		btnSearchCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearchCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/find.png")));

		JLabel lblNewLabel = new JLabel("Khách hàng");
		lblNewLabel.setForeground(new Color(11, 135, 201));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnDeleteCustomer = new JButton("Xoá KH");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDeleteCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					deleteCustomer();
				}
			}
		});
		btnDeleteCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_delete_user_32.png")));

		JButton btnEditCustomer = new JButton("Chỉnh sửa");
		btnEditCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					editCustomer();
				}
			}
		});
		btnEditCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_edit_user_32.png")));
		btnEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnAddCustomer = new JButton("Thêm KH");
		btnAddCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					addCustomer();
				}
			}
		});
		btnAddCustomer.setIcon(new ImageIcon(Main.class.getResource("/navata/images/button_add_user_32.png")));
		btnAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 421,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnSearchCustomer, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
								.addComponent(btnAddCustomer, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnEditCustomer, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(btnDeleteCustomer, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE).addGap(34))
								.addGroup(gl_panel_1
										.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
												133, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(879, Short.MAX_VALUE)))));
		gl_panel_1
				.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addGap(14)
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
										.addGap(7)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(btnDeleteCustomer, GroupLayout.DEFAULT_SIZE, 41,
														Short.MAX_VALUE)
												.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnEditCustomer, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSearchCustomer, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING,
								gl_panel_1.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE).addComponent(
										btnAddCustomer, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel_1.linkSize(SwingConstants.VERTICAL, new Component[] { textFieldSearch, btnSearchCustomer });
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel_customer = new GroupLayout(panel_customer);
		gl_panel_customer.setHorizontalGroup(gl_panel_customer.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE));
		gl_panel_customer.setVerticalGroup(gl_panel_customer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_customer.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)));
		panel_customer.setLayout(gl_panel_customer);

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Nhà cung cấp", new ImageIcon(Main.class.getResource("/navata/images/home.png")), panel_8,
				null);

		JPanel panel_9 = new JPanel();
		panel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_9.setBackground(new Color(230, 230, 250));

		textFieldProviderSearch = new JTextField();
		PromptSupport.setPrompt("Nhập tên, mã hoặc SĐT nhà cung cấp", textFieldProviderSearch);
		textFieldProviderSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldProviderSearch.setColumns(10);
		textFieldProviderSearch.setBorder(
				new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(245, 245, 245)));

		JButton btnSearchProvider = new JButton("Tìm kiếm");
		btnSearchProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					searchProvider(textFieldProviderSearch.getText());
				}
			}
		});
		btnSearchProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/find.png")));
		btnSearchProvider.setForeground(new Color(11, 135, 201));
		btnSearchProvider.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnAddProvider = new JButton("Thêm KH");
		btnAddProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					addProvider();
				}
			}
		});
		btnAddProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_add_32.png")));
		btnAddProvider.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnEditProvider = new JButton("Chỉnh sửa");
		btnEditProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					editProvider();
				}
			}
		});
		btnEditProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_32.png")));
		btnEditProvider.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnDeleteProvider = new JButton("Xoá KH");
		btnDeleteProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					deleteProvider();
				}
			}
		});
		btnDeleteProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_remove_32.png")));
		btnDeleteProvider.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel labelProvider = new JLabel("Nhà cung cấp");
		labelProvider.setForeground(new Color(11, 135, 201));
		labelProvider.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_9.createSequentialGroup()
										.addComponent(labelProvider, GroupLayout.PREFERRED_SIZE, 184,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(gl_panel_9.createSequentialGroup()
								.addComponent(textFieldProviderSearch, GroupLayout.PREFERRED_SIZE, 421,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnSearchProvider, GroupLayout.PREFERRED_SIZE, 131,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
								.addComponent(btnAddProvider, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnEditProvider, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE)
								.addGap(11).addComponent(btnDeleteProvider, GroupLayout.PREFERRED_SIZE, 141,
										GroupLayout.PREFERRED_SIZE)
								.addGap(34)))));
		gl_panel_9
				.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_9.createSequentialGroup()
								.addGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_9.createSequentialGroup().addGap(14)
												.addComponent(labelProvider, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
										.addGap(7)
										.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
												.addComponent(btnDeleteProvider, GroupLayout.DEFAULT_SIZE, 41,
														Short.MAX_VALUE)
												.addComponent(textFieldProviderSearch, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_9.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE)
								.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnEditProvider, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSearchProvider, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_9.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE).addComponent(
								btnAddProvider, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel_9.linkSize(SwingConstants.VERTICAL, new Component[] { textFieldProviderSearch, btnSearchProvider });
		panel_9.setLayout(gl_panel_9);

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING).addGap(0, 1251, Short.MAX_VALUE)
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE));
		gl_panel_8.setVerticalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING).addGap(0, 475, Short.MAX_VALUE)
				.addGroup(gl_panel_8.createSequentialGroup()
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)));

		tableProvider = new JTable();

		tableProvider.setRowHeight(30);

		// ---------- start poup menu
		// -------------------------------------------
		// constructs the popup menu
		popupMenuProvider = new JPopupMenu();
		menuItemAddProvider = new JMenuItem("Thêm nhà cung cấp");
		menuItemEditProvider = new JMenuItem("Chỉnh sửa thông tin");
		menuItemDeleteProvider = new JMenuItem("Xoá nhà cung cấp");

		menuItemAddProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_add_32.png")));
		menuItemEditProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_32.png")));
		menuItemDeleteProvider.setIcon(new ImageIcon(Main.class.getResource("/navata/images/provider_remove_32.png")));

		menuItemAddProvider.addActionListener(this);
		menuItemEditProvider.addActionListener(this);
		menuItemDeleteProvider.addActionListener(this);

		popupMenuProvider.add(menuItemAddProvider);
		popupMenuProvider.add(menuItemEditProvider);
		popupMenuProvider.add(menuItemDeleteProvider);

		// sets the popup menu for the table
		tableProvider.setComponentPopupMenu(popupMenuProvider);

		tableProvider.addMouseListener(new TableMouseListener(tableProvider));

		// ---------- finish poup menu
		// -------------------------------------------

		scrollPane_1.setViewportView(tableProvider);
		panel_8.setLayout(gl_panel_8);

		JPanel panel_provider = new JPanel();
		tabbedPane.addTab("Nhà cung cấp", new ImageIcon(Main.class.getResource("/navata/images/home.png")),
				panel_provider, null);

		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_2.setBackground(new Color(230, 230, 250));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBorder(
				new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(245, 245, 245)));
		PromptSupport.setPrompt("Nhập tên, mã hoặc SĐT khách hàng", textField_1);

		JButton button_2 = new JButton("Tìm kiếm");
		button_2.setForeground(new Color(11, 135, 201));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_3 = new JButton("Thêm KH");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_1 = new JLabel("Khách hàng");
		label_1.setForeground(new Color(11, 135, 201));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(
						gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel_2.createSequentialGroup()
														.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 522,
																Short.MAX_VALUE)
														.addGap(26)
														.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 131,
																GroupLayout.PREFERRED_SIZE)
										.addGap(158)
										.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE).addGap(34)).addGroup(
														gl_panel_2.createSequentialGroup()
																.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 133,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap(879, Short.MAX_VALUE)))));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
				.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(8).addComponent(textField_1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGap(12)));
		gl_panel_2.linkSize(SwingConstants.VERTICAL, new Component[] { textField_1, button_2 });
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel_provider = new GroupLayout(panel_provider);
		gl_panel_provider.setHorizontalGroup(gl_panel_provider.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE));
		gl_panel_provider.setVerticalGroup(gl_panel_provider.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_provider.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(219, Short.MAX_VALUE)));
		panel_provider.setLayout(gl_panel_provider);

		JPanel panel_goods = new JPanel();
		tabbedPane.addTab("Hàng hoá", new ImageIcon(Main.class.getResource("/navata/images/drug_basket.png")),
				panel_goods, null);

		JPanel panel_3 = new JPanel();
		panel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_3.setBackground(Color.LIGHT_GRAY);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null),

		"Nh\u1EADp t\u00EAn, m\u00E3 ho\u1EB7c S\u0110T kh\u00E1ch h\u00E0ng", TitledBorder.LEFT,

		TitledBorder.TOP, null, new Color(245, 245, 245)));
		textField_2.setBackground(Color.LIGHT_GRAY);

		JButton button_4 = new JButton("Tìm kiếm");
		button_4.setIcon(new ImageIcon(Main.class.getResource("/navata/images/search_32.png")));

		JButton btnThmHh = new JButton("Thêm HH");
		btnThmHh.setIcon(new ImageIcon(Main.class.getResource("/navata/images/add_pill_32.png")));
		btnThmHh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel label = new JLabel("Khách hàng");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0, 1022, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap().addGroup(gl_panel_3
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(
								gl_panel_3.createSequentialGroup()
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
										.addGap(26)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE)
										.addGap(158)
										.addComponent(btnThmHh, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE)
										.addGap(34))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGap(0, 95, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap(11, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(btnThmHh, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_2,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel_goods = new GroupLayout(panel_goods);
		gl_panel_goods.setHorizontalGroup(gl_panel_goods.createParallelGroup(Alignment.LEADING).addComponent(panel_3,
				GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE));
		gl_panel_goods.setVerticalGroup(gl_panel_goods.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_goods.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(239, Short.MAX_VALUE)));
		panel_goods.setLayout(gl_panel_goods);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE).addGap(0)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(60)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE).addGap(0)));

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		panel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_5.setBackground(new Color(230, 230, 250));

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBorder(
				new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(245, 245, 245)));

		JButton button_5 = new JButton("Tìm kiếm");
		button_5.setForeground(new Color(11, 135, 201));
		button_5.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_6 = new JButton("Thêm KH");
		button_6.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_2 = new JLabel("Khách hàng");
		label_2.setForeground(new Color(11, 135, 201));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5
				.setHorizontalGroup(
						gl_panel_5.createParallelGroup(Alignment.LEADING).addGap(0, 1022, Short.MAX_VALUE)
								.addGroup(gl_panel_5.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel_5.createSequentialGroup()
														.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 522,
																Short.MAX_VALUE)
														.addGap(26)
														.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 131,
																GroupLayout.PREFERRED_SIZE)
										.addGap(158)
										.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE).addGap(34)).addGroup(
														gl_panel_5.createSequentialGroup()
																.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 133,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap(879, Short.MAX_VALUE)))));
		gl_panel_5
				.setVerticalGroup(
						gl_panel_5.createParallelGroup(Alignment.TRAILING).addGap(0, 96, Short.MAX_VALUE)
								.addGroup(gl_panel_5.createSequentialGroup().addGap(14)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addComponent(button_6, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
								.addGroup(gl_panel_5.createSequentialGroup()
										.addContainerGap(44, Short.MAX_VALUE).addComponent(button_5,
												GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(14)));
		gl_panel_5.linkSize(SwingConstants.VERTICAL, new Component[] { textField_3, button_5 });
		panel_5.setLayout(gl_panel_5);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGap(0, 1022, Short.MAX_VALUE)
				.addComponent(panel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGap(0, 315, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(219, Short.MAX_VALUE)));
		panel_4.setLayout(gl_panel_4);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_6, null);

		JPanel panel_7 = new JPanel();
		panel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel_7.setBackground(new Color(230, 230, 250));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBorder(
				new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(245, 245, 245)));

		JButton button = new JButton("Tìm kiếm");
		button.setForeground(new Color(11, 135, 201));
		button.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_7 = new JButton("Thêm KH");
		button_7.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_3 = new JLabel("Khách hàng");
		label_3.setForeground(new Color(11, 135, 201));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7
				.setHorizontalGroup(
						gl_panel_7.createParallelGroup(Alignment.LEADING).addGap(0, 1251, Short.MAX_VALUE)
								.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel_7.createSequentialGroup()
														.addComponent(textField, GroupLayout.DEFAULT_SIZE, 751,
																Short.MAX_VALUE)
														.addGap(26)
														.addComponent(button, GroupLayout.PREFERRED_SIZE, 131,
																GroupLayout.PREFERRED_SIZE)
										.addGap(158)
										.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE).addGap(34)).addGroup(
														gl_panel_7.createSequentialGroup()
																.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 133,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap(1108, Short.MAX_VALUE)))));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING).addGap(0, 96, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_7.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
								.addGroup(gl_panel_7.createSequentialGroup().addGap(8).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE).addGap(12)));
		gl_panel_7.linkSize(SwingConstants.VERTICAL, new Component[] { textField, button });
		panel_7.setLayout(gl_panel_7);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGap(0, 1251, Short.MAX_VALUE)
				.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGap(0, 475, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(379, Short.MAX_VALUE)));
		panel_6.setLayout(gl_panel_6);
		panel.setLayout(gl_panel);

	}

	protected ImageIcon createImageIcon(String path, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource(path)); // load
																			// the
																			// image
																			// to
																			// a
																			// imageIcon
		Image image = imageIcon.getImage(); // transform it

		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); // scale
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JMenuItem menu = (JMenuItem) e.getSource();

		if (menu == menuItemAddCustomer) {
			addCustomer();
		} else if (menu == menuItemEditCustomer) {
			editCustomer();
		} else if (menu == menuItemDeleteCustomer) {
			deleteCustomer();

		} else if (menu == menuItemAddProvider) {
			addProvider();
		} else if (menu == menuItemEditProvider) {
			editProvider();
		} else if (menu == menuItemDeleteProvider) {
			deleteProvider();
		}

		// model.getValueAt(i, 0);
		// System.out.println(model.getValueAt(i, 0) + "");
		// AbstractButton button = (AbstractButton) e.getSource();
		// if (e.getActionCommand().equals(button.getActionCommand())) {
		//
		// int row = table.convertRowIndexToModel(table.getSelectedRow());
		// MyAwesomeTableModel model = (MyAwesomeTableModel) table.getModel();
		// //
		// // MyRowData data = model.getRowAt(row);
		// // JOptionPane.showMessageDialog(null, data);
		//
		// }
	}

	public void editCustomer() {

		int i = tableCustomer.getSelectedRow();
		if (i != -1) {
			String customer_id = (String) modelCustomer.getValueAt(i, 1);
			String customer_name = (String) modelCustomer.getValueAt(i, 2);
			String customer_phone = (String) modelCustomer.getValueAt(i, 3);
			String customer_email = (String) modelCustomer.getValueAt(i, 4);
			String customer_address = (String) modelCustomer.getValueAt(i, 5);
			String customer_birthday = (String) modelCustomer.getValueAt(i, 6);
			String customer_note = (String) modelCustomer.getValueAt(i, 7);

			AddCustomerJDialog addCustomer = new AddCustomerJDialog(customer_id, customer_name, customer_phone,
					customer_email, customer_address, customer_birthday, customer_note);
			addCustomer.setModal(true);
			addCustomer.setLocationRelativeTo(null);
			addCustomer.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JDesktopPane(), "Vui lòng chọn khách hàng", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void addCustomer() {
		AddCustomerJDialog addCustomer = new AddCustomerJDialog();
		addCustomer.setModal(true);
		addCustomer.setLocationRelativeTo(null);
		addCustomer.setVisible(true);
	}

	public void deleteCustomer() {
		int i = tableCustomer.getSelectedRow();
		if (i != -1) {
			String customer_id = (String) modelCustomer.getValueAt(i, 1);

			dao.deleteCustomer(customer_id);
			// table.setModel(setModel(model, dao.listCustomer()));
			searchCustomer(textFieldSearch.getText());

		} else {
			JOptionPane.showMessageDialog(new JDesktopPane(), "Vui lòng chọn khách hàng", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void addProvider() {
		AddProviderJDialog addProvider = new AddProviderJDialog();
		addProvider.setModal(true);
		addProvider.setLocationRelativeTo(null);
		addProvider.setVisible(true);
	}

	public void editProvider() {

		int i = tableProvider.getSelectedRow();

		if (i != -1) {
			String provider_id = (String) modelProvider.getValueAt(i, 1);
			String provider_name = (String) modelProvider.getValueAt(i, 2);
			String provider_phone = (String) modelProvider.getValueAt(i, 3);
			String provider_email = (String) modelProvider.getValueAt(i, 4);
			String provider_address = (String) modelProvider.getValueAt(i, 5);

			String provider_note = (String) modelProvider.getValueAt(i, 6);
			AddProviderJDialog addProvider = new AddProviderJDialog(provider_id, provider_name, provider_phone,
					provider_email, provider_address, provider_note);
			addProvider.setModal(true);
			addProvider.setLocationRelativeTo(null);
			addProvider.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JDesktopPane(), "Vui lòng chọn nhà cung cấp", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void deleteProvider() {

		int i = tableProvider.getSelectedRow();
		if (i != -1) {
			String provider_id = (String) modelProvider.getValueAt(i, 1);
			dao.deleteProvider(provider_id);
			// tableProvider.setModel(setModelProvider(modelProvider, dao.));
			searchProvider(textFieldProviderSearch.getText());
		} else {
			JOptionPane.showMessageDialog(new JDesktopPane(), "Vui lòng chọn nhà cung cấp", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
