package org.android.roboxito.codbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class codebar extends Activity {
	private TextView lbl;
	
 private static String codbar;
protected void onCreate(Bundle savedInstanceState) {
			    
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.codebar);
		 lbl=(TextView) findViewById(R.id.lblCodbar);	
		 Button b1=(Button)findViewById(R.id.button1);
		 
		Bundle parametros = this.getIntent().getExtras(); //Definimos el contenedor de parametros
		if(parametros !=null) {
			
		    codbar =   parametros.getString("codigo");
			lbl.setText("El codigo se capturo correctamente: "+ codbar);
			}
		else{lbl.setText("El codigo no se capturo correctamente, intente nuevamente"); }
		
		 b1.setOnClickListener(new View.OnClickListener() {
								
					@Override
					public void onClick(View arg0) {
						 //se llama a una nueva actividad
						   Intent i = new Intent(codebar.this, nfcdemo.class);
						   i.putExtra("codigoBar",codbar);
				           startActivity(i);
					}
				});
}}
