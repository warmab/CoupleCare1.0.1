package activities.couple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import listcalendar.ListEntrance;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.FeatureInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import calculatedays.ColorEnum;
import calculatedays.Day;
import calculatedays.SimpleDate;
import datepicker.DatePickerFragmentfin;

public class Activity_principalview extends Activity {
	TextView tvdate, tvdatelist, tvstatusdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principalview);
		tvdate = (TextView) findViewById(R.id.tvdate);
		// tvstatusdate = (TextView) findViewById(R.id.statusdate);

		// Fecha actual
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		int calendarTime = Calendar.DAY_OF_MONTH;
		int temp = calendar.get(calendarTime);
		calendar.set(calendarTime, temp);
		SimpleDateFormat formatoFecha = new SimpleDateFormat();
		formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		Date fechaSum = calendar.getTime();
		formatoFecha.applyPattern("dd/MM/yyyy");
		String fechaRespuesta = formatoFecha.format(fechaSum);
		tvdate.setText(fechaRespuesta);

		SharedPreferences pref = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String datestart = pref.getString("datestart", "");
		Log.d("datestart", datestart);
		int perioddays = pref.getInt("perioddays", 0);
		int cycleperiod = pref.getInt("durationcycle", 0);

		Calendar cal = GregorianCalendar.getInstance();
		SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;

		try {
			date1 = formattext.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cal.setTime(date1);
		String datestartlist = pref.getString("datestart", "");
		SharedPreferences pref2 = getSharedPreferences("actlist",
				Context.MODE_PRIVATE);

		ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();
		Day dia = new Day(cal, true, "hola", ColorEnum.BLUE);
		dia.setCyclePeriod(cycleperiod);
		dia.setPeriod(perioddays);
		dia.calculateFertilesDays();
		for (SimpleDate fecha : dia.getSimpleDatesList()) {

			datos.add(new ListEntrance(R.drawable.ic_launcher,
					fecha.toString(), fecha.getColor().toString()));

			// Toast.makeText(getApplicationContext(), fecha.toString(),
			// Toast.LENGTH_LONG).show();
			int dayac = calendar.get(calendar.DAY_OF_MONTH);
			int monthac = calendar.get(calendar.MONTH);
			int yearac = calendar.get(calendar.YEAR);

			SimpleDate date = new SimpleDate(dayac, monthac, yearac,
					fecha.getDayType());

			for (int i = 1; i < dia.getSimpleDatesList().size(); i++) {
				if (dia.getSimpleDatesList().get(i).equals(date)) {
					tvdatelist.setText(fecha.toString());
				}

			}

		}

	}

	public void calllistcal(View view) {
		Intent i = new Intent(this, Activity_ListCalendar.class);
		startActivity(i);

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void showpickerfinish(View v) {
		DialogFragment newFragment = new DatePickerFragmentfin();
		newFragment.show(getFragmentManager(), "Period Finish");
		date();
	}

	// Es llamado desde un boton, para calcular la diferencia de dias
	public void date() {
		SharedPreferences pref = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String start = pref.getString("datestart", "");
		String fin = pref.getString("datend", "");
		Calendar cbegin = GregorianCalendar.getInstance();
		Calendar cfinish = GregorianCalendar.getInstance();
		Date dateb = null;
		Date datef = null;
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dateb = formatdate.parse(start);
			datef = formatdate.parse(fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diference = Math.abs(datef.getDate() - dateb.getDate());
		Editor editor = pref.edit();
		editor.putLong("perioddays", diference);
		editor.commit();

		Toast.makeText(getApplicationContext(), "Thanks for your information",
				Toast.LENGTH_LONG).show();

		String begin = "" + dateb;
		String finish = "" + datef;
		String str = "" + diference;
		Log.d("DiferenceDate", str);
		Log.d("Begin", begin);
		Log.d("finish", finish);
	}

	public void list() {
		SharedPreferences pref = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String dayb = pref.getString("datestart", "");
		String daye = pref.getString("datend", "");
		int periodays = pref.getInt("perioddays", 0);
		Calendar cal = new GregorianCalendar().getInstance();
		SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		try {
			date1 = formattext.parse(dayb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cal.setTime(date1);
		String fecha1 = "" + date1;
		String periodd = "" + periodays;
		Log.d("Fecha", fecha1 + " " + periodd);

		Day day = new Day(cal, true, "hola", ColorEnum.BLUE);
		day.setPeriodays(periodays);
		day.setPeriod(periodays);
		day.calculateFertilesDays();
		day.getSimpleDatesList();
		Log.d("Lista", day.getSimpleDatesList().toString());
		Toast.makeText(getApplicationContext(),
				day.getSimpleDatesList().toString(), Toast.LENGTH_LONG).show();
	}

	public void callsettings(View v) {
		Intent i = new Intent(this, Activity_Settings.class);
		startActivity(i);

	}

}
