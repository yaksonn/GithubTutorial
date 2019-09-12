package com.yakson.vngrs.githubtutorial.utils.customview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.utils.extension.setVisible
import kotlinx.android.synthetic.main.layout_custom_toolbar.view.*

class CustomToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    Toolbar(context, attrs, defStyleAttr) {

    private var showBackIcon: Boolean = false
    private var centerText: String? = null
    private var centerTextSize: Int = 0

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.layout_custom_toolbar, this, true)

        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.CustomToolbar, 0, 0)
            showBackIcon = a.getBoolean(R.styleable.CustomToolbar_showBackIcon, false)
            centerText = a.getString(R.styleable.CustomToolbar_showCenterText)

            setToolbar()
            setCenterText(centerText)
            setCenterTextSize(centerTextSize)
            setBackBtn()


            a.recycle()
        }
    }

    private fun setToolbar() {
        if (showBackIcon){
            ivBack.setVisible()
        }

    }

    /**
     * set center toolbar text
     */
    fun setCenterText(centerText: String?) {
        if (centerText != null) {
            tvToolbarText.setVisible()
            tvToolbarText.text = centerText
        }
    }

    /**
     * Set text size for center toolbar text
     */
    private fun setCenterTextSize(centerTextSize: Int) {
        if (centerTextSize > 0) {
            tvToolbarText.setTextSize(TypedValue.COMPLEX_UNIT_SP, centerTextSize.toFloat())
        }
    }

    /**
     * Set Back Button
     */
    private fun setBackBtn() {
        ivBack.setOnClickListener {
            if (context is Activity) {
                (context as Activity).onBackPressed()
            }
        }
    }

}