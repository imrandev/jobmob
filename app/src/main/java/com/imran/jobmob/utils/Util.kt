package com.imran.jobmob.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

object ViewType {
    const val LOADER_VIEW = 0
    const val NORMAL_VIEW = 1
}

object DateUtil {

    @Throws(ParseException::class)
    fun getFormattedDate(actualDate: String) : String{
        val formatted = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(actualDate)

        var formattedDate = formatted.format(date!!)

        val formattedDateArray = formattedDate.split(" ")

        formattedDate = String.format("%s %s, %s",
            getDayOfMonthSuffix(formattedDateArray[0].toInt()), formattedDateArray[1], formattedDateArray[2])
        return formattedDate
    }

    private fun getDayOfMonthSuffix(n: Int): String {
        return if (n in 11..13) {
            String.format("%sth", n)
        } else when (n % 10) {
            1 -> String.format("%sst", n)
            2 -> String.format("%snd", n)
            3 -> String.format("%srd", n)
            else -> String.format("%sth", n)
        }
    }
}