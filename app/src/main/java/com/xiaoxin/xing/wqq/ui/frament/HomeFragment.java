package com.xiaoxin.xing.wqq.ui.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxin.xing.wqq.R;

import butterknife.ButterKnife;

/**
 * @author xiaoxin
 * @date 2016/11/10
 * @describe ：
 * 修改内容
 */
public class HomeFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
