package activities.couple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login extends Activity {
	EditText etuname, etpass;
	TextView tvmessage;
	ImageButton btnsignup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etuname = (EditText) findViewById(R.id.etuname);
		etpass = (EditText) findViewById(R.id.etpass);
		tvmessage = (TextView) findViewById(R.id.tvmessage);
		btnsignup = (ImageButton) findViewById(R.id.btnsignuo);
		/*SharedPreferences pref = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		Boolean loged = pref.getBoolean("logged", false);
		if (loged.equals(true)) {
			callppalview();
		} else {

			Boolean data = pref.getBoolean("datawexist", false);

			if (data.equals(true)) {
				tvmessage.setVisibility(View.INVISIBLE);
				btnsignup.setVisibility(View.INVISIBLE);
			} else {
				tvmessage.setVisibility(View.VISIBLE);
				btnsignup.setVisibility(View.VISIBLE);
			}
		}*/

	}

	public void execute(View view) {
		String uname = etuname.getText().toString();
		String pass = etpass.getText().toString();
		Log.d("uname", uname);
		Log.d("pass", pass);

		SharedPreferences settigns = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String uname1 = settigns.getString("username", "");
		String passshared = settigns.getString("password", "");
		// finish();

		if (uname.equals(uname1) && pass.equals(passshared)) {
			Toast.makeText(getApplicationContext(), "Welcome" + " " +uname,
					Toast.LENGTH_LONG).show();
			Boolean logged = true;
			Editor editor = settigns.edit();
			editor.putBoolean("logged", logged);
			editor.commit();
			callstart();

		} else if (uname != uname1 && pass.equals(passshared)) {
			Toast.makeText(getApplicationContext(), "Email is incorrect",
					Toast.LENGTH_LONG).show();
		} else if (pass != passshared && uname.equals(uname1)) {
			Toast.makeText(getApplicationContext(), "Password is incorrect",
					Toast.LENGTH_LONG).show();
		} else if (uname != uname1 && pass != passshared) {
			Toast.makeText(getApplicationContext(), "you're not my owner",
					Toast.LENGTH_LONG).show();
		}
	}

	public void callsignup(View view) {
		Intent i = new Intent(this, Activity_mysignup.class);
		startActivity(i);
	}

	public void callstart() {
		Intent a = new Intent(this, Activity_configstart.class);
		a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
	}

	public void callppalview() {
		Intent a = new Intent(this, Activity_principalview.class);
		a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
		Log.d("hola", "estoy en la ppalview");
	}

}
