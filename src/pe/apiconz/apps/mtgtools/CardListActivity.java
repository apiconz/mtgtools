package pe.apiconz.apps.mtgtools;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

public class CardListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.card_list);
		super.onCreate(savedInstanceState);
		
		Card card = null;
		List<Card> listCard = new ArrayList<Card>();
		
		card = new Card();
		card.cardName="Elfos de Yanowar";
		card.cardNumber=1;
		listCard.add(card);
		
		card = new Card();
		card.cardName="Naturalizar";
		card.cardNumber=123;
		listCard.add(card);
		
		card = new Card();
		card.cardName="Hydra";
		card.cardNumber=2;
		listCard.add(card);
		
		ListCardAdapter listCardAdapter = new ListCardAdapter(this, listCard);
		setListAdapter(listCardAdapter);
	}

}
