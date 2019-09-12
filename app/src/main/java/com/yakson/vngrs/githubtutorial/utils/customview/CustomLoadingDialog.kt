package com.yakson.vngrs.githubtutorial.utils.customview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import com.yakson.vngrs.githubtutorial.R
import kotlinx.android.synthetic.main.view_progress.*

/**
 * Custom Loading Dialog
 */
class CustomLoadingDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.view_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        mCustomProgress.indeterminateDrawable?.setColorFilter(
            ContextCompat.getColor(context, android.R.color.white),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

}