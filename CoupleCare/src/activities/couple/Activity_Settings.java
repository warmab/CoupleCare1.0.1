package activities.couple;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
//import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import datepicker.DatePickerFragmentSB;
import datepicker.DatePickerFragmentSF;

public class Activity_Settings extends Activity {

	Spinner ListCycle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		//DatePicker dpstart,dpfinish;
		//Use of spinner
		ListCycle = (Spinner) findViewById(R.id.spinnerdays);
		String[] options = { "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, options);
		ListCycle.setAdapter(adapter);
		ListCycle.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView2, int position, long id) {
				
				onPause();
				Toast.makeText(
						parentView.getContext(),
						"You selected "
								+ parentView.getItemAtPosition(position)
										.toString(), Toast.LENGTH_LONG).show();
				
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}
	
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void showpickerbegin(View v) {
	    DialogFragment newFragment = new DatePickerFragmentSB();
	    newFragment.show(getFragmentManager(), "Period Beggining");
	    Log.d("Entra", "Guarda");
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	
	public void showpickerfinish(View v) {
	    DialogFragment newFragment = new DatePickerFragmentSF();
	    newFragment.show(getFragmentManager(), "Period Finish");
	    Log.d("Entra", "Guarda");
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
	
	/*public void calllogin(View view){
		Intent i = new Intent(this, Activity_login.class);
		startActivity(i);
	}*/
	
	public void calllater(View view){
		Intent i = new Intent(this, Activity_principalview.class);
		startActivity(i);
	}
	public void done(View view){
		Context context = getApplicationContext();
		CharSequence text = "Your data was save cute";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		finish();
	}
	
	public void cancel(View view){
		Context context = getApplicationContext();
		CharSequence text = "Ok, in another moment :D";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		finish();
	}
	
	public void changpass(View view){
		Intent i = new Intent(this, Activity_changespass.class);
		startActivity(i);
	}
	
	
}
