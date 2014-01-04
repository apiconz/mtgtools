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

		String[] numberArray = new String[60];

		populateNumberArray(numberArray);

		numberPicker.setMinValue(0);
		numberPicker.setMaxValue(numberArray.length - 1);
		numberPicker.setDisplayedValues(numberArray);
		numberPicker.setValue(40);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
	}

	private void populateNumberArray(String[] numberArray) {
		int count = -20;

		for (int i = 0; i < numberArray.length; i++) {
			numberArray[i] = "" + count;
			count++;
		}
	}
}
