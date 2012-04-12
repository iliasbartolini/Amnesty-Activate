package uk.org.amnesty.activate;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class HandleImageUpload  extends Activity{

	private static final String OVERLAY_TEXT1 = "date: 12 Apr 2012";
	private static final String OVERLAY_TEXT2 = "time 11h:37m:21s GMT";
	private static final String OVERLAY_TEXT3 = "lon: 51.522047, lat: -0.109654";
	public static final String FILE_URI = "FILE_URI";
	private String fileUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handle_image_upload);	
		
		fileUri = getIntent().getStringExtra(FILE_URI);
		
		Toast.makeText(this, "Image saved to:\n" + fileUri, Toast.LENGTH_LONG).show();
		ImageView image = (ImageView) findViewById(R.id.image);
		
		Bitmap fileBitmap = BitmapFactory.decodeFile(Uri.parse(fileUri).getPath());
		Bitmap overlayedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.activate_logo);
		
		Bitmap result = overlay(fileBitmap, overlayedBitmap);
		fileBitmap.recycle();
		overlayedBitmap.recycle();
		image.setImageBitmap(result);
	}

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(100);
		canvas.drawText(OVERLAY_TEXT1, 50, 250, paint);
		canvas.drawText(OVERLAY_TEXT2, 50, 400, paint);
		canvas.drawText(OVERLAY_TEXT3, 50, 550, paint);
        return bmOverlay;
    }

}
