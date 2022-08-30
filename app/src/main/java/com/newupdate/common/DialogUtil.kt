package com.newupdate.common

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.newupdate.R

class DialogUtil {
    private val TAG = "DialogUtil"

    private var progressDialog: ProgressDialog? = null

    private fun DialogUtil() {
        // This utility class is not publicly instantiable
    }

    fun showLoadingDialog(activity: Activity?, callingPlace: String): ProgressDialog? {
        Log.d(TAG, "showLoadingDialog: $callingPlace")
        progressDialog = ProgressDialog(activity)
        progressDialog!!.show()
        if (progressDialog!!.window != null) {
            progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog!!.setContentView(R.layout.process_dialog)
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(true)
        return progressDialog
    }

    fun hideDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.hide()
        }
    }

}