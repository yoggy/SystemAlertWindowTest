SystemAlertWindowTest
====

AndroidManifest.xml

      <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
          .
          .
          .
      <service android:name=".AlertWindowService" />
          .
          .
          .

AlertWindowService.java

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

        return super.onStartCommand(intent, flags, startId);
    }

MainActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = (Button)findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, AlertWindowService.class));
            }
        });

        buttonClose = (Button)findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, AlertWindowService.class));
            }
        });
    }


Copyright and license
----
Copyright (c) 2016 yoggy

Released under the [MIT license](LICENSE.txt)