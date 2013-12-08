package pe.apiconz.apps.mtgtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class BDHelper extends SQLiteOpenHelper {

	private Context myContext;
	private static String DB_PATH = "";
	private static String DB_NAME = "mtgtools.sqlite";
	public SQLiteDatabase sqLiteDatabase;
	private static final String TAG = "BDHelper";
	String tableName = "table_card";
	
	public BDHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		boolean isDatabaseExist = checkDatabase();

		if (isDatabaseExist) {
			openDatabase();
		} else {
			Log.i(TAG, "Se creará la base de datos");
			try {
				createDatabase();
				this.close();
				openDatabase();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Toast.makeText(context, "Initial database is created",
					Toast.LENGTH_LONG).show();

		}
	}

	private void createDatabase() throws IOException {
		this.getReadableDatabase();
		copyDatabase();

	}

	private void copyDatabase() throws IOException {
		InputStream mInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);

		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer)) > 0) {
			mOutput.write(mBuffer, 0, mLength);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	public boolean openDatabase() {
		String mPath = DB_PATH + DB_NAME;
		sqLiteDatabase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
		return sqLiteDatabase != null;
	}

	private boolean checkDatabase() {

		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public synchronized void close() {
		if (sqLiteDatabase != null)
			sqLiteDatabase.close();
		super.close();
	}

	public List<Card> getListOfCards(List<Card> listCard) {
		String selectQuery = "SELECT card_number,card_title FROM " + tableName;
		this.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Card card = new Card();
				card.cardName = cursor.getString(1);
				card.cardNumber = cursor.getInt(0);
				listCard.add(card);

			} while (cursor.moveToNext());
		}
		sqLiteDatabase.close();
		return listCard;
	}

	public Card getDetailsOfCard(String cardNumber) {
		String selectQuery = "SELECT card_number,card_title,card_type,card_mana,card_rarity,card_artist,card_edition FROM " + tableName + " WHERE card_number = ?";
		this.getReadableDatabase();
		String[] arguments = new String[]{cardNumber};
		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, arguments);

		Card card = new Card();
		if(cursor.moveToFirst()){
			card.cardNumber = cursor.getInt(0);
			card.cardName = cursor.getString(1);
			card.cardType = cursor.getString(2);
			card.cardMana = cursor.getString(3);
			card.cardRarity = cursor.getString(4);
			card.cardArtist = cursor.getString(5);
			card.cardEdition = cursor.getString(6);
		}
		sqLiteDatabase.close();
		return card;
	}
}
