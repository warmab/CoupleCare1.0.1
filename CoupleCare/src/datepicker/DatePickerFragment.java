package datepicker;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		//Save data of begin period
		String dateStart = day + "/" + month + "/" + year;
		SharedPreferences cycledate = getActivity().getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = cycledate.edit();
		editor.putInt("yearb", year);
		editor.putInt("monthb", month);
		editor.putInt("dayb", day);
		editor.putString("datestart", dateStart);
		editor.commit();
	}
	

}