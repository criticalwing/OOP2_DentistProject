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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private JTable procedureListPanel;
	private JScrollPane scroller;
	private JPanel listOfProcedures;
	private int selectedProcedureID;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

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
		procedureListPanel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(55);
		procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(455);
		procedureListPanel.getColumnModel().getColumn(2).setPreferredWidth(110);
		procedureListPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				ListProcedures.this.selectedProcedureID = (int) procedureListPanel
						.getModel().getValueAt(
								procedureListPanel.getSelectedRow(), 0);
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		listOfProcedures.setLayout(null);

		scroller = new JScrollPane(procedureListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 620, 220);

		listOfProcedures.add(scroller);
		add(listOfProcedures);

		JLabel lblNewLabel_1 = new JLabel("Procedure List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 257, 89, 23);
		add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.setBounds(109, 257, 89, 23);
		add(btnEdit);
		btnEdit.setVisible(false);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(208, 257, 89, 23);
		add(btnDelete);
		btnDelete.setVisible(false);
	}

	
	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}


	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}




	public int getSelectedProcedureID() {
		return selectedProcedureID;
	}

	public void setSelectedProcedureID(int selectedProcedureID) {
		this.selectedProcedureID = selectedProcedureID;
	}

	private TableModel listProcedures(String search) {
		if (search.equals("all")) {
			return new ProcedureTableModel(dataAccess.getProcedures(), 3);
		} else {
			return new ProcedureTableModel(
					dataAccess.findProcedureByName(search), 3);
		}
	}
}
