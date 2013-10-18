package activities.couple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.Duration;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import calculatedays.ColorEnum;
import calculatedays.Day;
import datepicker.DatePickerFragmentfin;

public class Activity_principalview extends Activity {
	TextView tvdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principalview);
		tvdate = (TextView) findViewById(R.id.tvdate);
		Date actualdate = new Date();
		SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
		String adate = format.format(actualdate);
		tvdate.setText(adate);
		//list();
	
		
	}
	
	public void calllistcal(View view){
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
	
	public void list(){
		SharedPreferences pref = getSharedPreferences("datawoman", Context.MODE_PRIVATE);
		String dayb = pref.getString("datestart", "");
		String daye = pref.getString("datend", "");
		long periodays = pref.getLong("perioddays", 0);
		Calendar cal = new GregorianCalendar().getInstance();
	    SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");    
	    Date date1 = null;
	    try {
			date1=formattext.parse(dayb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   cal.setTime(date1);
	   String fecha1 = ""+date1;
	   String periodd = ""+periodays;
	   Log.d("Fecha",fecha1 + " "+ periodd);
		
		Day day = new Day(cal, true, "hola", ColorEnum.BLUE);
		day.setPeriodays(periodays);
		day.setPeriod(periodays);
		day.calculateFertilesDays();
		day.getSimpleDatesList();
		Log.d("Lista", day.getSimpleDatesList().toString());
		Toast.makeText(getApplicationContext(), day.getSimpleDatesList().toString(), Toast.LENGTH_LONG).show();
	}
	
	
	
	

}
