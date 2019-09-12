package com.yakson.vngrs.githubtutorial.utils

import android.content.Context
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.yakson.vngrs.githubtutorial.R

/**
 * Loading placeholder with Glide
 */
class PlaceholderProgress constructor(context: Context) : CircularProgressDrawable(context) {

    init {
        initialize(context)
    }

    private fun initialize(context: Context) {
        setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        this.strokeWidth = 5F
        this.centerRadius = 30f
        this.start()
    }

}