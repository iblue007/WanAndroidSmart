package com.example.wanandroidsmart.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.baselib.base.IBasePresenter
import com.example.baselib.util.PrefUtils
import com.example.wanandroidsmart.base.AppBaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

class SplashActivity : AppBaseActivity<IBasePresenter<*>>(), EasyPermissions.PermissionCallbacks {

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }


}