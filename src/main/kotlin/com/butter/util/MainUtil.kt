package com.butter.util

import java.text.SimpleDateFormat
import java.util.*

object MainUtil {
    fun getCurrentTime(): Any {
        val date = SimpleDateFormat("hh:mm:ss")
        return date.format(Date())
    }
}
