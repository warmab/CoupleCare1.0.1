package activities.couple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import listcalendar.ListEntrance;
import listcalendar.List_Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
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
		SharedPreferences pref = getSharedPreferences("datoswoman", Context.MODE_PRIVATE);
		int day = pref.getInt("dayb", 0); 
		int month = pref.getInt("monthb", 0);
		int year = pref.getInt("yearb", 0);
		
		Calendar cal = GregorianCalendar.getInstance();
	       cal.set(Calendar.DAY_OF_MONTH, day);
	       cal.set(Calendar.YEAR, year);
	       cal.set(Calendar.MONTH, month);
	        

	        ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();  
	        Day dia = new Day(cal,true,"hola",ColorEnum.BLUE);
	       
	        dia.calculateFertilesDays();
	        for(int i=0; i<=cal.MONTH; i++){
	        for(SimpleDate fecha : dia.getSimpleDatesList()) {
	        	
	        	datos.add(new ListEntrance(R.drawable.ic_launcher, fecha.toString(), fecha.getColor().toString()));
	        	 cal.add(Calendar.MONTH,1);
	        }
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
	

}
