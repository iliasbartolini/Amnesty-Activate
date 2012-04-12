package uk.org.amnesty.activate;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
		
		Bitmap fileBitmap = BitmapFactory.decodeFile(Uri.parse(fileUri).getPath());
		Bitmap overlayedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.activate_logo);
		
		Bitmap result = overlay(fileBitmap, overlayedBitmap);
		
		image.setImageBitmap(result);
	}

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }

}
