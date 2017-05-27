/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.utils;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lotus on 2017/5/27.
 */

public class SimpleAdapterUtil {
    public static SimpleAdapter create(Context context, List<Object[]> item, int resource, int[] to) {
        //定义适配器内容数据
        List<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
        //获取要显示内容数量
        int itemLength = item.get(0).length;
        //获取要显示内容内部长度
        int itemCount = item.size();
        //定义适配器from参数
        String[] adapterFroms = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            adapterFroms[i] = "Item" + String.valueOf(i);
        }
        //准备适配器内容
        for (int i = 0; i < itemLength; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 0; j < itemCount; j++) {
                map.put(adapterFroms[j], item.get(j)[i]);
            }
            data.add(map);
        }
        //绑定适配器并返回
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, data, resource, adapterFroms, to);
        return simpleAdapter;
    }
}

