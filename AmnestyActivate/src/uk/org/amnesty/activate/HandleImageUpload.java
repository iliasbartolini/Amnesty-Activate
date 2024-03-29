package uk.org.amnesty.activate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HandleImageUpload  extends Activity{

	private static final String OVERLAY_TEXT1 = "date: 12 Apr 2012   11:37:21 GMT";
	private static final String OVERLAY_TEXT2 = "longitude: 51.522047";
	private static final String OVERLAY_TEXT3 = "latitude: -0.109654";
	private static final String OVERLAY_TEXT4 = "altitude: 28.38114";
	public static final String FILE_URI = "FILE_URI";
	private String fileUri;
	public static Bitmap result; //temporary "singleton" to save memory allocation OutOfMemoryError

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handle_image_upload);	
		
		fileUri = getIntent().getStringExtra(FILE_URI);
		ImageView image = (ImageView) findViewById(R.id.image);
		
		Bitmap fileBitmap = BitmapFactory.decodeFile(Uri.parse(fileUri).getPath());
		Bitmap overlayedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.activate_logo);
		
		result = overlay(fileBitmap, overlayedBitmap);
		fileBitmap.recycle();
		overlayedBitmap.recycle();
		image.setImageBitmap(result);
	}
	
	public void onUpload(View v) {
		Intent intent = new Intent(this, ImageUploadOptions.class);
		intent.putExtra(HandleImageUpload.FILE_URI, fileUri.toString());
		startActivity(intent);
	}

    static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        int textSize = 100;
		int lineSize = textSize + 50;
        paint.setTextSize(textSize);
        int startY = 200;
        int startX = 50;
		canvas.drawText(OVERLAY_TEXT1, startX, startY+=lineSize, paint);
		canvas.drawText(OVERLAY_TEXT2, startX, startY+=lineSize, paint);
		canvas.drawText(OVERLAY_TEXT3, startX, startY+=lineSize, paint);
		canvas.drawText(OVERLAY_TEXT4, startX, startY+=lineSize, paint);
        return bmOverlay;
    }

}
