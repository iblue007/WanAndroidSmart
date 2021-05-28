package com.example.wanandroidsmart

import com.example.baselib.BaseApplication
import com.example.baselib.util.ColorUtils
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter

class WanAndroidApplication : BaseApplication() {

    companion object {
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                MaterialHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
                val footer = BallPulseFooter(context)
                footer.setAnimatingColor(ColorUtils.parseColor(R.color.theme))
                footer.setBackgroundColor(ColorUtils.parseColor(R.color.white))
                footer
            }
        }
    }
}