package com.fredoliveira.financeskotlin.model

import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum
import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by fred.oliveira on 18/05/2018.
 */
class Transaction(val value: BigDecimal,
                  val category: String,
                  val type: TransactionTypeEnum,
                  val date: Calendar = Calendar.getInstance())