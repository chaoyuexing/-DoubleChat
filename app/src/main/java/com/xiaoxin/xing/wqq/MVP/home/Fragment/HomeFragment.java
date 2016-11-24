package com.xiaoxin.xing.wqq.MVP.home.Fragment;

import com.xiaoxin.xing.wqq.R;
import com.xiaoxin.xing.wqq.app.BaseFragment;

import butterknife.ButterKnife;

/**
 * @author xiaoxin
 * @date 2016/11/10
 * @describe ：
 * 修改内容
 */
public class HomeFragment extends BaseFragment {

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
