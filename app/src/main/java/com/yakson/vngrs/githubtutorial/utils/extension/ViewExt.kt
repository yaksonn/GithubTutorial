package com.yakson.vngrs.githubtutorial.utils.extension

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yakson.vngrs.githubtutorial.utils.PlaceholderProgress
import java.text.SimpleDateFormat
import java.util.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}


fun ImageView.setCircleImage(url: String) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun ImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(PlaceholderProgress(context))
        .apply(RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
        .into(this)
}

fun ImageView.setImageWithoutCache(url: String) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(PlaceholderProgress(context))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        )
        .into(this)
}

/**
 * Format string for currency
 */
fun String.currencyFormat(): String {
    val locale = Locale("tr", "TR")

    val nf = NumberFormat.getCurrencyInstance(locale)
    val decimalFormatSymbols = (nf as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = ""
    nf.decimalFormatSymbols = decimalFormatSymbols

    return nf.format(toDouble())
}

/**
 * Format Double for currency
 */
fun Double.currencyFormat(): String {
    val locale = Locale("tr", "TR")

    val nf = NumberFormat.getCurrencyInstance(locale)
    val decimalFormatSymbols = (nf as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = ""
    nf.decimalFormatSymbols = decimalFormatSymbols

    return nf.format(this)
}

/**
 * Format Digit Data
 *
 * 5000 -> 5.000
 */
fun String.formatDigit(): String {
    val symbols = DecimalFormatSymbols()
    symbols.decimalSeparator = '.'
    symbols.groupingSeparator = '.'

    val dec = DecimalFormat("###,###.###", symbols)
    return dec.format(this.replace(".", "").toDouble())
}

/**
 * Convert millis to date
 * 2131232131 -> 15/12/2323
 */
fun Long.convertMillisToDateString(): String {
    val date = Date()
    date.time = this
    val sendingDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("tr", "TR"))
    return sendingDateFormat.format(date)
}

/**
 * Convert millis to date
 * 2131232131 -> 15/12/2323
 */
fun Long.convertMillisToDateStringWithTime(): String {
    val date = Date()
    date.time = this
    val sendingDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("tr", "TR"))
    return sendingDateFormat.format(date)
}

fun TextView.convertDate(eventDate: String?) {
    var spf = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))
    val newDate = spf.parse(eventDate)
    spf = SimpleDateFormat("dd MMM", Locale("tr"))

    this.text = spf.format(newDate)
}

@SuppressLint("SetTextI18n")
fun TextView.convertFullDate(firstText: String, date: String?) {
    var spf = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale("tr"))
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("dd MMMM yyyy - hh:mm", Locale("tr"))

    if (firstText.isNotEmpty()) {
        this.text = "$firstText  ${spf.format(newDate)}"
    } else {
        this.text = spf.format(newDate)
    }
}

@SuppressLint("SetTextI18n")
fun TextView.convertDate(firstText: String, date: String?) {
    var spf = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale("tr"))
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))

    if (firstText.isNotEmpty()) {
        this.text = "$firstText  ${spf.format(newDate)}"
    } else {
        this.text = spf.format(newDate)
    }
}

fun TextView.convertTime(eventTime: String?) {
    this.text = eventTime?.substring(0, eventTime.lastIndexOf(':'))
}
