package ie.patrickrobertson.dentist.screens;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

public class DatePicker extends JPanel {

	private JComboBox comboBoxDay;
	private JComboBox comboBoxMonth;
	private JComboBox comboBoxYear;
	private DefaultComboBoxModel days;
	private Date date;
	private JLabel lblDate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DatePicker() {
		SimpleDateFormat month = new SimpleDateFormat("MMMMMMMM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		String monthTxt = month.format(Calendar.getInstance().getTime());
		String dayTxt = day.format(Calendar.getInstance().getTime());
		String yearTxt = year.format(Calendar.getInstance().getTime());
		setBorder(null);
		setBounds(10, 284, 284, 40);
		setLayout(null);

		comboBoxDay = new JComboBox();
		comboBoxDay.setModel(daysDecider(monthTxt, yearTxt));
		comboBoxDay.setBounds(0, 20, 76, 20);
		add(comboBoxDay);
		comboBoxDay.setSelectedItem(dayTxt);

		comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" }));
		comboBoxMonth.setBounds(80, 20, 114, 20);
		add(comboBoxMonth);
		comboBoxMonth.setSelectedItem(monthTxt);
		comboBoxMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboBoxDay.setModel(daysDecider(
						(String) comboBoxMonth.getSelectedItem(),
						(String) comboBoxYear.getSelectedItem()));

			}
		});

		comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] { "2010",
				"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
				"2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
		comboBoxYear.setBounds(193, 20, 85, 20);
		add(comboBoxYear);
		comboBoxYear.setSelectedItem(yearTxt);

		lblDate = new JLabel("Date:");
		lblDate.setBounds(0, 5, 46, 15);
		add(lblDate);
		comboBoxYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxDay.setModel(daysDecider(
						(String) comboBoxMonth.getSelectedItem(),
						(String) comboBoxYear.getSelectedItem()));
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel daysDecider(String month, String year) {

		if (month.equals("February")) {
			if (year.equals("2012") || year.equals("2016")
					|| year.equals("2020") || year.equals("2024")
					|| year.equals("2028")) {
				return new DefaultComboBoxModel(new String[] { "1", "2", "3",
						"4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
						"14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29" });

			} else {
				return new DefaultComboBoxModel(new String[] { "1", "2", "3",
						"4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
						"14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28" });
			}
		} else if (month.equals("September") || month.equals("April")
				|| month.equals("June") || month.equals("November")) {
			return new DefaultComboBoxModel(new String[] { "1", "2", "3", "4",
					"5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
					"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30" });
		} else {
			return new DefaultComboBoxModel(new String[] { "1", "2", "3", "4",
					"5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
					"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" });

		}
	}

	public JComboBox getComboBoxDay() {
		return comboBoxDay;
	}

	public void setComboBoxDay(JComboBox comboBoxDay) {
		this.comboBoxDay = comboBoxDay;
	}

	public JComboBox getComboBoxMonth() {
		return comboBoxMonth;
	}

	public void setComboBoxMonth(JComboBox comboBoxMonth) {
		this.comboBoxMonth = comboBoxMonth;
	}

	public JComboBox getComboBoxYear() {
		return comboBoxYear;
	}

	public void setComboBoxYear(JComboBox comboBoxYear) {
		this.comboBoxYear = comboBoxYear;
	}

	public Date getDate() {

		Date today = new Date();
		DateFormat df = new SimpleDateFormat("dd/MMMMMMM/yyyy");
		String date = ((String) getComboBoxDay().getSelectedItem()).concat("/")
				.concat(((String) getComboBoxMonth().getSelectedItem()).concat(
						"/").concat(
						(String) getComboBoxYear().getSelectedItem()));

		try {
			today = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return today;
	}

}
