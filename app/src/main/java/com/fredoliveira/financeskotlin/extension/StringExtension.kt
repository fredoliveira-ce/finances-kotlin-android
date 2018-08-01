package com.fredoliveira.financeskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fred.oliveira on 23/05/2018.
 */
fun String.convertForCalendar(): Calendar {
    val convertedDate: Date = SimpleDateFormat("MM/dd/yyyy").parse(this)
    val calendarDate = Calendar.getInstance()
    calendarDate.time = convertedDate

    return calendarDate
}