package uk.org.amnesty.activate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageUploadOptions extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_upload_options);	
		ImageView image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(HandleImageUpload.result);
	}

}
