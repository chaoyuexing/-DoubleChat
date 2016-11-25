package com.xiaoxin.xing.wqq;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.xiaoxin.xing.wqq.app.BaseActivity;
import com.xiaoxin.xing.wqq.entity.ItemBean;
import com.xiaoxin.xing.wqq.entity.TabEntity;

import com.xiaoxin.xing.wqq.Widget.MorePopWindow;
import com.xiaoxin.xing.wqq.MVP.home.Fragment.ContactFragment;
import com.xiaoxin.xing.wqq.MVP.home.Fragment.HomeFragment;
import com.xiaoxin.xing.wqq.MVP.home.Fragment.MessageFrament;
import com.xiaoxin.xing.wqq.MVP.home.Fragment.ZbFragment;
import com.xiaoxin.xing.wqq.Widget.CustomRelativeLayout;
import com.xiaoxin.xing.wqq.Widget.DragLayout;
import com.xiaoxin.xing.wqq.util.ItemDataUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG_CONTENT = "TAG_CONTENT";
    @Bind(R.id.tl_title)
    SegmentTabLayout mTopTitle; //头部按钮
    @Bind(R.id.seal_more)
    ImageView mSealMore; //添加
    private DragLayout dl;
    private ListView lv;
    private ImageView ivIcon, ivBottom;
    private QuickAdapter<ItemBean> quickAdapter;
    private CustomRelativeLayout mCll;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTopTitles = {"消息", "电话"};
    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    Random mRandom = new Random();
    private HomeFragment mHomeFragment;
    private MessageFrament mMessageFrament;
    private ContactFragment mContactFragment;
    private ZbFragment mZbFragment;
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //setStatusBar();
        initDragLayout();
        initView();
        initData();
        initTab();
        //initFragment1();
        initFragment(savedInstanceState);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.showMsg(0, 55);
        tabLayout.setMsgMargin(0, -5, 5);
        tabLayout.showMsg(1, 100);
        tabLayout.setMsgMargin(1, -5, 5);
        //设置未读消息红点
        tabLayout.showDot(2);
        MsgView rtv_2_2 = tabLayout.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }
        //设置未读消息背景
        tabLayout.showMsg(3, 5);
        tabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = tabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        mDecorView = getWindow().getDecorView();
        mTopTitle.setTabData(mTopTitles);
    }

    private void initData() {
        mSealMore.setOnClickListener(this);
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {

            mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("mHomeFragment");
            mMessageFrament = (MessageFrament) getSupportFragmentManager().findFragmentByTag("mMessageFrament");
            mContactFragment = (ContactFragment) getSupportFragmentManager().findFragmentByTag("mContactFragment");
            mZbFragment = (ZbFragment) getSupportFragmentManager().findFragmentByTag("mZbFragment");
            currentTabPosition = savedInstanceState.getInt("HOME_CURRENT_TAB_POSITION");
        } else {
            mHomeFragment = new HomeFragment();
            mMessageFrament = new MessageFrament();
            mContactFragment = new ContactFragment();
            mZbFragment = new ZbFragment();
            ;
            transaction.add(R.id.fl, mHomeFragment, "mHomeFragment");
            transaction.add(R.id.fl, mMessageFrament, "mMessageFrament");
            transaction.add(R.id.fl, mContactFragment, "mContactFragment");
            transaction.add(R.id.fl, mZbFragment, "mZbFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
                tabLayout.showMsg(position, mRandom.nextInt(100) + 1);
            }
        });
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(mMessageFrament);
                transaction.hide(mContactFragment);
                transaction.hide(mZbFragment);
                transaction.show(mHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //消息
            case 1:
                transaction.show(mMessageFrament);
                transaction.hide(mContactFragment);
                transaction.hide(mZbFragment);
                transaction.hide(mHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //联系人
            case 2:
                transaction.hide(mMessageFrament);
                transaction.show(mContactFragment);
                transaction.hide(mZbFragment);
                transaction.hide(mHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //更多
            case 3:
                transaction.hide(mMessageFrament);
                transaction.hide(mContactFragment);
                transaction.show(mZbFragment);
                transaction.hide(mHomeFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (tabLayout != null) {
            outState.putInt("HOME_CURRENT_TAB_POSITION", tabLayout.getCurrentTab());
        }
    }


    private void initDragLayout() {
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragLayout.DragListener() {
            //界面打开的时候
            @Override
            public void onOpen() {
                Log.e("ss", dl.getState() + "sssss");
            }

            //界面关闭的时候
            @Override
            public void onClose() {
                Log.e("ss", dl.getState() + "sssss");
            }

            //界面滑动的时候
            @Override
            public void onDrag(float percent) {
                ViewHelper.setAlpha(ivIcon, 1 - percent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (dl.getState()) {
            dl.close();  //关闭
        } else {
            super.onBackPressed();
        }
    }

    //初始化Fragment
    private void initFragment() {
        //getFragmentManager(); 4.0+
        //获取fragment管理器
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();//开始一个事务
//        //使用fragment替换现有布局. 参1:当前布局的id;参2:要替换的fragment
//        transaction
//                .replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT);//参3:打一个标记,方便以后找到该Fragment对象
//        transaction.commit();//提交事务
//        getSupportFragmentManager().
//                beginTransaction().
//                replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT).
//                commit();

        //通过tag找到fragment
        //ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
    }

    private void initView() {
        mCll = (CustomRelativeLayout) findViewById(R.id.cll);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        ivBottom = (ImageView) findViewById(R.id.iv_bottom);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(quickAdapter = new QuickAdapter<ItemBean>(this, R.layout.item_left_layout, ItemDataUtils.getItemBeans()) {
            @Override
            protected void convert(BaseAdapterHelper helper, ItemBean item) {
                helper.setImageResource(R.id.item_img, item.getImg())
                        .setText(R.id.item_tv, item.getTitle());
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(MainActivity.this, "Click Item " + position, Toast.LENGTH_SHORT).show();
            }
        });
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dl.open();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.seal_more:
                MorePopWindow morePopWindow = new MorePopWindow(MainActivity.this);
                morePopWindow.showPopupWindow(mSealMore);
                break;

        }
    }
}
