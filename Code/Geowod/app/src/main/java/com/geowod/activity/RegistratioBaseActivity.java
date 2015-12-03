package com.geowod.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.geowod.R;
import com.geowod.crop.AlbumStorageDirFactory;
import com.geowod.crop.BaseAlbumDirFactory;
import com.geowod.crop.CropImage;
import com.geowod.crop.FroyoAlbumDirFactory;
import com.geowod.crop.ScalingUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistratioBaseActivity extends Activity {

	private AlbumStorageDirFactory mAlbumStorageDirFactory;
	public String mCurrentPhotoPath = "";
	public String mCurrentPhotoPath1 = "";
	private String newPath = "";
	private String newPath1 = "";
	private File _photoFile;
	private Uri mImageUri;
	private final String JPEG_FILE_PREFIX = "IMG_";
	private final String JPEG_FILE_SUFFIX = ".jpg";
	private String timeStamp = "", imageFileName = "";
	public static final int CAMERA_REQUEST = 0;
	public static final int PICK_IMAGE_REQUEST = 1;
	public static final int CAMERA_REQUEST1 = 0;
	public static final int PICK_IMAGE_REQUEST1 = 1;
	private String[] arr = { "file" };
	private String[] arr1 = { "" };
	private String filePAthToServer = "";

	// ********************************************************************************

	// **************************************************************************

	/**
	 * Show dialog for selecting image from gallery or camera
	 */
	// ******************************************************************************

	public void showTakeImagePopup() {

		final CharSequence[] items = { "Select Image From Gallery", "Open Camera", "Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Photo");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				switch (item) {
				case 0:

					openGallery();

					break;

				case 1:
					openCamera();
					break;

				case 3:
					break;
				}

			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void showTakeImagePopup1() {

		final CharSequence[] items = { "Select Image From Gallery", "Open Camera", "Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Photo");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				switch (item) {
					case 0:

						openGallery1();

						break;

					case 1:
						openCamera1();
						break;

					case 3:
						break;
				}

			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	// ***********************************************************************

	private void openGallery() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PICK_IMAGE_REQUEST);
	}

	private void openGallery1() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PICK_IMAGE_REQUEST1);
	}

	// ********************************************************************************

	protected void openCamera() {
		try {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
				mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
			} else {
				mAlbumStorageDirFactory = new BaseAlbumDirFactory();
			}
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			try {
				_photoFile = setUpPhotoFile();
				mCurrentPhotoPath = _photoFile.getAbsolutePath();
				Log.v("", "mCurrentPhotoPath=" + mCurrentPhotoPath);
				mImageUri = Uri.fromFile(_photoFile);
				Log.v("", "mImageUri-------" + mImageUri);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoFile));
			} catch (Exception e) {
				e.printStackTrace();
				_photoFile = null;
			}
			this.startActivityForResult(intent, CAMERA_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void openCamera1() {
		try {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
				mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
			} else {
				mAlbumStorageDirFactory = new BaseAlbumDirFactory();
			}
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			try {
				_photoFile = setUpPhotoFile();
				mCurrentPhotoPath1 = _photoFile.getAbsolutePath();
				Log.v("", "mCurrentPhotoPath=" + mCurrentPhotoPath1);
				mImageUri = Uri.fromFile(_photoFile);
				Log.v("", "mImageUri-------" + mImageUri);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoFile));
			} catch (Exception e) {
				e.printStackTrace();
				_photoFile = null;
			}
			this.startActivityForResult(intent, CAMERA_REQUEST1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// ********************************************************************************

	private File setUpPhotoFile() {
		File f = createImageFile();
		return f;
	}

	// *********************************************************************************

	@SuppressLint("SimpleDateFormat")
	private File createImageFile() {
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
		imageFileName = JPEG_FILE_PREFIX + timeStamp;
		@SuppressWarnings("unused")
		String imageName = imageFileName + JPEG_FILE_SUFFIX;
		File albumF = getAlbumDir();
		File imageF = null;
		try {

			imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageF;
	}

	// ***********************************************************************************
	private File getAlbumDir() {
		File storageDir = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());
			if (storageDir != null) {
				if (!storageDir.mkdirs()) {
					if (!storageDir.exists()) {
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
		} else {
			// Log.v(getString(R.string.app_name),
			// "External storage is not mounted READ/WRITE.");
		}
		return storageDir;
	}

	private String getAlbumName() {
		return getString(R.string.app_name);
	}

	// ***********************************************************************************

	public void sendCameraSuccessCode(String mCurrentPhotoPath2, int takePhoto) {
		System.out.println("on camera successsssssss");
		mCurrentPhotoPath = mCurrentPhotoPath2;
		Log.v("", "_photoFile=" + _photoFile);
		if (_photoFile != null) {
			mCurrentPhotoPath = _photoFile.getAbsolutePath();
		}
		File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
		imagesFolder.mkdirs();
		String filePath = imagesFolder.getAbsolutePath();
		Bitmap bitmap = loadImage(mCurrentPhotoPath);
		;
		newPath = filePath + "/" + "uploaded" + ".jpg";
		Log.e("", "newPath1...." + newPath);
		filePAthToServer = newPath;
		saveImage(bitmap, newPath);
		showPhoto(newPath);

		Intent intent;
		intent = new Intent(this, CropImage.class);
		intent.putExtra("image-path", newPath);
		intent.putExtra("scale", true);
		intent.putExtra("circleCrop", false);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, 3);

	}

	public void sendCameraSuccessCode1(String mCurrentPhotoPath2, int takePhoto) {
		System.out.println("on camera successsssssss");
		mCurrentPhotoPath1 = mCurrentPhotoPath2;
		Log.v("", "_photoFile=" + _photoFile);
		if (_photoFile != null) {
			mCurrentPhotoPath1 = _photoFile.getAbsolutePath();
		}
		File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
		imagesFolder.mkdirs();
		String filePath = imagesFolder.getAbsolutePath();
		Bitmap bitmap = loadImage(mCurrentPhotoPath1);
		;
		newPath1 = filePath + "/" + "uploaded" + ".jpg";
		Log.e("", "newPath1...." + newPath1);
		filePAthToServer = newPath1;
		saveImage(bitmap, newPath1);
		showPhoto(newPath1);

		Intent intent;
		intent = new Intent(this, CropImage.class);
		intent.putExtra("image-path", newPath1);
		intent.putExtra("scale", true);
		intent.putExtra("circleCrop", false);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, 4);

	}


	private void saveImage(Bitmap imageBitmap, String getImagePath) {
		if (getImagePath != null && getImagePath.trim().length() > 0) {
			File imageFile = new File(getImagePath);
			imageFile.delete();
			try {
				imageFile.createNewFile();
				imageFile.deleteOnExit();
				FileOutputStream out = new FileOutputStream(imageFile);
				imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ************************************************************************************
	private void showPhoto(String photoPath) {
		System.out.println("show photooooooooooooooooooooooooooooo");
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
			Log.v("", "bitmap=" + bitmap);
			bitmap = createScaledBitmap(bitmap);
			if (bitmap != null) {
				// TODO set image
				// imageButtonProfilePic.setImageBitmap(bitmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// **************************************************************************************
	public Bitmap createScaledBitmap(Bitmap bitmap) {
		final int maxSize = 960;
		int outWidth;
		int outHeight;
		int inWidth = bitmap.getWidth();
		int inHeight = bitmap.getHeight();
		if (inWidth > inHeight) {
			outWidth = maxSize;
			outHeight = (inHeight * maxSize) / inWidth;
		} else {
			outHeight = maxSize;
			outWidth = (inWidth * maxSize) / inHeight;
		}
		Bitmap resizedBitmap = ScalingUtilities.createScaledBitmap(bitmap, outWidth, outHeight, ScalingUtilities.ScalingLogic.CROP);

		return resizedBitmap;
	}

	// ***************************************************************************************
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	private Bitmap loadImage(String path) {
		int orientation;
		try {
			if (path == null) {
				return null;
			}
			Bitmap bitmap = null;
			bitmap = BitmapFactory.decodeFile(path);
			ExifInterface exif = new ExifInterface(path);
			orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
			Log.v("ExifInteface .........", "rotation =" + orientation);
			Log.v("orientation", "" + orientation);
			Matrix m = new Matrix();
			if ((orientation == ExifInterface.ORIENTATION_ROTATE_180)) {
				m.postRotate(180);
				Log.v("in orientation", "" + orientation);
				bitmap = BitmapFactory.decodeFile(path);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != null) {
					return bitmap;
				}
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				m.postRotate(90);
				Log.v("in orientation", "" + orientation);
				bitmap = BitmapFactory.decodeFile(path);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != null) {
					return bitmap;
				}
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				m.postRotate(270);
				Log.v("in orientation", "" + orientation);
				bitmap = BitmapFactory.decodeFile(path);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != null) {
					return bitmap;
				}
			}
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	public Bitmap getBitmapFromCameraData(Intent data, Context context) {
		Uri selectedImage = data.getData();
		// Matrix mat = new Matrix();
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		filePAthToServer = picturePath;
		cursor.close();
		Bitmap bmpPic = BitmapFactory.decodeFile(picturePath);

		int rotate = 0;
		int orientation = 0;
		try {
			getContentResolver().notifyChange(selectedImage, null);
			File imageFile = new File(picturePath);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
			orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_270:
				rotate = 270;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				rotate = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				rotate = 90;
				break;
			}
			Log.v("kguy", "Exif orientation: " + orientation);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/****** Image rotation ****/
		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);
		Bitmap cropped = Bitmap.createBitmap(bmpPic, 0, 0, bmpPic.getWidth(), bmpPic.getHeight(), matrix, true);

		return cropped;
	}

	@SuppressWarnings("deprecation")
	public String getRealPathFromURI(Uri contentUri) {

		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);

	}
}
