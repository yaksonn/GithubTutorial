package com.yakson.vngrs.githubtutorial.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.yakson.vngrs.githubtutorial.R
import kotlinx.android.synthetic.main.layout_custom_general_dialog.view.*
import org.jetbrains.anko.layoutInflater

fun Activity.startActivityModal(intent: Intent) {
    val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_bottom_in, R.anim.slide_none)
    this.startActivity(intent, options.toBundle())
}

fun Activity.startActivityModalWithResult(intent: Intent, requestCode: Int) {
    val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_bottom_in, R.anim.slide_none)
    if (Build.VERSION.SDK_INT >= 21){
        this.startActivityForResult(intent, requestCode, options.toBundle())
    }
    else{
        ActivityCompat.startActivityForResult(this, intent, requestCode, options.toBundle())
    }
}

fun Activity.startActivityFromRight(intent: Intent) {
    val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left)
    this.startActivity(intent, options.toBundle())
}

fun Activity.startActivityFromRightWithResult(intent: Intent, requestCode: Int) {
    val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left)
    if (Build.VERSION.SDK_INT >= 21){
        this.startActivityForResult(intent, requestCode, options.toBundle())
    }
    else{
        ActivityCompat.startActivityForResult(this, intent, requestCode, options.toBundle())
    }
}

fun Activity.slideOutRight() {
    this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
}

fun Activity.slideOutModal() {
    this.overridePendingTransition(R.anim.slide_none, R.anim.slide_bottom_out)
}

@SuppressLint("InflateParams")
fun Context.showGeneralAlertDialog(
    title: String?,
    @DrawableRes icon: Int?,
    message: String?,
    btn1Text : String?, btn1Click : (() -> Unit)?,
    showCloseIcon : Boolean = true
) {

    var dialog: AlertDialog? = null
    val builder = AlertDialog.Builder(this)

    val rootView = this.layoutInflater.inflate(R.layout.layout_custom_general_dialog, null)
    builder.setView(rootView)
    builder.setCancelable(false)


    if (showCloseIcon) rootView.closeImageButton.setVisible() else rootView.closeImageButton.setGone()
    rootView.closeImageButton.setOnClickListener { dialog?.dismiss() }

    // Create and show dialog
    dialog = builder.create()
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()
}



