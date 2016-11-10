package com.xiaoxin.xing.wqq.ui.frament;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xiaoxin.xing.wqq.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentFragment extends BaseFragment {

    @Bind(R.id.vp_content)
    ViewPager mViewPager;
    @Bind(R.id.bnv_bottom_activity)
    BottomNavigationView mBnvBottomActivity;
    private View mView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_content;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
