package com.xiaoxin.xing.wqq.MVP.home.model;

import android.os.Handler;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：
 * 修改内容
 */

public class LoginModel implements ILoginModel{

    public final String TAG =  LoginModel.class.getSimpleName();
    Handler handler = new Handler();


    @Override
    public void login(final String name, final String pwd, final Callback callback) {
        handler.postDelayed(new Runnable() {//延时200ms回调，模拟网络请求
            @Override
            public void run() {
                if ("xiaoxin".equals(name) && "123456".equals(pwd)) {
                    callback.onSuccess();
                } else {
                    callback.onFailure("用户名或密码错误");
                }
            }
        }, 2000);
    }
}
