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

public class Activity_mysignup extends Activity {
	public EditText etmail, etpass, etpassv, etuname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mysignup);
		etuname = (EditText) findViewById(R.id.etuname);
		etmail = (EditText) findViewById(R.id.etemail);
		etpass = (EditText) findViewById(R.id.etpass);
		etpassv = (EditText) findViewById(R.id.etpassv);
		SharedPreferences prefsignp = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		etuname.setText(prefsignp.getString("username", ""));
		etmail.setText(prefsignp.getString("mail", ""));
		etpass.setText(prefsignp.getString("pass", ""));
		etpassv.setText(prefsignp.getString("passv", ""));
		Boolean data = prefsignp.getBoolean("datawexist", false);
		Boolean datamen = prefsignp.getBoolean("Couplexist", false);
		if(data.equals(true) && datamen.equals(true) ){
			calllogin();
		}
		else if(data!=true){
			callsignup();
		}else if (datamen!=true) {
			callsignupmen();
		}else if(data.equals(true) && datamen.equals(false)){
			
		}
		
	}

	public void execute(View view) {

		// Variables for getText of EditText
		String uname = etuname.getText().toString();
		if (uname.matches("")) {
			Toast.makeText(this, "You did not enter a username",
					Toast.LENGTH_SHORT).show();
			return;
		}
		String email = etmail.getText().toString();
		if (email.matches("")) {
			Toast.makeText(this, "You did not enter a email",
					Toast.LENGTH_SHORT).show();
			return;
		}
		String pass = etpass.getText().toString();
		if (pass.matches("")) {
			Toast.makeText(this, "You did not enter a password",
					Toast.LENGTH_SHORT).show();
			return;
		}
		String passv = etpassv.getText().toString();
		if (passv.matches("")) {
			Toast.makeText(this, "You did not enter a pass verify",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// Create the sharedPreferences of SignUp
		SharedPreferences prefsingp1 = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		Editor editor = prefsingp1.edit();

		if (pass.equals(passv)) {
			// Put the variables like data of sharedpreferences of woman
			editor.putString("username", uname);
			editor.putString("email", email);
			editor.putString("password", pass);
			editor.putBoolean("datawexist", true);

			editor.commit();

			// Create the alert dialog to ask for if women want to register
			// partner
			AlertDialog.Builder dialogosm = new AlertDialog.Builder(this);
			dialogosm.setTitle("Optional");
			dialogosm.setMessage("¿Would you like to register your partner?");
			dialogosm.setCancelable(false);
			dialogosm.setPositiveButton("Confirm",
					new DialogInterface.OnClickListener() {
						// If she have a partner, send she to signupmen
						// activity.
						@Override
						public void onClick(DialogInterface dialog1, int id) {

							callsignupmen();

						}

					});
			dialogosm.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						// If she doesn´t have a partner put a boolean valor in
						// false, and send to configurations
						@Override
						public void onClick(DialogInterface dialog, int which) {
							SharedPreferences prefsignp2 = getSharedPreferences(
									"datawoman", Context.MODE_PRIVATE);
							Editor editor = prefsignp2.edit();
							editor.putBoolean("Couplexist", false);
							editor.commit();
							calllogin();

						}

					});
			dialogosm.show();
			// callconfigact();

		}
		// If the passwords are different show a toast and put the boolean
		// valor datawexist in false.
		else {
			Context context = getApplicationContext();
			CharSequence text = "Passwords are different.";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			SharedPreferences prefsignp3 = getSharedPreferences("datawoman",
					Context.MODE_PRIVATE);
			Editor editor2 = prefsignp3.edit();
			editor2.putBoolean("datawexist", false);
			editor2.commit();

		}

	}

	/*
	 * Call to SignUpMen for intent
	 */
	private void callsignupmen() {
		Intent it = new Intent(this, Activity_signupmen.class);
		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}
	
	private void callsignup() {
		Intent it = new Intent(this, Activity_mysignup.class);
		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}

	/*
	 * Call to Configuration activity for intent
	 */
	private void callconfigact() {
		Intent i = new Intent(this, Activity_configstart.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);

	}

	public void calllogin() {
		Intent i = new Intent(this, Activity_Login.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

}
