package com.xiaoxin.xing.wqq.MVP.home.presenter;

import android.content.Context;
import android.content.Intent;

import com.xiaoxin.xing.wqq.MVP.home.base.BasePresenter;
import com.xiaoxin.xing.wqq.MVP.home.model.Callback;
import com.xiaoxin.xing.wqq.MVP.home.model.ILoginModel;
import com.xiaoxin.xing.wqq.MVP.home.view.ILoginView;
import com.xiaoxin.xing.wqq.MainActivity;

import static com.xiaoxin.xing.wqq.app.DoubleChatApplication.context;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：
 * 修改内容
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    private ILoginModel loginModel;
    private Context content ;


    public LoginPresenter(Context context,ILoginModel loginModel) {
        this.loginModel = loginModel;
        this.content = context;
    }

    @Override
    public void login() {
        checkViewAttached();
        getMvpView().showLoading("登录中");
        loginModel.login(getMvpView().getUsername(), getMvpView().getPassword(), new Callback() {
            @Override
            public void onSuccess() {
                if (isViewAttached()) {
                    getMvpView().hideLoading();
                    getMvpView().showResult("登录成功");
                    Intent intent = new Intent(content,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    content.startActivity(new Intent(context, MainActivity.class));
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (isViewAttached()) {
                    getMvpView().hideLoading();
                    getMvpView().showResult(errorMsg);
                }
            }
        });
    }
}
