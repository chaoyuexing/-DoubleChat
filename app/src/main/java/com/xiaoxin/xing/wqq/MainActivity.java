package com.xiaoxin.xing.wqq;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.chinaztt.entity.ItemBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.xiaoxin.xing.wqq.entity.TabEntity;
import com.xiaoxin.xing.wqq.ui.widget.CustomRelativeLayout;
import com.xiaoxin.xing.wqq.ui.widget.DragLayout;
import com.xiaoxin.xing.wqq.util.ItemDataUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_CONTENT = "TAG_CONTENT";
    private DragLayout dl;
    private ListView lv;
    private ImageView ivIcon, ivBottom;
    private QuickAdapter<ItemBean> quickAdapter;
    private CustomRelativeLayout mCll;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    Random mRandom = new Random();
    // show MsgTipView
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SparseArray<Boolean> mInitSetMap = new SparseArray<>();
    private int mTabCount;
    private LinearLayout mTabsContainer;
    private int mCurrentTab;
    private int mLastTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //setStatusBar();
        initDragLayout();
        initView();
        initTab();
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
                //Toast.makeText(MainActivity.this,"sss",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabReselect(int position) {
                tabLayout.showMsg(position, mRandom.nextInt(100) + 1);
                Toast.makeText(MainActivity.this,"s3232ss",Toast.LENGTH_SHORT).show();
            }
        });
    }




    /**
     * 切换
     */
    private void SwitchTo(int position) {

        switch (position) {
            //首页
            case 0:

                Toast.makeText(MainActivity.this,"11",Toast.LENGTH_SHORT).show();
                break;
            //消息
            case 1:

                Toast.makeText(MainActivity.this,"22",Toast.LENGTH_SHORT).show();
                break;
            //联系人
            case 2:

                Toast.makeText(MainActivity.this,"33",Toast.LENGTH_SHORT).show();
                break;
            //更多
            case 3:

                Toast.makeText(MainActivity.this,"44",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void initDragLayout() {
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragLayout.DragListener() {
            //界面打开的时候
            @Override
            public void onOpen() {

            }
            //界面关闭的时候
            @Override
            public void onClose() {
            }

            //界面滑动的时候
            @Override
            public void onDrag(float percent) {
                ViewHelper.setAlpha(ivIcon, 1 - percent);
            }
        });
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
        lv.setAdapter(quickAdapter=new QuickAdapter<ItemBean>(this,R.layout.item_left_layout, ItemDataUtils.getItemBeans()) {
            @Override
            protected void convert(BaseAdapterHelper helper, ItemBean item) {
                helper.setImageResource(R.id.item_img,item.getImg())
                        .setText(R.id.item_tv,item.getTitle());
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(MainActivity.this,"Click Item "+position,Toast.LENGTH_SHORT).show();
            }
        });
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dl.open();
            }
        });
    }

}
