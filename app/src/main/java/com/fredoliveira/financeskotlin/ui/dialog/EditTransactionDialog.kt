package com.fredoliveira.financeskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.extension.formatDate
import com.fredoliveira.financeskotlin.model.Transaction
import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum

/**
 * Created by fred.oliveira on 23/05/2018.
 */
class EditTransactionDialog(viewGroup: ViewGroup,
                            private val context: Context) : TransactionDialog(context, viewGroup){
    override val titleButtonPositive: String
        get() = "Alterar"

    override fun getTitleByTransactionType(type: TransactionTypeEnum): Int {
        return when (type) {
            TransactionTypeEnum.REVENUE -> R.string.update_revenue
            TransactionTypeEnum.EXPENDITURE -> R.string.update_expense
        }
    }

    fun configDialog(transaction: Transaction, delegate: (transition: Transaction) -> Unit) {
        super.configDialog(transaction.type, delegate)

        initFields(transaction)
    }

    private fun initFields(transaction: Transaction) {
        fieldValue.setText(transaction.value.toString())
        fieldDate.setText(transaction.date.formatDate())
        val categories = context.resources.getStringArray(getCategoryByTransactionType(transaction.type))
        fieldCategory.setSelection(categories.indexOf(transaction.category), true)
    }
}