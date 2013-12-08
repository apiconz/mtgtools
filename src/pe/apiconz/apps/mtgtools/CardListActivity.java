package pe.apiconz.apps.mtgtools;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CardListActivity extends ListActivity implements
		OnItemClickListener {

	private BDHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.card_list);
		super.onCreate(savedInstanceState);

		databaseHelper = new BDHelper(this);
		databaseHelper.openDatabase();

		List<Card> listCard = new ArrayList<Card>();

		listCard = databaseHelper.getListOfCards(listCard);
		databaseHelper.close();

		ListCardAdapter listCardAdapter = new ListCardAdapter(this, listCard);
		setListAdapter(listCardAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "Clicked row " + position, Toast.LENGTH_SHORT)
				.show();

		TextView txtCardNumber = (TextView) v.findViewById(R.id.txtCardNumber);

		Toast.makeText(this, "Valor de la carta " + txtCardNumber.getText(),
				Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(getBaseContext(), CardDetailActivity.class);
		intent.putExtra("cardNumber", txtCardNumber.getText());
		startActivity(intent);

	}

	@Override
	public void onItemClick(AdapterView parent, View v, int arg2, long arg3) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
@Override
public boolean onOptionsItemSelected(MenuItem item) {

	switch (item.getItemId()) {
	case R.id.action_aboutme:
		Intent intent = new Intent(getBaseContext(),AboutMeActivity.class);
		startActivity(intent);
		break;

	default:
		break;
	}
	
	return super.onOptionsItemSelected(item);
}
}
