package com.xiaoxin.xing.wqq.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.xiaoxin.xing.wqq.base.BasePager;

/**
 * @author xiaoxin
 * @date 2016/11/9
 * @describe ：消息
 * 修改内容
 */
public class MessagePager extends BasePager{

    public MessagePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        TextView view = new TextView(mActivity);
        view.setTextSize(22);
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        view.setText("消息");

        flContainer.addView(view);
    }
}
