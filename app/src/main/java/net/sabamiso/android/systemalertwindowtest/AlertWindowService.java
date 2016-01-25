package net.sabamiso.android.systemalertwindowtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class AlertWindowService extends Service {
    public static final String TAG = "AlertWindowService";

    WindowManager windowManager;
    LinearLayout layout;

    // binder
    public class ReceiverServiceBinder extends Binder {
        AlertWindowService getService() {
            return AlertWindowService.this;
        }
    }

    private final IBinder binder = new ReceiverServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (layout != null) return super.onStartCommand(intent, flags, startId);

        layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.argb(64, 255, 255, 0));

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                400,
                640,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(layout, params);

        Log.i(TAG, "onStartCommand(), intent=" + intent + ", startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        windowManager.removeView(layout);
    }
}
