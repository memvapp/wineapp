package org.android.roboxito.codbar;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CodbarActivity extends Activity {
    /** Called when the activity is first created. */
	  private static final String BS_PACKAGE = "com.google.zxing.client.android";
	  public static final int REQUEST_CODE = 0x0000c0de;
	  private TextView lbl;
	  private Button boton;
	  private Activity activity;
	  public static String contents;//se define como public static para capturarla en otra actividad y static para que no pierda su valor.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.main);

	    lbl=(TextView) findViewById(R.id.lblCodbar);	    
	    boton=(Button)findViewById(R.id.button1);
	    
	    boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
			    intentScan.putExtra("PROMPT_MESSAGE", "Enfoque entre 9 y 11 cm. Viendo solo el codigo de barras");
			    String targetAppPackage = findTargetAppPackage(intentScan);
			    if (targetAppPackage == null) {
			      showDownloadDialog();
			    } else  startActivityForResult(intentScan, REQUEST_CODE);
			}
		}); 
    }
    
    private String findTargetAppPackage(Intent intent) {
        PackageManager pm = activity.getPackageManager();
        List<ResolveInfo> availableApps = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (availableApps != null) {
          for (ResolveInfo availableApp : availableApps) {
            String packageName = availableApp.activityInfo.packageName;
            if (BS_PACKAGE.contains(packageName)) {
              return packageName;
            }
          }
        }
        return null;
      }
    private AlertDialog showDownloadDialog() {
    	  final String DEFAULT_TITLE = "Instalar Barcode Scanner?";
    	  final String DEFAULT_MESSAGE =
    	      "Esta aplicacion necesita Barcode Scanner. Quiere instalarla?";
    	  final String DEFAULT_YES = "Si";
    	  final String DEFAULT_NO = "No";

        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
        downloadDialog.setTitle(DEFAULT_TITLE);
        downloadDialog.setMessage(DEFAULT_MESSAGE);
        downloadDialog.setPositiveButton(DEFAULT_YES, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Uri uri = Uri.parse("market://details?id=" + BS_PACKAGE);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
              activity.startActivity(intent);
            } catch (ActivityNotFoundException anfe) {
                // Hmm, market is not installed
            	Toast.makeText(CodbarActivity.this, "Android market no esta instalado,no puedo instalar Barcode Scanner", Toast.LENGTH_LONG).show();
            }
          }
        });
        downloadDialog.setNegativeButton(DEFAULT_NO, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {}
        });
        return downloadDialog.show();
      }

	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		    if (requestCode == REQUEST_CODE) {
		        if (resultCode == Activity.RESULT_OK) {
		          String contents = intent.getStringExtra("SCAN_RESULT");
		          String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
		          byte[] rawBytes = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
		          int intentOrientation = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
		          Integer orientation = intentOrientation == Integer.MIN_VALUE ? null : intentOrientation;
		          String errorCorrectionLevel = intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");
		          Toast.makeText(this, contents, Toast.LENGTH_LONG).show();

		          //se llama a una nueva actividad
				   //Intent i = new Intent(CodbarActivity.this, enviobdd.class);
		           Intent i = new Intent(CodbarActivity.this, codebar.class);
				   i.putExtra("codigo",contents);
		           startActivity(i);
		          //lbl.setText(contents);
		        }
		      }
		      //return;
	  }
}