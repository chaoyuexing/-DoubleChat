package com.xiaoxin.xing.wqq.MVP.home.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaoxin.xing.wqq.R;
import com.xiaoxin.xing.wqq.adapter.FeedAdapter;
import com.xiaoxin.xing.wqq.app.BaseFragment;
import com.xiaoxin.xing.wqq.Widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author xiaoxin
 * @date 2016/11/10
 * @describe ：更多
 * 修改内容
 */
public class ZbFragment extends BaseFragment {
    @Bind(R.id.feed_list)
    RecyclerView mFeedList;
    @Bind(R.id.iv_avatar)
    CircleImageView mSuspensionIv;
    @Bind(R.id.tv_nickname)
    TextView mSuspensionTv;
    @Bind(R.id.top_divider)
    View mTopDivider;
    @Bind(R.id.suspension_bar)
    RelativeLayout mSuspensionBar;

    private int mCurrentPosition = 0;
    private int mSuspensionHeight;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zb;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        final FeedAdapter adapter = new FeedAdapter();

        mFeedList.setLayoutManager(linearLayoutManager);
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);
        mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view == null) return;
                if (view.getTop() <= mSuspensionHeight) {
                    mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                } else {
                    mSuspensionBar.setY(0);
                }

                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mSuspensionBar.setY(0);

                    updateSuspensionBar();
                }
            }
        });
    }

    private void updateSuspensionBar() {
        Picasso.with(getActivity())
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fit()
                .into(mSuspensionIv);

        mSuspensionTv.setText("Taeyeon " + mCurrentPosition);
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
