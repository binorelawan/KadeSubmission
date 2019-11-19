package relawan.kade2.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


// convert time schedule to local time(indonesia)
object DateTime{

    private fun formatDate(date: String): String {
        var result = ""
        val old = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        old.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val oldDate = old.parse(date)
            val newFormat = SimpleDateFormat("EEE, dd MMM yyyy;HH:mm", Locale.getDefault())
            newFormat.timeZone = TimeZone.getTimeZone("GMT+7")

            result = newFormat.format(oldDate!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }


    fun getDate(date: String?): String {
        return formatDate(date.toString())
    }

}