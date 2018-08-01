package com.fredoliveira.financeskotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.extension.formatDate
import com.fredoliveira.financeskotlin.extension.formatDecimal
import com.fredoliveira.financeskotlin.model.Transaction
import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum
import kotlinx.android.synthetic.main.transaction_item.view.*

/**
 * Created by fred.oliveira on 18/05/2018.
 */
class ListTransactionsAdapter(private val transactions: List<Transaction>,
                              private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)

        val transaction = transactions[position]

        when (transaction.type) {
            TransactionTypeEnum.REVENUE -> {
                view.transaction_value.setTextColor(ContextCompat.getColor(context, R.color.revenue))
                view.transaction_icon.setBackgroundResource(R.drawable.icon_transaction_item_revenue)
            }
            TransactionTypeEnum.EXPENDITURE -> {
                view.transaction_value.setTextColor(ContextCompat.getColor(context, R.color.expense))
                view.transaction_icon.setBackgroundResource(R.drawable.icon_transaction_item_expense)
            }
        }

        view.transaction_value.text = transaction.value.formatDecimal()
        view.transaction_category.text = transaction.category
        view.transaction_date.text = transaction.date.formatDate()

        return view
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactions.size
    }

}