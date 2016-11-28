package com.xiaoxin.xing.wqq.MVP.home.base;

/**
 * @author xiaoxin
 * @date 2016/11/26
 * @describe ：View层基类
 * 修改内容
 */

public interface MvpView {

    /**
     * 显示加loading
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 取消loading显示
     */
    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param errorMsg
     */
    void showError(String errorMsg);
}
