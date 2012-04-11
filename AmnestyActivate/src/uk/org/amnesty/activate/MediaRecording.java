package uk.org.amnesty.activate;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

public class MediaRecording extends Activity {

	private Uri fileUri;
	

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;


	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//startVideoCaptureIntent();

		startImageCaptureIntent();
	
	}


	private void startVideoCaptureIntent() {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		fileUri = getOutputMediaFileUri("VID_", ".mp4");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
		startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
	}


	private void startImageCaptureIntent() {
		// create new Intent
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri("IMG_", ".jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			String path = (data != null) ? data.getData().toString(): "";
			if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
				Toast.makeText(this, "Image saved to:\n" + path, Toast.LENGTH_LONG).show();
			} else if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
				Toast.makeText(this, "Video saved to:\n" + path, Toast.LENGTH_LONG).show();
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
				Log.d("MyCameraApp", "failed to create directory");
				throw new MediaStorageMkDirFailedException();
			}
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = mediaStorageDir.getPath() + File.separator + filePrefix + timeStamp + fileExtension;

		File mediaFile = new File(fileName);
		
		return Uri.fromFile(mediaFile);
	}
}