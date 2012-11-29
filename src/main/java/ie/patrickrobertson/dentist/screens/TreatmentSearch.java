package ie.patrickrobertson.dentist.screens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ie.patrickrobertson.dentist.Procedure;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.ProcedureTableModel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TreatmentSearch extends LayoutTemplate {
	private JTable procedureListPanel;
	private JScrollPane scroller;
	private JPanel listOfProcedures;
	private DataAccess dataAccess;
	private DatePicker beforeDate;
	private DatePicker afterDate;
	private JButton btnSearch;
	private JButton btnResetButton;
	private int selectedProcedureID;

	public TreatmentSearch(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
		//intialize select Procedure to -1 to allow for people not searching by Procedure
		selectedProcedureID = -1;

		JLabel lblAfter = new JLabel("After:");
		lblAfter.setBounds(30, 11, 46, 14);
		add(lblAfter);

		beforeDate = new DatePicker();
		beforeDate.setBounds(30, 26, 275, 40);
		add(beforeDate);

		JLabel lblBefore = new JLabel("Before");
		lblBefore.setBounds(335, 11, 46, 14);
		add(lblBefore);

		afterDate = new DatePicker();
		afterDate.setBounds(335, 26, 275, 40);
		add(afterDate);

		JLabel lblResults = new JLabel("Select Procedure:");
		lblResults.setBounds(30, 77, 275, 14);
		add(lblResults);

		listOfProcedures = new JPanel();
		listOfProcedures.setBounds(30, 102, 580, 238);
		add(listOfProcedures);
		listOfProcedures.setLayout(null);

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
				selectedProcedureID = (int) procedureListPanel.getModel()
						.getValueAt(procedureListPanel.getSelectedRow(), 0);

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

		scroller = new JScrollPane(procedureListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 580, 238);

		listOfProcedures.add(scroller);
		add(listOfProcedures);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(521, 351, 89, 23);
		add(btnSearch);

		JLabel lblNoteIfNo = new JLabel(
				"Note if no procedure selected it will search all");
		lblNoteIfNo.setBounds(30, 351, 375, 14);
		add(lblNoteIfNo);

		btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(422, 351, 89, 23);
		add(btnResetButton);
	}

	private TableModel listProcedures(String search) {
		if (search.equals("all")) {
			return new ProcedureTableModel(dataAccess.getProcedures(), 3);
		} else {
			return new ProcedureTableModel(
					dataAccess.findProcedureByName(search), 3);
		}
	}

	public DatePicker getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(DatePicker beforeDate) {
		this.beforeDate = beforeDate;
	}

	public DatePicker getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(DatePicker afterDate) {
		this.afterDate = afterDate;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnResetButton() {
		return btnResetButton;
	}

	public void setBtnResetButton(JButton btnResetButton) {
		this.btnResetButton = btnResetButton;
	}

	public int getSelectedProcedureID() {
		return selectedProcedureID;
	}

	public void setSelectedProcedureID(int selectedProcedureID) {
		this.selectedProcedureID = selectedProcedureID;
	}

	
}
