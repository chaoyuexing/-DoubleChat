package com.xiaoxin.xing.wqq.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xiaoxin.xing.wqq.R;

/**
 * @author xiaoxin
 * @date 2016/11/9
 * @describe ：底部标签的基类;
 * 修改内容
 */
public class BasePager {

    public Activity mActivity;
    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContainer;//空的帧布局, 由子类动态填充布局

    public View mRootView;//当前页面的根布局

    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = initViews();
    }

    //初始化布局
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        flContainer = (FrameLayout) view.findViewById(R.id.fl_container);
        return view;
    }

    public void initData() {

    }

}
