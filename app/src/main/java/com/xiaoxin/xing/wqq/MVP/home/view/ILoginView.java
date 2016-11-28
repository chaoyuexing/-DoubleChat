package com.xiaoxin.xing.wqq.MVP.home.view;

import com.xiaoxin.xing.wqq.MVP.home.base.MvpView;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：
 * 修改内容
 */

public interface ILoginView extends MvpView {
    /**
     * 从UI中获取用户输入的用户名
     *
     * @return
     */
    String getUsername();

    /**
     * 从UI中获取用户输入的密码
     *
     * @return
     */
    String getPassword();

    /**
     * 显示结果
     *
     * @param result
     */
    void showResult(String result);

}
