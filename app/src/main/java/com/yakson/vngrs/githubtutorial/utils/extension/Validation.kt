package com.yakson.vngrs.githubtutorial.utils.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.isPasswordValid(): Boolean {
    return this.isNotEmpty() && this.length >= 6
}

/**
 * Check length is equal or higher
 */
fun String.isLengthOk(length : Int) : Boolean {
    return this.isNotEmpty() && this.length >= length
}

/**
 * 2.05.2019 -> 02.05.2019
 */
fun String.formatDate(): String? {
    val comingDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale("tr", "TR"))
    var date: Date? = null
    try {
        date = comingDateFormat.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        return null
    }
    return comingDateFormat.format(date)

}