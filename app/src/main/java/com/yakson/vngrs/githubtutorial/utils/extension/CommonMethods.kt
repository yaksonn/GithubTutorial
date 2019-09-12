package com.yakson.vngrs.githubtutorial.utils.extension

import java.text.SimpleDateFormat
import java.util.*


fun getCurrentDate(isOnlyDate: Boolean): String {

    val sdf: SimpleDateFormat = if (isOnlyDate) {
        SimpleDateFormat("yyyy-MM-dd", Locale("tr"))
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
    }

    return sdf.format(Date())
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))
    return sdf.format(Date())
}

fun compareDates(today: String, eventDate: String?): Boolean {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))

    val date1 = format.parse(today)
    val date2 = format.parse(eventDate)

    if (date1 <= date2) {
        return true
    }

    return false
}

fun convertStringToDate(date: String?): Date {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))

    val mDate = format.parse(date)

    return mDate
}

fun getLastOneWeek(isOnlyDate: Boolean): String {
    val cal = Calendar.getInstance()

    val sdf: SimpleDateFormat = if (isOnlyDate) {
        SimpleDateFormat("yyyy-MM-dd", Locale("tr"))
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
    }

    cal.add(Calendar.DAY_OF_MONTH, -7)
    return sdf.format(cal.time)
}

fun getLastCoupleMonth(position: Int, isOnlyDate: Boolean): String {
    val cal = Calendar.getInstance()

    val sdf: SimpleDateFormat = if (isOnlyDate) {
        SimpleDateFormat("yyyy-MM-dd", Locale("tr"))
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
    }

    cal.add(Calendar.MONTH, -position)
    return sdf.format(cal.time)
}

fun getLastYear(isOnlyDate: Boolean): String {
    val cal = Calendar.getInstance()

    val sdf: SimpleDateFormat = if (isOnlyDate) {
        SimpleDateFormat("yyyy-MM-dd", Locale("tr"))
    } else {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
    }

    cal.add(Calendar.YEAR, -1)
    return sdf.format(cal.time)
}