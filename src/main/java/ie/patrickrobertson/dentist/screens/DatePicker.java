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
import java.util.ArrayList;
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
	private String monthTxt;
	private String dayTxt;
	private String yearTxt;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DatePicker() {
		SimpleDateFormat month = new SimpleDateFormat("MMMMMMMM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		monthTxt = month.format(Calendar.getInstance().getTime());
		dayTxt = day.format(Calendar.getInstance().getTime());
		yearTxt = year.format(Calendar.getInstance().getTime());
		setBorder(null);
		setBounds(10, 284, 292, 40);
		setLayout(null);

		comboBoxDay = new JComboBox();
		comboBoxDay.setModel(daysDecider(monthTxt, yearTxt));
		comboBoxDay.setBounds(0, 20, 42, 20);
		add(comboBoxDay);
		//added to allow for 01 style days
		comboBoxDay.setSelectedItem(String.valueOf(Integer.valueOf(dayTxt)));
		

		comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" }));
		comboBoxMonth.setBounds(52, 20, 99, 20);
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
		comboBoxYear.setModel(yearsDecider());
		comboBoxYear.setBounds(161, 20, 57, 20);
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
		if (month.equals(monthTxt) && year.equals(yearTxt)) {
			String[] days = new String[Integer.valueOf(dayTxt)];
			for (int x = 0; x < Integer.valueOf(dayTxt); x++) {
				days[x] = String.valueOf(x + 1);
			}
			return new DefaultComboBoxModel(days);
		} else {
			if (month.equals("February")) {
				if (year.equals("2012") || year.equals("2016")
						|| year.equals("2020") || year.equals("2024")
						|| year.equals("2028")) {
					return new DefaultComboBoxModel(new String[] { "1", "2",
							"3", "4", "5", "6", "7", "8", "9", "10", "11",
							"12", "13", "14", "15", "16", "17", "18", "19",
							"20", "21", "22", "23", "24", "25", "26", "27",
							"28", "29" });

				} else {
					return new DefaultComboBoxModel(new String[] { "1", "2",
							"3", "4", "5", "6", "7", "8", "9", "10", "11",
							"12", "13", "14", "15", "16", "17", "18", "19",
							"20", "21", "22", "23", "24", "25", "26", "27",
							"28" });
				}
			} else if (month.equals("September") || month.equals("April")
					|| month.equals("June") || month.equals("November")) {
				return new DefaultComboBoxModel(new String[] { "1", "2", "3",
						"4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
						"14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29", "30" });
			} else {
				return new DefaultComboBoxModel(new String[] { "1", "2", "3",
						"4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
						"14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29", "30", "31" });

			}
		}
	}
	
	private DefaultComboBoxModel monthsDecider(String year) {
		String[] allMonths = new String[] { "January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		if (year.equals(yearTxt)) {
			ArrayList<String> months = new ArrayList<String>();
			for (String month : allMonths) {
				if (!month.equals(monthTxt)) {
					months.add(month);
				} else {
					months.add(month);
					new DefaultComboBoxModel((String[]) months.toArray());
				}
			}
		}
		return new DefaultComboBoxModel(allMonths);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel yearsDecider() {
			String[] years = new String[Integer.valueOf(yearTxt)-2000];
			int y = 2000;
			for (int x = 0; x < years.length; x++) {
				years[x] = String.valueOf(y + 1);
				y++;
			}
			return new DefaultComboBoxModel(years);
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
