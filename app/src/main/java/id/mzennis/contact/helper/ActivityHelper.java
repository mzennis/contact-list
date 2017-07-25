package id.mzennis.contact.helper;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by mzennis on 11/9/16.
 */
public class ActivityHelper {

	protected ActivityHelper() {
		// Empty
	}

	public static View contentView(@NonNull final Activity activity) {
		return activity.findViewById(android.R.id.content);
	}
}