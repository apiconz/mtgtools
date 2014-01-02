package pe.apiconz.apps.mtgtools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CounterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_counter);
		super.onCreate(savedInstanceState);

		NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);

		numberPicker.setMinValue(-50);
		numberPicker.setMaxValue(50);
		numberPicker.setWrapSelectorWheel(true);
		numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

				Toast.makeText(getBaseContext(), "Viejo valor:" + oldVal + " - Nuevo valor: " + newVal, Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
