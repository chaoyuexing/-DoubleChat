package com.xiaoxin.xing.wqq.MVP.home.model;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：
 * 修改内容
 */

public interface Callback {

    /**
     * 成功
     */
    void onSuccess();

    /**
     * 失败
     *
     * @param errorMsg 失败信息
     */
    void onFailure(String errorMsg);
}
