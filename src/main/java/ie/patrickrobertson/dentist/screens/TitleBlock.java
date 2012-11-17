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

public class TitleBlock extends JPanel {
	private JLabel pageTitleLabel;

	public TitleBlock() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(0,0,640,50);
		setLayout(null);
		
		pageTitleLabel = new JLabel("Title Holder");
		pageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		pageTitleLabel.setBounds(10,11, 620, 32);
		add(pageTitleLabel);
		

	}

	public JLabel getPageTitleLabel() {
		return pageTitleLabel;
	}

	public void setPageTitleLabelText(String title) {
		pageTitleLabel.setText(title);
	}
}
