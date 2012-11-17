package ie.patrickrobertson.dentist.screens;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;

public class HomePage extends LayoutTemplate {

	/**
	 * Create the panel.
	 */
	public HomePage() {
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 640, 63);
		add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton = new JButton("Add Patient");
		panel.add(btnNewButton, "2, 2");
		
		JButton btnNewButton_4 = new JButton("View Patients");
		panel.add(btnNewButton_4, "4, 2");
		
		JButton btnNewButton_2 = new JButton("Generate Invoice");
		panel.add(btnNewButton_2, "6, 2");
		
		JButton btnNewButton_5 = new JButton("Add Procedure");
		panel.add(btnNewButton_5, "2, 4");
		
		JButton btnNewButton_3 = new JButton("View Procedures");
		panel.add(btnNewButton_3, "4, 4");
		
		JButton btnNewButton_1 = new JButton("Generate Report");
		panel.add(btnNewButton_1, "6, 4");

	}

}
