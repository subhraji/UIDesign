package e.subhrajit.uidesign;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();

        /* If you has other classes that need context object to initialize when application is created,
         you can use the appContext here to process. */
    }

    public static Context getAppContext() {
        return context;
    }

}