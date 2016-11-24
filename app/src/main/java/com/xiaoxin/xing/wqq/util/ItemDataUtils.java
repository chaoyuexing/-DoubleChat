package com.xiaoxin.xing.wqq.util;
import com.xiaoxin.xing.wqq.R;
import com.xiaoxin.xing.wqq.entity.ItemBean;

import java.util.ArrayList;
import java.util.List;

public final class ItemDataUtils {

    private ItemDataUtils() {
    }

    public static List<ItemBean> getItemBeans(){
        List<ItemBean> itemBeans=new ArrayList<>();
        itemBeans.add(new ItemBean(R.drawable.sidebar_purse,"QQ钱包",true));
        itemBeans.add(new ItemBean(R.drawable.sidebar_decoration,"个性装扮",true));
        itemBeans.add(new ItemBean(R.drawable.sidebar_favorit,"我的收藏",true));
        itemBeans.add(new ItemBean(R.drawable.sidebar_album,"我的相册",false));
        itemBeans.add(new ItemBean(R.drawable.sidebar_file,"我的文件",false));
        return  itemBeans;
    }
    
}
