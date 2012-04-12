package uk.org.amnesty.activate;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageUploadOptions extends Activity{
	
	private String fileUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_upload_options);	
		
		fileUri = getIntent().getStringExtra(HandleImageUpload.FILE_URI);
		
		ImageView image = (ImageView) findViewById(R.id.image);
		
		image.setImageBitmap(HandleImageUpload.result);
	}

}
