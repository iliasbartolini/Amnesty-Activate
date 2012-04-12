package uk.org.amnesty.activate;

import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MediaRecording extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private Uri fileUri;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
	}

	
	public void onStartVideoCapture(View v) {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		fileUri = getOutputMediaFileUri("VID_", ".mp4");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
		startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
	}


	public void onStartImageCapture(View v) {
		// create new Intent
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri("IMG_", ".jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
				Intent intent = new Intent(this, HandleImageUpload.class);
				intent.putExtra(HandleImageUpload.FILE_URI, fileUri.toString());
				startActivity(intent);
			} else if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
				Toast.makeText(this, "Video saved to:\n" + fileUri, Toast.LENGTH_LONG).show();
			}
		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
		}
	}

	private Uri getOutputMediaFileUri(String filePrefix, String fileExtension) {
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
		
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "Failed to create directory");
				throw new MediaStorageMkDirFailedException();
			}
		}
		
		File mediaFile;
		try {
			mediaFile = File.createTempFile(filePrefix, fileExtension, mediaStorageDir);
			mediaFile.delete();
		} catch (IOException e) {
			Log.d("MyCameraApp", e.getStackTrace().toString());
			throw new MediaStorageMkDirFailedException();
		}
		
		return Uri.fromFile(mediaFile);
	}
}