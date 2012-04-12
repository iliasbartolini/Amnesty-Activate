package uk.org.amnesty.activate;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class HandleImageUpload  extends Activity{

	public static final String FILE_URI = "FILE_URI";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handle_image_upload);	

		String fileUri = getIntent().getStringExtra(FILE_URI);
		
		Toast.makeText(this, "Image saved to:\n" + fileUri, Toast.LENGTH_LONG).show();

		ImageView image = (ImageView) findViewById(R.id.image);
		image.setImageURI(Uri.parse(fileUri));

	}


}
