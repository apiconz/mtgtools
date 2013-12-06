package pe.apiconz.apps.mtgtools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {

	private Context myContext;
	private static String DB_PATH = "";
	private static String DB_NAME = "mtgtools.sqlite";
	public SQLiteDatabase sqLiteDatabase;
	private static final String TAG = "BDHelper";

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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createDatabase() throws IOException {
		this.getReadableDatabase();
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

	private boolean openDatabase() {
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
}
