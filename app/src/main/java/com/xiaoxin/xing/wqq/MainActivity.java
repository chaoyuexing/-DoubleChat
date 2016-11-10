package com.xiaoxin.xing.wqq;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.xiaoxin.xing.wqq.entity.ItemBean;
import com.xiaoxin.xing.wqq.ui.activity.BaseActivity;
import com.xiaoxin.xing.wqq.ui.frament.ContentFragment;
import com.xiaoxin.xing.wqq.ui.widget.CustomRelativeLayout;
import com.xiaoxin.xing.wqq.ui.widget.DragLayout;
import com.xiaoxin.xing.wqq.util.ItemDataUtils;

public class MainActivity extends BaseActivity {
    private static final String TAG_CONTENT = "TAG_CONTENT";
    private DragLayout dl;
    private ListView lv;
    private ImageView ivIcon, ivBottom;
    private QuickAdapter<ItemBean> quickAdapter;
    private CustomRelativeLayout mCll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        initDragLayout();
        initView();
        initFragment();
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
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT).
                commit();

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
