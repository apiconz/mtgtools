package pe.apiconz.apps.mtgtools;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

	public static final String TAG = DownloadImageTask.class.getCanonicalName();

	private final WeakReference<ImageView> imageViewReference;

	public DownloadImageTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	@Override
	protected Bitmap doInBackground(String... params) {

		String url = params[0];
		final AndroidHttpClient client = AndroidHttpClient
				.newInstance("Android");
		final HttpGet getRequest = new HttpGet(url);

		try {
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(TAG, "Hubo un error durante la descarga de la imagen");
				return null;
			}

			final HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				InputStream inputStream = null;
				try {
					inputStream = httpEntity.getContent();
					final Bitmap bitmap = BitmapFactory
							.decodeStream(inputStream);
					return bitmap;

				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					httpEntity.consumeContent();
				}
			}
		} catch (Exception e) {
			getRequest.abort();
			Log.w(TAG,
					"Se produjo una excepción durante la descarga de la imagen");
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return null;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (imageViewReference != null) {
			ImageView imageView = imageViewReference.get();

			if (imageView != null) {
				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					imageView.setImageDrawable(imageView.getContext()
							.getResources().getDrawable(R.drawable.card_back));
				}
			}
		}

	}

}
