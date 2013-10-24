package activities.CCM;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_ppalview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ppalview);
	}
	
	
	public void calllistcalendar(View v){
	 Intent i = new Intent(this, Activity_ListCalendar.class);
	 startActivity(i);	
	}
	
	public void callcouplesett(View v){
		Intent i = new Intent(this, Activity_CoupleSettings.class);
		startActivity(i);
		
	}
	

}
