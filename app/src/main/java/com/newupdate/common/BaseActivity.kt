package com.newupdate.common

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), MainInterfaces {
    var context: Activity? = null
    val dialogUtil: DialogUtil = DialogUtil()
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.context = this

    }

    override fun showLoading(){
        mProgressDialog = dialogUtil.showLoadingDialog(context, "Base Activity")
        mProgressDialog?.setCancelable(false)
    }

    override fun hideLoading(){
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.cancel()
        }
    }

    override fun checkOffline() {

    }
}