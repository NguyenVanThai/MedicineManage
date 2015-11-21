package navata.form;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.xswingx.PromptSupport;

import navata.build.Constant;
import navata.dao.DaoDatabase;

import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class AddProviderJDialog extends JDialog {
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
	private JLabel lbError;
	private String type = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

			AddProviderJDialog dialog = new AddProviderJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddProviderJDialog() {
		this.type = Constant.CONTENT.ADD.getContent();
		init();
		UI();
		defineAdd();
	}

	public AddProviderJDialog(String provider_id, String provider_name, String provider_phone, String provider_email,
			String provider_address, String provider_note) {
		this.type = Constant.CONTENT.EDIT.getContent();
		init();
		UI();
		defineEdit(provider_id, provider_name, provider_phone, provider_email, provider_address, provider_note);
	}

	public void save() {

		String id = fieldId.getText();
		String name = fieldName.getText();
		String phone = fieldPhone.getText();
		String email = fieldEmail.getText();
		String address = fieldAddress.getText();
		String note = fieldNote.getText();

		if (checkData(lbError, fieldName.getText(), fieldPhone.getText(), fieldEmail.getText())) {
			if (type.equals(Constant.CONTENT.ADD.getContent())) {
				// dao.insertProvider(fieldName.getText(), fieldPhone.getText(),
				// fieldEmail.getText(),
				// fieldAddress.getText(), fieldNote.getText());
				dao.addProvider(id, name, phone, email, address, note);
			} else {
				dao.editProvider(id, name, phone, email, address, note);
			}
			exitAddUser();
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

	public void defineAdd() {
		// init Customer Code
		fieldId.setText("NCC" + dao.providerCode());
	}

	public void defineEdit(String provider_id, String provider_name, String provider_phone, String provider_email,
			String provider_address, String provider_note) {
		fieldId.setText(provider_id);
		fieldName.setText(provider_name);
		fieldPhone.setText(provider_phone);
		fieldEmail.setText(provider_email);
		fieldAddress.setText(provider_address);
		fieldNote.setText(provider_note);
	}

	public void init() {
		dao = new DaoDatabase();
	}

	public void UI() {
		setResizable(false);
		setBackground(Color.WHITE);

		setBounds(100, 100, 685, 521);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(194, 220, 250));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(194, 220, 250));
		JPanel panel_3 = new JPanel();
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

		JLabel lblSinhNht = new JLabel("Ghi ch\u00FA");
		lblSinhNht.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblTnNcc = new JLabel("T\u00EAn NCC");
		lblTnNcc.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblMKhchHng = new JLabel("M\u00E3 NCC");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 15));
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
								.addComponent(lblTnNcc, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(fieldPhone, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(fieldName, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(fieldEmail, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(fieldAddress, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(fieldNote, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)).addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldName, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnNcc, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
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
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldNote, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSinhNht, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(71, Short.MAX_VALUE)));
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] { fieldId, fieldName });
		panel_3.setLayout(gl_panel_3);

		JLabel lblToMiKhch = new JLabel("T\u1EA1o m\u1EDBi nh\u00E0 cung c\u1EA5p");
		lblToMiKhch.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblToMiKhch, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(433, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblToMiKhch,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		JButton btnSave = new JButton("L\u01B0u");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					save();
				}

			}
		});
		btnSave.setIcon(new ImageIcon(AddProviderJDialog.class.getResource("/navata/images/save_32.png")));
		JButton btnExit = new JButton("Tr\u1EDF v\u1EC1");
		btnExit.setIcon(new ImageIcon(AddProviderJDialog.class.getResource("/navata/images/back_32.png")));

		lbError = new JLabel("");
		lbError.setForeground(Color.RED);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lbError)
						.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE).addComponent(btnExit)
						.addGap(40).addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addGap(12)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnSave)
								.addComponent(btnExit).addComponent(lbError))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
	}

	protected void exitAddUser() {
		dispose();
	}
}
