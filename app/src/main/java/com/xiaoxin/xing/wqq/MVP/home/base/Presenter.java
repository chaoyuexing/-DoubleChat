package com.xiaoxin.xing.wqq.MVP.home.base;

/**
 * @author xiaoxin
 * @date 2016/11/26
 * @describe ：
 * 修改内容
 */

public interface Presenter<V extends MvpView> {

    /**
     * Presenter与View建立连接
     *
     * @param mvpView 与此Presenter相对应的View
     */
    void attachView(V mvpView);

    /**
     * Presenter与View连接断开
     */
    void detachView();

}
