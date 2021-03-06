package com.example.wanandroidsmart.ui.main.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.example.baselib.util.ToastUtils
import com.example.wanandroidsmart.R
import com.example.wanandroidsmart.adapter.ArticleAdapter
import com.example.wanandroidsmart.base.AppLazyFragment
import com.zs.wanandroid.entity.ArticleEntity
import com.zs.wanandroid.entity.BannerEntity
import com.zs.wanandroid.proxy.ImageLoad
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : AppLazyFragment<HomeContract.Present<HomeContract.View>>(), HomeContract.View,
    BGABanner.Adapter<ImageView, String>, BGABanner.Delegate<ImageView, String> {


    private var bannerList = mutableListOf<BannerEntity>()
    private var articleList = mutableListOf<ArticleEntity.DatasBean>()
    private var articleAdapter: ArticleAdapter? = null
    override fun lazyInt() {
        initView()
        //   loadingTip.loading()
        loadData()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rlSearch.elevation = 10f
            llRadius.elevation = 20f
            rvHomeList.isNestedScrollingEnabled = false
        }
        articleAdapter = ArticleAdapter(articleList)
        articleAdapter?.setNewData(articleList)
        rvHomeList.adapter = articleAdapter
        rvHomeList.layoutManager = LinearLayoutManager(context)

    }

    private fun loadData() {
        if (bannerList.size == 0) {
            presenter?.loadBanner()
        }
        articleList.clear()
        articleAdapter?.setNewData(articleList)
        presenter?.loadData(1)
    }

    override fun createPresenter(): HomeContract.Present<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun showList(list: MutableList<ArticleEntity.DatasBean>) {
        if (list.isNotEmpty()) {
            articleList.addAll(list)
            articleAdapter?.setNewData(articleList)
        } else {
            if (articleList.size == 0) {

            }
        }
    }

    override fun showBanner(bannerList: MutableList<BannerEntity>) {
        this.bannerList.addAll(bannerList)
        initBanner()
    }

    private fun initBanner() {
        banner.setAutoPlayAble(true)
        val views: MutableList<View> = ArrayList()
        bannerList.forEach {
            views.add(
                LayoutInflater.from(context).inflate(R.layout.banner_layout, null)
                    .findViewById(R.id.ivBanner)
            )
        }
        banner.setAdapter(this)
        banner.setDelegate(this)
        banner.setData(views)
    }

    override fun collectSuccess() {

    }

    override fun unCollectSuccess() {

    }

    override fun onError(error: String) {

    }

    override fun fillBannerItem(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        itemView?.let {
            it.scaleType = ImageView.ScaleType.CENTER_CROP
            val bannerEntity = bannerList[position]
            // Glide.with(this).load(bannerEntity.imagePath).into(it)
            ImageLoad.load(it, bannerEntity.imagePath)
        }
    }

    override fun onBannerItemClick(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        ToastUtils.show("banner click")
    }
}