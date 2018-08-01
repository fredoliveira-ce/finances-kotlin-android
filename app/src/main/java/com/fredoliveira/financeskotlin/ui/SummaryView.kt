package com.fredoliveira.financeskotlin.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.extension.formatDecimal
import com.fredoliveira.financeskotlin.model.Summary
import com.fredoliveira.financeskotlin.model.Transaction
import kotlinx.android.synthetic.main.summary_card.view.*
import java.math.BigDecimal

/**
 * Created by fred.oliveira on 21/05/2018.
 */
class SummaryView(context: Context,
                  private val view: View,
                  transactions: List<Transaction>) {

    private val summary: Summary = Summary(transactions)
    private val colorReveneu = ContextCompat.getColor(context, R.color.revenue)
    private val colorExpenditure = ContextCompat.getColor(context, R.color.expense)

    fun update() {
        addRevenue()
        addExpenditure()
        addAmount()
    }

    private fun addRevenue() {
        var revenueAmount = summary.reveneu

        view.summary_card_revenue.setTextColor(colorReveneu)
        view.summary_card_revenue.text = revenueAmount.formatDecimal()
    }

    private fun addExpenditure() {
        var expenditureAmount = summary.expenditure

        with(view.summary_card_expenditure) {
            setTextColor(colorExpenditure)
            text = expenditureAmount.formatDecimal()
        }
    }

    private fun addAmount() {
        var amount = summary.amount

        val color = selectColorByFinancialOperation(amount)

        view.summary_card_total.setTextColor(color)

        view.summary_card_total.text = amount.formatDecimal()
    }

    private fun selectColorByFinancialOperation(amount: BigDecimal): Int {
        return if (amount >= BigDecimal.ZERO) {
            colorReveneu
        } else {
            colorExpenditure
        }
    }
}