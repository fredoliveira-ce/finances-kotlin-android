package com.fredoliveira.financeskotlin.model

import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum
import java.math.BigDecimal

/**
 * Created by fred.oliveira on 21/05/2018.
 */
class Summary(private val transactions: List<Transaction>) {
    val reveneu get() = calculateByFinancialOperation(TransactionTypeEnum.REVENUE)

    val expenditure get() = calculateByFinancialOperation(TransactionTypeEnum.EXPENDITURE)

    val amount get() = reveneu.subtract(expenditure)

    fun calculateByFinancialOperation(transactionType: TransactionTypeEnum) : BigDecimal {
        var amount = transactions
                .filter { it.type == transactionType }
                .sumByDouble { it.value.toDouble() }

        return BigDecimal(amount)
    }

}