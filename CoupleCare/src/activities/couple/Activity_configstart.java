package activities.couple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import datepicker.DatePickerFragment;
import datepicker.DatePickerFragmentfin;

@SuppressLint("NewApi")
public class Activity_configstart extends Activity {
	Spinner ListCycle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configstart);
		DatePicker dpstart, dpfinish;
		// Use of spinner
		ListCycle = (Spinner) findViewById(R.id.cycledays);
		String[] options = { "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, options);
		ListCycle.setAdapter(adapter);
		ListCycle.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView2, int position, long id) {
				Toast.makeText(
						parentView.getContext(),
						"You selected "
								+ parentView.getItemAtPosition(position)
										.toString(), Toast.LENGTH_LONG).show();
				onPause();

			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showpickerbegin(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "Period Beggining");
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void showpickerfinish(View v) {
		DialogFragment newFragment = new DatePickerFragmentfin();
		newFragment.show(getFragmentManager(), "Period Finish");

	}

	public void date() {
		SharedPreferences pref = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String start = pref.getString("datestart", "");
		String fin = pref.getString("datefinish", "");
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

	@Override
	public void onPause() {
		// get Sinner Selected text here
		String selectedtext = ListCycle.getSelectedItem().toString();
		int duration = Integer.parseInt(selectedtext);

		// Create SharedPreferences to store selected value

		SharedPreferences spinnerPrefs = this.getSharedPreferences("datawoman",
				MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = spinnerPrefs.edit();
		prefsEditor.putInt("durationcycle", duration);
		prefsEditor.commit();

		super.onPause();

	}

	public void callppalview(View view) {
		date();
		Intent i = new Intent(this, Activity_principalview.class);
		startActivity(i);

	}

	public void callsettings(View view) {
		Intent i = new Intent(this, Activity_Settings.class);
		startActivity(i);
	}

}
