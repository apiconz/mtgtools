package pe.apiconz.apps.mtgtools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class CounterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_counter);
		super.onCreate(savedInstanceState);
		
		
		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
	}
}
