package id.mzennis.contact;

import android.app.Application;
import android.content.Context;

/**
 * Created by mzennis on 7/25/17.
 */

public class MainApplication extends Application {

    public static Context CONTEXT = null;

    @Override
    public void onCreate() {
        super.onCreate();

        CONTEXT = getApplicationContext();
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }
}
