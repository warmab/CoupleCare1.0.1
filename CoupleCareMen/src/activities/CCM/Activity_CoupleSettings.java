package activities.CCM;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_CoupleSettings extends Activity {
	
	EditText emailw,syncpass;
	ImageButton btnenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_couplesettings);
		emailw = (EditText) findViewById(R.id.etemailw);
		syncpass = (EditText) findViewById(R.id.etsynpass);
		
	}
	
	public void execute(View v){
		
		
		String email = emailw.getText().toString();
		if (email.matches("")) {
		    Toast.makeText(this, "You did not enter a Email", Toast.LENGTH_SHORT).show();
		    return;
		}
		
		
		String spass = syncpass.getText().toString();
		if (email.matches("")) {
		    Toast.makeText(this, "You did not enter a Sync Pass", Toast.LENGTH_SHORT).show();
		    return;
		}
		
		SharedPreferences pref = getSharedPreferences("prefcouple", Context.MODE_PRIVATE);
		Editor e = pref.edit();
		e.putString("emailwoman", email);
		e.putString("SyncPass", spass);
	}
	
	public void callppalview(View v){
		Intent i = new Intent(this, Activity_ppalview.class);
		startActivity(i);
	}
	

}
