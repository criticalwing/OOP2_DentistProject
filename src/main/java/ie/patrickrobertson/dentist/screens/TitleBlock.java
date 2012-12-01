package ie.patrickrobertson.dentist.screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TitleBlock extends JPanel {
	private JLabel pageTitleLabel;
	private JButton btnSaveData;

	public TitleBlock() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(0,0,640,50);
		setLayout(null);
		
		pageTitleLabel = new JLabel("Title Holder");
		pageTitleLabel.setIcon(new ImageIcon("C:\\Users\\PatrickRobertson\\workspace\\OOP2_DentistProject\\src\\main\\resources\\Teeth.gif"));
		pageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		pageTitleLabel.setBounds(10,11, 520, 32);
		add(pageTitleLabel);
		
		btnSaveData = new JButton("Commit All");
		btnSaveData.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSaveData.setBounds(540, 11, 90, 23);
		add(btnSaveData);
		btnSaveData.setVisible(false);
		

	}

	public JLabel getPageTitleLabel() {
		return pageTitleLabel;
	}

	public void setPageTitleLabelText(String title) {
		pageTitleLabel.setText(title);
	}

	public JButton getBtnSaveData() {
		return btnSaveData;
	}

	public void setBtnSaveData(JButton btnSaveData) {
		this.btnSaveData = btnSaveData;
	}
}
