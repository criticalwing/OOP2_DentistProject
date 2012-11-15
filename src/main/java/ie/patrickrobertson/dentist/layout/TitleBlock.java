package ie.patrickrobertson.dentist.layout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class TitleBlock extends JPanel {

	private JLabel systemTypeLabel;
	private JLabel pageTitleLabel;

	public TitleBlock() {
		setBounds(0,0,640,70);
		setLayout(null);
		systemTypeLabel = new JLabel("-");
		systemTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		systemTypeLabel.setBounds(106, 41, 102, 14);
		add(systemTypeLabel);
		
		JLabel lblSystemType = new JLabel("System Version:");
		lblSystemType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSystemType.setBounds(10, 41, 105, 14);
		add(lblSystemType);
		
		JLabel lblNewLabel = new JLabel("Biters : Dental Surgery");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 11, 326, 30);
		add(lblNewLabel);
		
		pageTitleLabel = new JLabel("-");
		pageTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		pageTitleLabel.setBounds(10,56, 326, 17);
		add(pageTitleLabel);
		

	}

	public JLabel getSystemTypeLabel() {
		return systemTypeLabel;
	}

	public void setSystemTypeLabelText(String type) {
		systemTypeLabel.setText(type);
	}

	public JLabel getPageTitleLabel() {
		return pageTitleLabel;
	}

	public void setPageTitleLabelText(String title) {
		pageTitleLabel.setText(title);
	}

	



}
