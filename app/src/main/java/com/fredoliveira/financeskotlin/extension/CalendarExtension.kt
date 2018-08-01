package com.fredoliveira.financeskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fred.oliveira on 21/05/2018.
 */

fun Calendar.formatDate(): String {
    return SimpleDateFormat("MM/dd/yyyy").format(this.time)
}
