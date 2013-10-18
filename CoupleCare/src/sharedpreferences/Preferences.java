package sharedpreferences;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences extends Activity {
	private String email;
	private String pass;
	private String emailh;
	private String passh;
	private String syncPass;
	private Date startDate;
	private int duration;
	private boolean isLogged;
	private Date enddate;

	public void deletedShared() {
		SharedPreferences prefs = getSharedPreferences("datawoman", MODE_PRIVATE);

		SharedPreferences.Editor editor = prefs.edit();

		editor.clear();
		editor.commit();
	}

	private String getEmail() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("email", "");
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private String getPass() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("password", "");
		return pass;
	}

	private void setPass(String pass) {
		this.pass = pass;
	}

	private String getEmailh() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("emailmen", "");
		return emailh;
	}

	private void setEmailh(String emailh) {
		this.emailh = emailh;
	}

	private String getSyncPass() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("passync", "");
		return syncPass;
	}

	private void setSyncPass(String syncPass) {
		this.syncPass = syncPass;
	}

	private Date getStartDate() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("datestart", "");
		return startDate;
	}

	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private Date getdatend() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("datend", "");
		return enddate;
	}

	private void setdatend(Date endate) {
		this.enddate = enddate;
	}
	
	private int getDuration() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getString("durationcycle", "");
		return duration;
	}

	private void setDuration(int duration) {
		this.duration = duration;
	}

	private boolean isLogged() {
		SharedPreferences prefe = getSharedPreferences("datawoman",
				Context.MODE_PRIVATE);
		prefe.getBoolean("logged", false);
		return isLogged;
	}

	private void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}