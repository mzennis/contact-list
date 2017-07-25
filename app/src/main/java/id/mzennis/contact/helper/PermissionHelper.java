package id.mzennis.contact.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public abstract class PermissionHelper {

	public static boolean checkPermission(Context context, String permission){
		int result = ContextCompat.checkSelfPermission(context, permission);
		return result == PackageManager.PERMISSION_GRANTED;
	}

	public static void requestPermission(Context context, String permission, int permissionCode){
		if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)){
			Toast.makeText(context, "Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
		} else {
			ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, permissionCode);
		}
	}

	public static boolean verifyPermissions(int[] grantResults) {
		if(grantResults.length < 1){
			return false;
		}

		for (int result : grantResults) {
			if (result != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasPermissionInManifest(Context context, String permissionName) {
		final String packageName = context.getPackageName();
		try {
			final PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
			final String[] declaredPermisisons = packageInfo.requestedPermissions;
			if (declaredPermisisons != null && declaredPermisisons.length > 0) {
				for (String p : declaredPermisisons) {
					if (p.equals(permissionName)) {
						return true;
					}
				}
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean requestAllPermission(Context context, String[] permission){
		boolean requestPermission;
		if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission[0])
				|| ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission[1])){
			requestPermission =  true;
		} else {
			requestPermission =  true;
		}

		return requestPermission;
	}
}
