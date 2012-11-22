package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.ProcedureTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JTextField;

public class ListProcedures extends LayoutTemplate {

	DataAccess dataAccess;
	private JButton btnSearchPatient;
	private JTable procedureListPanel;
	private JScrollPane scroller;
	private JTextField searchInput;
	private JPanel listOfProcedures;
	
	/**
	 * Create the panel.
	 */
	public ListProcedures(DataAccess dataAccess) {
		this.dataAccess = dataAccess;

		listOfProcedures = new JPanel();
		listOfProcedures.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listOfProcedures.setBackground(Color.WHITE);
		listOfProcedures.setBounds(10, 26, 620, 220);
		
		procedureListPanel = new JTable(listProcedures("all"));
		procedureListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		procedureListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(55);
		procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(455);
		procedureListPanel.getColumnModel().getColumn(2).setPreferredWidth(110);
		listOfProcedures.setLayout(null);
		
		scroller = new JScrollPane(procedureListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 620, 220);
		
		listOfProcedures.add(scroller);
		add(listOfProcedures);
		
		btnSearchPatient = new JButton("Search");
		btnSearchPatient.setBounds(197, 333, 105, 23);
		add(btnSearchPatient);
		btnSearchPatient.addActionListener(new searchPatientListener());
		btnSearchPatient.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Procedure List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);
		
		JLabel lblPatientContactNumber = new JLabel("Procedure - Name");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 284, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblRefineResults = new JLabel("Refine Results");
		lblRefineResults.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRefineResults.setBounds(10, 257, 139, 14);
		add(lblRefineResults);
		
		searchInput = new JTextField();
		searchInput.setBounds(10, 304, 292, 20);
		add(searchInput);
		searchInput.setColumns(10);
		searchInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!searchInput.equals("")){
					btnSearchPatient.setVisible(true);
				}else{
					btnSearchPatient.setVisible(false);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(searchInput.equals("")){
					btnSearchPatient.setVisible(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(searchInput.equals("")){
					btnSearchPatient.setVisible(false);
				}
			}
		});
	}

	private TableModel listProcedures(String search){
		if(search.equals("all")){
			return new ProcedureTableModel(dataAccess.getProcedures(),3);
		}else{
			return new ProcedureTableModel(dataAccess.findProcedureByName(search),3);
		}
	}
	
	public class searchPatientListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			scroller.remove(procedureListPanel);
			listOfProcedures.remove(scroller);
			
			procedureListPanel = new JTable(listProcedures(searchInput.getText()));
			procedureListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			procedureListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(55);
			procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(455);
			procedureListPanel.getColumnModel().getColumn(2).setPreferredWidth(110);
			
			scroller = new JScrollPane(procedureListPanel);
			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroller.setBounds(0, 0, 620, 220);
			scroller.add(procedureListPanel);
			listOfProcedures.add(scroller);
			repaint();
		}
		
	}
}
