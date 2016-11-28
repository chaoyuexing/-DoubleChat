package com.xiaoxin.xing.wqq.MVP.home.model;

/**
 * @author xiaoxin
 * @date 2016/11/25
 * @describe ：登录接口
 * 修改内容
 */

public interface ILoginModel {

    /**
     * 登录
     * @param name 用户名
     * @param pwd 密码
     * @param callback 回调
     */
    void login(String name,String pwd,Callback callback);

}
