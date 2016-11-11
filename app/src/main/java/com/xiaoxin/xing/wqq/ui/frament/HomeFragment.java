package com.xiaoxin.xing.wqq.ui.frament;

import com.xiaoxin.xing.wqq.R;

import butterknife.ButterKnife;

/**
 * @author xiaoxin
 * @date 2016/11/10
 * @describe ：
 * 修改内容
 */
public class HomeFragment extends BaseFragment{

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
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
