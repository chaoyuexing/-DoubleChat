package com.xiaoxin.xing.wqq.ui.frament;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.xiaoxin.xing.wqq.R;
import com.xiaoxin.xing.wqq.base.BasePager;
import com.xiaoxin.xing.wqq.base.impl.ContactPager;
import com.xiaoxin.xing.wqq.base.impl.DynamicPager;
import com.xiaoxin.xing.wqq.base.impl.KnowPager;
import com.xiaoxin.xing.wqq.base.impl.MessagePager;
import com.xiaoxin.xing.wqq.ui.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentFragment extends BaseFragment {

    @Bind(R.id.vp_content)
    NoScrollViewPager mViewPager;
    @Bind(R.id.rg_group)
    RadioGroup mRgGroup;
    private ArrayList<BasePager> mList;//4个标签页的集合
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_content, container, false);
        }
        ButterKnife.bind(this, mView);
        return mView;
    }


    @Override
    public View initViews() {

        return null;
    }

    @Override
    public void initData() {
        mList = new ArrayList<BasePager>();
        mList.add(new MessagePager(mActivity));
        mList.add(new ContactPager(mActivity));
        mList.add(new DynamicPager(mActivity));
        mList.add(new KnowPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());

        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        //消息
                        //mViewPager.setCurrentItem(0);
                        mViewPager.setCurrentItem(0, false);//去掉页面切换的动画
                        break;
                    case R.id.rb_news:
                        //联系人
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        //动态
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        //kwon
                        mViewPager.setCurrentItem(3, false);
                        break;
                      default:
                        break;
                }
            }
        });

        //监听ViewPager页面切换事件, 初始化当前页面数据
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mList.get(position);
                pager.initData();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //手动初始化第一个页面的数据
        mList.get(0).initData();
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获取当前页面对象
            BasePager pager = mList.get(position);

            pager.initData();
            container.addView(pager.mRootView);

            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
