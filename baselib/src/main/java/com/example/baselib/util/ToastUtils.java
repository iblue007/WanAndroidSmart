package com.example.baselib.util;

import android.widget.Toast;

import com.example.baselib.BaseApplication;

/**
 * des toast工具类
 * @author zs
 * @date 2020-03-10
 */
public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(BaseApplication.Companion.getContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
