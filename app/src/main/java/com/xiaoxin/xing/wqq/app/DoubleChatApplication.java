package com.xiaoxin.xing.wqq.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by xing on 2016/11/8.
 */

public class DoubleChatApplication extends Application{

    public static Context context;
    public static Handler mainHandler;//主线程的handler
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();//在主线程创建的handler，就是主线程的handler
    }
}
