package ie.patrickrobertson.dentist.screens;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class Welcome extends LayoutTemplate {


	private static final long serialVersionUID = 1L;
	private JButton btnNonserialized;
	private JButton btnSerialized;
	private JButton btnDatabase;

	public Welcome() {
		setBackground(Color.WHITE);
		
		JPanel welcomeMessage = new JPanel();
		welcomeMessage.setBackground(Color.WHITE);
		welcomeMessage.setBounds(0, 11, 560, 49);
		add(welcomeMessage);
		welcomeMessage.setLayout(new BoxLayout(welcomeMessage, BoxLayout.Y_AXIS));
		
		JLabel lblWelcomeToBiters = new JLabel("Welcome to Biters!");
		lblWelcomeToBiters.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcomeToBiters.setFont(new Font("Tahoma", Font.PLAIN, 22));
		welcomeMessage.add(lblWelcomeToBiters);
		
		JLabel lblPleaseSelectWhich = new JLabel("Please select which system you would like to use:");
		lblPleaseSelectWhich.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeMessage.add(lblPleaseSelectWhich);
		
		JPanel mainChoiceButtons = new JPanel();
		mainChoiceButtons.setBackground(Color.WHITE);
		mainChoiceButtons.setBounds(0, 71, 560, 42);
		add(mainChoiceButtons);
		
		btnNonserialized = new JButton("Non-Serialized");
		mainChoiceButtons.add(btnNonserialized);
		
		btnSerialized = new JButton("Serialized");
		btnSerialized.setBorder(UIManager.getBorder("Button.border"));
		mainChoiceButtons.add(btnSerialized);
		
		btnDatabase = new JButton("Database");
		mainChoiceButtons.add(btnDatabase);
		

	}

	public JButton getBtnNonserialized() {
		return btnNonserialized;
	}

	public void setBtnNonserialized(JButton btnNonserialized) {
		this.btnNonserialized = btnNonserialized;
	}

	public JButton getBtnSerialized() {
		return btnSerialized;
	}

	public void setBtnSerialized(JButton btnSerialized) {
		this.btnSerialized = btnSerialized;
	}

	public JButton getBtnDatabase() {
		return btnDatabase;
	}

	public void setBtnDatabase(JButton btnDatabase) {
		this.btnDatabase = btnDatabase;
	}
	
	
}
