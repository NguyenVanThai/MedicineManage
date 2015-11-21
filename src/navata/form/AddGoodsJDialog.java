package navata.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.TitledBorder;

import org.jdesktop.xswingx.PromptSupport;

public class AddGoodsJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			AddGoodsJDialog dialog = new AddGoodsJDialog();
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
	public AddGoodsJDialog() {
		setResizable(false);
		setBackground(Color.WHITE);

		setBounds(100, 100, 685, 521);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));

		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(128, 128, 128));
		panel_3.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panel_3, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		textField = new JTextField();
		textField.setForeground(new Color(128, 128, 128));
		textField.setEnabled(false);

		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Hệ thống tự tạo mã", textField);

		textField_1 = new JTextField(10);
		textField_1.setForeground(new Color(128, 128, 128));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		// textField_1.setColumns(10);
		textField_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_1.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Nhập tên hàng hoá", textField_1);

		textField_2 = new JTextField();
		textField_2.setForeground(new Color(128, 128, 128));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_2.setBackground(Color.WHITE);

		textField_3 = new JTextField();
		textField_3.setForeground(new Color(128, 128, 128));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_3.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Nhập giá vốn", textField_3);

		textField_4 = new JTextField();
		textField_4.setForeground(new Color(128, 128, 128));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_4.setBackground(Color.WHITE);

		textField_6 = new JTextField();
		textField_6.setForeground(new Color(128, 128, 128));
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_6.setColumns(10);
		textField_6.setBorder(new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		textField_6.setBackground(Color.WHITE);

		JLabel lblEmail = new JLabel("Gi\u00E1 v\u1ED1n");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblSinThoi = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblaCh = new JLabel("Gi\u00E1 b\u00E1n");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblSinhNht = new JLabel("Ng\u00E0y s\u1EA3n xu\u1EA5t");
		lblSinhNht.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblTnNcc = new JLabel("T\u00EAn HH");
		lblTnNcc.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblMKhchHng = new JLabel("M\u00E3 HH");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNgyHtHng = new JLabel("Ng\u00E0y h\u1EBFt h\u1EA1ng");
		lblNgyHtHng.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_5 = new JTextField();
		textField_5.setForeground(new Color(128, 128, 128));
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_5.setBackground(Color.WHITE);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addGap(
						17)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnNcc, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSinhNht, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNgyHtHng, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
						.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel_3
				.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnNcc, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSinhNht, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNgyHtHng, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(16, Short.MAX_VALUE)));
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] { textField, textField_1, textField_2, textField_3,
				textField_4, textField_6, textField_5 });
		gl_panel_3.linkSize(SwingConstants.HORIZONTAL,
				new Component[] { lblEmail, lblSinThoi, lblaCh, lblSinhNht, lblTnNcc, lblMKhchHng, lblNgyHtHng });
		panel_3.setLayout(gl_panel_3);

		JLabel lblToMiKhch = new JLabel("T\u1EA1o h\u00E0ng ho\u00E1 ");
		lblToMiKhch.setForeground(new Color(65, 105, 225));
		lblToMiKhch.setFont(new Font("Tahoma", Font.BOLD, 30));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblToMiKhch)
					.addContainerGap(456, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblToMiKhch, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		JButton btnNewButton = new JButton("L\u01B0u");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(AddGoodsJDialog.class.getResource("/navata/images/floppy.png")));
		JButton btnNewButton_1 = new JButton("L\u01B0u v\u00E0 ti\u1EBFp t\u1EE5c");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setIcon(new ImageIcon(AddGoodsJDialog.class.getResource("/navata/images/undock.png")));

		JButton btnTr = new JButton("Tr\u1EDF v\u1EC1");
		btnTr.setFont(btnTr.getFont().deriveFont(btnTr.getFont().getStyle() | Font.BOLD));

		btnTr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTr.setIcon(new ImageIcon(AddGoodsJDialog.class.getResource("/navata/images/backward.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(261, Short.MAX_VALUE)
					.addComponent(btnTr, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTr, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel_1.linkSize(SwingConstants.VERTICAL, new Component[] {btnNewButton, btnNewButton_1, btnTr});
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
	}
}
