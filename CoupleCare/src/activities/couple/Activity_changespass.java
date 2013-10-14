package activities.couple;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_changespass extends Activity {
	EditText etactpass, etemail, etnpass, etvnpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepass);
		etemail = (EditText) findViewById(R.id.etmail);
		etactpass = (EditText) findViewById(R.id.etacpass);
		etnpass = (EditText) findViewById(R.id.etnpass);
		etvnpass = (EditText) findViewById(R.id.etvpass);

		SharedPreferences info = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		etemail.setText(info.getString("email", ""));
		etactpass.setText(info.getString("passync", ""));
	}

	public void changes(View view) {
		String email = etemail.getText().toString();
		if (email.matches("")) {
		    Toast.makeText(this, "You did not enter a email", Toast.LENGTH_SHORT).show();
		    return;
		}
		String pass1 = etactpass.getText().toString();
		if (pass1.matches("")) {
		    Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
		    return;
		}

		SharedPreferences info1 = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		String emailshared = info1.getString("email", "");
		String passshared = info1.getString("passync", "");
		if (email.equals(emailshared) && pass1.equals(passshared)) {

			String newpass = etnpass.getText().toString();
			if (newpass.matches("")) {
			    Toast.makeText(this, "You did not enter a new password", Toast.LENGTH_SHORT).show();
			    return;
			}
			String verpass = etvnpass.getText().toString();
			if (verpass.matches("")) {
			    Toast.makeText(this, "Please enter yout new password again", Toast.LENGTH_SHORT).show();
			    return;
			}

			if (newpass.equals(verpass)) {
				SharedPreferences info2 = getSharedPreferences("datawoman",
						Context.MODE_PRIVATE);
				Editor editor = info2.edit();
				editor.putString("passync", newpass);
				editor.commit();
				finish();
			}
		}

	}

	public void cancel(View view) {
		Context context = getApplicationContext();
		CharSequence text = "Ok, in another moment :D";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		finish();

	}

}
