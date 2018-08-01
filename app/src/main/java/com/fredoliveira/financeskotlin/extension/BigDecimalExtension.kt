package com.fredoliveira.financeskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by fred.oliveira on 21/05/2018.
 */

fun BigDecimal.formatDecimal(): String {
    return DecimalFormat("#.00").format(this)
}