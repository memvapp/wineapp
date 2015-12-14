package org.android.roboxito.codbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;



import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class enviobdd extends Activity {
	private TextView lbl;
	private static String cc;
	private static String cc2;
	private static String band;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//se utilizan para poder utilizar la version 4 de android.
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enviobdd);
		 lbl=(TextView) findViewById(R.id.lblCodbar);	
		 Button b1=(Button)findViewById(R.id.button1);
		 
		Bundle parametros = this.getIntent().getExtras(); //Definimos el contenedor de parametros
		if(parametros !=null) {
		     cc = parametros.getString("codigoBar");
		     cc2 = parametros.getString("codigonfc");
		     
			lbl.setText("Los codigos son: "+cc+" - "+cc2);
			}
		
		
	//evento boton que se activa al hacer click y que activa el script en php consultar.
	 b1.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast") @Override
			public void onClick(View v) {
				JSONArray ja=null;
				try {
					String data;
					 //data=httpGetData("http://10.0.3.2/prueba/consultarUsuario.php?cc="+cc.toString());
				    data=httpGetData("http://awine.site90.com/awine/consultar.php?cc="+cc.toString()+"&nfc="+cc2.toString());
					if(data.length()>1){
						
						ja=new JSONArray(data);
						Toast.makeText(getApplicationContext(), "Operación realizada con éxito.", 1000).show();}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique su conexion a internet y vuelva a intentarlo.", 1000).show();
					
				}
				try{
					
					  //lbl.setText("true "+ja.getString(0));
				      ja.getString(0);
					  
					 //se llama a una nueva actividad
					  Intent i = new Intent(enviobdd.this, valido.class);
			          startActivity(i);
				
					} catch (Exception e) {

						//lbl.setText("Falso");
						 //se llama a una nueva actividad
						  Intent i = new Intent(enviobdd.this, invalido.class);
				          startActivity(i);
						
		
			}
		 	
	}
	 
});}
	 
	//Funcion que hace la llamada del script php para conectar con la base de datos.
	 public String httpGetData(String mURL) {
         String response="";
         mURL=mURL.replace(" ", "%20");
          Log.i("LocAndroid Response HTTP Threas","Ejecutando get 0: "+mURL);
           HttpClient httpclient = new DefaultHttpClient();
           
          Log.i("LocAndroid Response HTTP Thread","Ejecutando get 1");
     	HttpGet httppost = new HttpGet(mURL);
          Log.i("LocAndroid Response HTTP Thread","Ejecutando get 2");
 try {
    

          Log.i("LocAndroid Response HTTP","Ejecutando get");
         // Execute HTTP Post Request
       ResponseHandler<String> responseHandler=new BasicResponseHandler();
         	response = httpclient.execute(httppost,responseHandler);
          Log.i("LocAndroid Response HTTP",response);
     	} catch (ClientProtocolException e) {
         Log.i("LocAndroid Response HTTP ERROR 1",e.getMessage());
         // TODO Auto-generated catch block
     } catch (IOException e) {
         
         Log.i("LocAndroid Response HTTP ERROR 2",e.getMessage());
         // TODO Auto-generated catch block
     }
     return response;
     
     }  
}