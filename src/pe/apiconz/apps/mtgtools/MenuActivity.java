package pe.apiconz.apps.mtgtools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		ImageButton imgBtnCardList = (ImageButton) findViewById(R.id.imgBtnCardList);
		ImageButton imgBtnDice = (ImageButton) findViewById(R.id.imgBtnDice);

		imgBtnCardList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i;
				i = new Intent(getBaseContext(), CardListActivity.class);
				startActivity(i);
			}
		});
		
		imgBtnDice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i;
				i = new Intent(getBaseContext(), CounterActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
