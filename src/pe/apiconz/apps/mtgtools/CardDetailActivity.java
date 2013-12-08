package pe.apiconz.apps.mtgtools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CardDetailActivity extends Activity {

	TextView txtDetailName, txtDetailMana, txtDetailType, txtDetailText,
			txtDetailExpansion, txtDetailNumber, txtDetailRarity,
			txtDetailArtist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.card_detail);
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String cardNumber = intent.getStringExtra("cardNumber");

		BDHelper bdHelper = new BDHelper(this);
		bdHelper.openDatabase();
		Card card = bdHelper.getDetailsOfCard(cardNumber);
		bdHelper.close();

		txtDetailName = (TextView) findViewById(R.id.txtDetailName);
		txtDetailMana = (TextView) findViewById(R.id.txtDetailMana);
		txtDetailType = (TextView) findViewById(R.id.txtDetailType);
		txtDetailText = (TextView) findViewById(R.id.txtDetailText);
		txtDetailExpansion = (TextView) findViewById(R.id.txtDetailExpansion);
		txtDetailNumber = (TextView) findViewById(R.id.txtDetailNumber);
		txtDetailRarity = (TextView) findViewById(R.id.txtDetailRarity);
		txtDetailArtist = (TextView) findViewById(R.id.txtDetailArtist);

		txtDetailName.setText(card.cardName);
		txtDetailMana.setText(card.cardMana);
		txtDetailType.setText(card.cardType);
		txtDetailText.setText(card.cardText);
		txtDetailExpansion.setText(card.cardEdition);
		txtDetailNumber.setText(cardNumber);
		txtDetailRarity.setText(card.cardRarity);
		txtDetailArtist.setText(card.cardArtist);

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

}
