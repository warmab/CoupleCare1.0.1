package activities.couple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_principalview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principalview);
	}
	
	public void calllistcal(View view){
		Intent i = new Intent(this, Activity_ListCalendar.class);
		startActivity(i);
		
	}
	

}
