package org.android.roboxito.codbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class valido extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.valido);
		
		Toast.makeText(getApplicationContext(), "Los codigos son correctos. El vino es autentico", 2000).show();
	}

}
