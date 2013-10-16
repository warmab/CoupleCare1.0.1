package activities.couple;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_signupmen extends Activity {
	EditText etunmen, etemail, etpassync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signupmen);
		
		etunmen = (EditText) findViewById(R.id.etunamem);
		etpassync = (EditText) findViewById(R.id.etpassync);
		etemail = (EditText) findViewById(R.id.etemailm);
		
		SharedPreferences  prefe = getSharedPreferences("datawoman", Context.MODE_PRIVATE);
		etunmen.setText(prefe.getString("unamem", ""));
		etpassync.setText(prefe.getString("passync", ""));
		etemail.setText(prefe.getString("emailmen", ""));
		
		
	}
	
	public void execute(View view){
		SharedPreferences preferencias = getSharedPreferences("datawoman", Context.MODE_PRIVATE);
		String uname = etunmen.getText().toString();
		if (uname.matches("")) {
		    Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
		    return;
		}
		String passync  = etpassync.getText().toString();
		if (passync.matches("")) {
		    Toast.makeText(this, "You did not enter a pass sync", Toast.LENGTH_SHORT).show();
		    return;
		}
		String email= etemail.getText().toString();
		if (email.matches("")) {
		    Toast.makeText(this, "You did not enter a email", Toast.LENGTH_SHORT).show();
		    return;
		}
		Editor editor = preferencias.edit();
		editor.putString("emailmen",  email);
		editor.putString("passync", passync);
		editor.putString("unamem",uname);
		editor.putBoolean("Couplexist", true);
		editor.commit();
		
		
		AlertDialog.Builder dialogosm = new AlertDialog.Builder(this);
		dialogosm.setTitle("Couple");
		dialogosm.setMessage("Thank's for register your partner, do you wanna begin?");
		dialogosm.setCancelable(false);
		dialogosm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog1, int id) {
				calllogin();
				finish();
			}
		}); 
		dialogosm.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				callconfig();
				SharedPreferences preferencias1 = getSharedPreferences("datawoman", Context.MODE_PRIVATE);
				Editor editor = preferencias1.edit();
				editor.putBoolean("Couplexist", false);
				editor.commit();
			}
		});
		dialogosm.show();
		
	}
	
	private void callconfig(){
		Intent it = new Intent(this, Activity_configstart.class);
	    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}

	private void calllogin(){
		Intent it = new Intent(this, Activity_Login.class);
	    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}

}
