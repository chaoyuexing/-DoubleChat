package com.xiaoxin.xing.wqq.MVP.home.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxin.xing.wqq.MVP.home.model.LoginModel;
import com.xiaoxin.xing.wqq.MVP.home.presenter.LoginPresenter;
import com.xiaoxin.xing.wqq.R;
import com.xiaoxin.xing.wqq.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：登录
 * 修改内容
 */

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener {

    @Bind(R.id.et_username)
    EditText mEtName;
    @Bind(R.id.et_password)
    EditText mEtPwd;
    @Bind(R.id.bt_login)
    Button mBt;

    private ProgressDialog progressDialog;
    private LoginPresenter presenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mBt.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        presenter = new LoginPresenter(mContext,new LoginModel());
        presenter.attachView(this);//这里与View建立连接
    }

    @Override
    public String getUsername() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void showLoading(String msg) {
        progressDialog.setMessage(msg);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(String result) {
        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_login)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                presenter.login();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();//这里与View断开连接

    }
}
