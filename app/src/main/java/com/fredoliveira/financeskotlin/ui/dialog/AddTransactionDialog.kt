package com.fredoliveira.financeskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum

/**
 * Created by fred.oliveira on 23/05/2018.
 */
class AddTransactionDialog(viewGroup: ViewGroup,
                           context: Context) : TransactionDialog(context, viewGroup) {
    override val titleButtonPositive: String
        get() = "Add"

    override fun getTitleByTransactionType(type: TransactionTypeEnum): Int {
        return when (type) {
            TransactionTypeEnum.REVENUE -> R.string.add_revenue
            TransactionTypeEnum.EXPENDITURE -> R.string.add_expense
        }
    }
}