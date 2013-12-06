package pe.apiconz.apps.mtgtools;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCardAdapter extends ArrayAdapter<Card> {

	private List<Card> listCard;
	private Activity context;

	public ListCardAdapter(Activity context, List<Card> listCard) {
		super(context, R.layout.card_list_item, listCard);
		this.listCard = listCard;
		this.context = context;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			view = inflater.inflate(R.layout.card_list_item, null);

			final ViewHolder holder = new ViewHolder();
			holder.imgCard = (ImageView) view.findViewById(R.id.imgCardImage);
			holder.txtName = (TextView) view.findViewById(R.id.txtCardTitle);
			holder.txtNumber = (TextView) view.findViewById(R.id.txtCardNumber);

			view.setTag(holder);
			holder.imgCard.setTag(this.listCard.get(position));

		} else {
			view = convertView;
			((ViewHolder) view.getTag()).imgCard.setTag(this.listCard
					.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();

		Card card = this.listCard.get(position);
		holder.imgCard.setImageResource(R.drawable.card_back);
		holder.txtName.setText(card.cardName);
		holder.txtNumber.setText(String.valueOf(card.cardNumber));

		return view;
	}

	private class ViewHolder {
		public ImageView imgCard;
		public TextView txtName;
		public TextView txtNumber;

	}

}
