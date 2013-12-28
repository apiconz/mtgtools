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

		// String[] displayedValues = new String[140];
		// int minValue = -40;
		// for (int i = 0; i < displayedValues.length; i++) {
		// displayedValues[i] = Integer.toString(minValue);
		// minValue++;
		// }

		final int minValue = -50;
		final int maxValue = 100;
		numberPicker.setMinValue(0);
		numberPicker.setMaxValue(maxValue - minValue);
		numberPicker.setValue(1);
		numberPicker.setFormatter(new NumberPicker.Formatter() {
			@Override
			public String format(int index) {
				return Integer.toString(index - minValue);
			}
		});
	}
}
