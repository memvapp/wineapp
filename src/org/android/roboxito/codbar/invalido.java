package org.android.roboxito.codbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class invalido extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invalido);
		Toast.makeText(getApplicationContext(), "Los códigos no coinciden. Puede que este frente a una falsificación.", 2000).show();
	}

}
