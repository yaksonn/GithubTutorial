package com.yakson.vngrs.githubtutorial.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

/**
 * General Helper class that holds sub helper class and functions
 */
class AppHelper constructor(private val context: Context){

    /**
     * Converts string res to String
     */
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getStringWithFormat(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    /**
     * Converts array resource to String array
     */
    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    /**
     * Converts drawable resource to Drawable Object
     */
    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    /**
     * Converts color res to color int
     */
    @ColorInt
    fun getColor(@ColorRes restId: Int): Int {
        return ContextCompat.getColor(context, restId)
    }

    /**
     * Return Context
     */
    fun getContext() : Context {
        return context
    }

}