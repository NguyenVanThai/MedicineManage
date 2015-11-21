package navata.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.xswingx.PromptSupport;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import navata.dao.DaoDatabase;
import navata.dto.DtoCustomer;
import navata.build.Constant;

public class AddCustomerJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fieldId;
	private JTextField fieldName;
	private JTextField fieldPhone;
	private JTextField fieldEmail;
	private JTextField fieldAddress;
	private JTextField fieldNote;
	private DaoDatabase dao;
	private JButton btnSave;
	private JLabel lbError;
	JDateChooser dateChooser;
	JTextFieldDateEditor editor;
	private String type = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			AddCustomerJDialog dialog = new AddCustomerJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkData(JLabel lbError, String name, String phone, String email) {
		boolean result = false;
		if (name.equals("")) {
			lbError.setText(Constant.ERROR.NAME.getContent());
			return result;
		}

		if (!phone.equals("")) {
			try {
				@SuppressWarnings("unused")
				long d = Long.parseLong(phone);
			} catch (NumberFormatException nfe) {
				lbError.setText(Constant.ERROR.PHONE.getContent());
				return result;
			}
		}

		if (!email.equals("")) {
			String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if (!email.matches(EMAIL_REGEX)) {
				lbError.setText(Constant.ERROR.EMAIL.getContent());
				return result;
			}
		}

		// No Error
		result = true;
		lbError.setText("");

		return result;

	}

	/**
	 * Create the dialog.
	 */

	public void defineAdd() {
		// init Customer Code
		fieldId.setText(Constant.CONTENT.KH.getContent() + dao.customerCode());
	}

	public void init() {
		dao = new DaoDatabase();
	}

	public AddCustomerJDialog(String customer_id, String customer_name, String customer_phone, String customer_email,
			String customer_address, String customer_birthday, String customer_note) {

		this.type = Constant.CONTENT.EDIT.getContent();
		init();
		UI();
		fieldId.setText(customer_id);
		fieldName.setText(customer_name);
		fieldPhone.setText(customer_phone);
		fieldEmail.setText(customer_email);
		fieldAddress.setText(customer_address);
		fieldNote.setText(customer_note);
	}

	public AddCustomerJDialog() {
		this.type = Constant.CONTENT.ADD.getContent();
		init();

		UI();
		defineAdd();
	}

	public void save() {
		String id = fieldId.getText();
		String name = fieldName.getText();
		String phone = fieldPhone.getText();
		String email = fieldEmail.getText();
		String address = fieldAddress.getText();
		String note = fieldNote.getText();

		if (checkData(lbError, name, phone, email)) {
			// if (checkData(lbError, fieldName.getText(), fieldPhone.getText(),
			// fieldEmail.getText())) {
			Date date;
			if (!editor.getText().equals("")) {
				date = new Date(dateChooser.getDate().getTime());
			} else {
				date = null;
			}

			System.out.println(type);
			if (type.equals(Constant.CONTENT.ADD.getContent())) {
				dao.addCustomer(fieldId.getText(), fieldName.getText(), fieldPhone.getText(), fieldEmail.getText(),
						fieldAddress.getText(), date, fieldNote.getText());
			} else {

				// System.out.println(fieldId.getText() + "-"+
				// fieldName.getText()+ "-"+ fieldPhone.getText()+ "-"+
				// fieldEmail.getText()+ "-"+
				// fieldAddress.getText()+ "-"+ date+ "-"+ fieldNote.getText());
				dao.editCustomer(fieldId.getText(), fieldName.getText(), fieldPhone.getText(), fieldEmail.getText(),
						fieldAddress.getText(), date, fieldNote.getText());
				// dao.editCustomer(id, name, phone, email, address, date,
				// note);
				// dao.edtCustomer();
			}
			exitAddUser();
		}
	}

	protected void exitAddUser() {
		dispose();
	}

	public void UI() {

		setResizable(false);
		setBackground(Color.WHITE);

		setBounds(100, 100, 685, 521);
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(194, 220, 250));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(194, 220, 250));
		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_1,
														GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)));

		// panel_3.add((JComponent) picker);

		fieldId = new JTextField();
		fieldId.setEnabled(false);
		fieldId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldId.setColumns(10);
		fieldId.setBackground(Color.WHITE);

		fieldName = new JTextField();
		fieldName.setForeground(Color.GRAY);
		fieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldName.setColumns(10);
		fieldName.setBackground(Color.WHITE);
		PromptSupport.setPrompt(Constant.CONTENT.NAME.getContent(), fieldName);

		fieldPhone = new JTextField();
		fieldPhone.setForeground(Color.GRAY);
		fieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldPhone.setColumns(10);
		fieldPhone.setBackground(Color.WHITE);

		fieldEmail = new JTextField();
		fieldEmail.setForeground(Color.GRAY);
		fieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldEmail.setColumns(10);
		fieldEmail.setBackground(Color.WHITE);
		PromptSupport.setPrompt(Constant.CONTENT.EMAIL.getContent(), fieldEmail);

		fieldAddress = new JTextField();
		fieldAddress.setForeground(Color.GRAY);
		fieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldAddress.setColumns(10);
		fieldAddress.setBackground(Color.WHITE);

		fieldNote = new JTextField();
		fieldNote.setForeground(Color.GRAY);
		fieldNote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNote.setColumns(10);
		fieldNote.setBackground(Color.WHITE);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblSinhNht = new JLabel("Sinh nh\u1EADt");
		lblSinhNht.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblGhiCh = new JLabel("Ghi ch\u00FA");
		lblGhiCh.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel label = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblMKhchHng = new JLabel("M\u00E3 kh\u00E1ch h\u00E0ng");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 15));

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooser.setForeground(Color.GRAY);
		dateChooser.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "",
				TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(64, 64, 64)));
		dateChooser.setLocale(new Locale("vi", "VN"));
		editor = (JTextFieldDateEditor) dateChooser.getDateEditor();

		editor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editor.setForeground(Color.GRAY);
		// editor.setEditable(false);
		// editor.setFocusable(false);
		editor.setEnabled(false);
		PromptSupport.setPrompt(Constant.CONTENT.BIRTHDAY.getContent(), editor);

		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(17)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 139,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSinhNht, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGhiCh, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldNote, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(fieldAddress, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(fieldEmail, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(fieldPhone, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(fieldName, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)).addContainerGap()));
		gl_panel_3
				.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldName, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldPhone, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fieldEmail, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldAddress, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinhNht, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGhiCh, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldNote, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(16, Short.MAX_VALUE)));
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] { fieldId, fieldName });
		panel_3.setLayout(gl_panel_3);

		JLabel lblToMiKhch = new JLabel("T\u1EA1o m\u1EDBi kh\u00E1ch h\u00E0ng");
		lblToMiKhch.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblToMiKhch, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(317, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblToMiKhch,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		btnSave = new JButton("L\u01B0u");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					save();
				}
			}
		});
		btnSave.setIcon(new ImageIcon(AddCustomerJDialog.class.getResource("/navata/images/save_32.png")));
		JButton btnNewButton_1 = new JButton("Tr\u1EDF v\u1EC1");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					exitAddUser();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AddCustomerJDialog.class.getResource("/navata/images/back_32.png")));

		lbError = new JLabel("");
		lbError.setForeground(Color.RED);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lbError)
						.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addGap(40).addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addGap(12)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnSave)
								.addComponent(btnNewButton_1).addComponent(lbError))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);

	}
}
