package com.example.wanandroidsmart.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.baselib.base.IBasePresenter
import com.example.wanandroidsmart.R
import com.example.wanandroidsmart.base.AppBaseActivity
import com.example.wanandroidsmart.ui.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppBaseActivity<IBasePresenter<*>>() {

    private var lastIndex: Int = 0;
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {

        initFragment()
        initBottom()
    }

    private fun initBottom() {
        btmNavigation.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> setFragmentPosition(0)
                    R.id.menu_project -> setFragmentPosition(1)
                    R.id.menu_square -> setFragmentPosition(2)
                    R.id.menu_official_account -> setFragmentPosition(3)
                    R.id.menu_mine -> setFragmentPosition(4)
                }
                true
            }
        }
    }

    private fun setFragmentPosition(i: Int) {
        var ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment = fragments[i]
        var lastFragment: Fragment = fragments[lastIndex]
        lastIndex = i;
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.frameLayout,currentFragment)
            ft.setMaxLifecycle(currentFragment,Lifecycle.State.RESUMED)
        }
        ft.show(currentFragment)
        ft.commit()
    }

    private fun initFragment() {
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        setFragmentPosition(0)
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }


}