package activities.couple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import listcalendar.ListEntrance;
import listcalendar.List_Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import calculatedays.ColorEnum;
import calculatedays.Day;
import calculatedays.SimpleDate;

public class Activity_ListCalendar extends Activity {
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		SharedPreferences pref = getSharedPreferences("datawoman", Context.MODE_PRIVATE);
		String datestart = pref.getString("datestart", "");
		Log.d("datestart", datestart);
		int perioddays = pref.getInt("perioddays", 0);
		int cycleperiod = pref.getInt("durationcycle", 0);
		
		Calendar cal = GregorianCalendar.getInstance();
	    SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");    
	    Date date1 = null;
	    
	    try {
			date1=formattext.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	   cal.setTime(date1);
	   String datestartlist = pref.getString("datestart", "");
	   SharedPreferences pref2 = getSharedPreferences("actlist", Context.MODE_PRIVATE);// Se crea una segunda sharedpreferences
	   
	   Editor e = pref2.edit();
	   e.putString("newstartdatel",datestartlist); //Guardo la fecha actual de inicio
	   e.putInt("perioddays", perioddays);//guardo los dias de periodo
	   e.putInt("durationcycle", cycleperiod);//guardo la duracion del ciclo en la nueva shared
	   e.commit();
	   String fecha1 = ""+date1;
	   String periodd = ""+perioddays;
	   Log.d("Fecha",fecha1 + " "+ periodd);
	   
	   
	        ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();  
	        Day dia = new Day(cal,true,"hola",ColorEnum.BLUE);
	        dia.setCyclePeriod(cycleperiod);
	        dia.setPeriod(perioddays);
	        dia.calculateFertilesDays();
	       for(SimpleDate fecha : dia.getSimpleDatesList()) {
	        	
	        	datos.add(new ListEntrance(R.drawable.globorosa, fecha.toString(), fecha.getColor().toString()));
	        	
	      
	        
	       }
	        
	        list = (ListView) findViewById(R.id.ListView_listado);
	        list.setAdapter(new List_Adapter(this, R.layout.activity_listcalendar, datos){
				@Override
				public void onEntrada(Object entrada, View view) {
			        if (entrada != null) {
			        	
			            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
			            if (texto_superior_entrada != null) 
			            	texto_superior_entrada.setText(((ListEntrance) entrada).get_textoEncima()); 

			            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
			            if (texto_inferior_entrada != null)
			            	texto_inferior_entrada.setText(((ListEntrance) entrada).get_textoDebajo()); 

			            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
			            if (imagen_entrada != null)
			            	imagen_entrada.setImageResource(((ListEntrance) entrada).get_idImagen());
			        }
				}
			});

	        list.setOnItemClickListener(new OnItemClickListener() { 
				@Override
				public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
					ListEntrance elegido = (ListEntrance) pariente.getItemAtPosition(posicion); 

	                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
	                Toast toast = Toast.makeText(Activity_ListCalendar.this, texto, Toast.LENGTH_LONG);
	                toast.show();
				}
	        });

	    }
	
	
	
	public void next(View view){
		SharedPreferences pref = getSharedPreferences("actlist", Context.MODE_PRIVATE);
		String datestart = pref.getString("newstartdatel", "");
		Log.d("newstartdatel", datestart);
		int perioddays = pref.getInt("perioddays", 0);
		int cycleperiod = pref.getInt("durationcycle", 0);
		
		
		Calendar cal = GregorianCalendar.getInstance();
		
		
	    SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");    
	    Date date1 = null;
	    
	    try {
			date1=formattext.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   cal.setTime(date1);
	   cal.add(Calendar.DAY_OF_MONTH,cycleperiod);
	   Log.d("cycle", ""+cycleperiod);
	   String fecha1 = ""+date1;
	   String periodd = ""+perioddays;
	   Log.d("Fecha",fecha1 + " "+ periodd + cal.toString());
	   
	   
	        ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();  
	        Day dia = new Day(cal,true,"hola",ColorEnum.BLUE);
	        dia.setCyclePeriod(cycleperiod);
	        dia.setCalendar(cal);
	        dia.setPeriod(perioddays);
	        dia.calculateFertilesDays();
	       for(SimpleDate fecha : dia.getSimpleDatesList()) {
	        	
	        	datos.add(new ListEntrance(R.drawable.globorosa, fecha.toString(), fecha.getColor().toString()));
	        
	       }
	        
	        list = (ListView) findViewById(R.id.ListView_listado);
	        list.setAdapter(new List_Adapter(this, R.layout.activity_listcalendar, datos){
				@Override
				public void onEntrada(Object entrada, View view) {
			        if (entrada != null) {
			            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
			            if (texto_superior_entrada != null) 
			            	texto_superior_entrada.setText(((ListEntrance) entrada).get_textoEncima()); 

			            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
			            if (texto_inferior_entrada != null)
			            	texto_inferior_entrada.setText(((ListEntrance) entrada).get_textoDebajo()); 

			            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
			            if (imagen_entrada != null)
			            	imagen_entrada.setImageResource(((ListEntrance) entrada).get_idImagen());
			        }
				}
			});

	        list.setOnItemClickListener(new OnItemClickListener() { 
				@Override
				public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
					ListEntrance elegido = (ListEntrance) pariente.getItemAtPosition(posicion); 

	                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
	                Toast toast = Toast.makeText(Activity_ListCalendar.this, texto, Toast.LENGTH_LONG);
	                toast.show();
				}
	        });
	        /*Guardo nueva fecha*/
	        String newdate = formattext.format(date1);
	 	   SharedPreferences pref1 = getSharedPreferences("actlist", Context.MODE_PRIVATE);
	 	   Editor e = pref1.edit();
	 	   e.putString("newstartdatel", newdate);
	 	   e.commit();
	       
	 	   
	        

		
	}

}
