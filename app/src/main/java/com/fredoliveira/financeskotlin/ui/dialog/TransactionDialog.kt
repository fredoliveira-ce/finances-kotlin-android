package com.fredoliveira.financeskotlin.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.extension.convertForCalendar
import com.fredoliveira.financeskotlin.extension.formatDate
import com.fredoliveira.financeskotlin.model.Transaction
import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by fred.oliveira on 24/05/2018.
 */
abstract class TransactionDialog(private val context: Context,
                                 private val viewGroup: ViewGroup) {
    private val viewDialog = createLayout()

    protected val fieldDate = viewDialog.form_transaction_date

    protected val fieldValue = viewDialog.form_transaction_value

    protected val fieldCategory = viewDialog.form_transaction_category

    abstract protected val titleButtonPositive: String

    fun configDialog(type: TransactionTypeEnum, delegate: (transaction: Transaction) -> Unit) {
        configDateField()

        configCategory(type)

        configForm(type, delegate)
    }

    private fun configForm(type: TransactionTypeEnum, delegate: (transaction: Transaction) -> Unit) {
        var title = getTitleByTransactionType(type)

        android.support.v7.app.AlertDialog.Builder(context)
                .setTitle(title)
                .setView(viewDialog)
                .setPositiveButton(titleButtonPositive, { dialogInterface, _ ->
                    val value = fieldValue.text.toString()
                    val date = fieldDate.text.toString()
                    val category = fieldCategory.selectedItem.toString()
                    var calendarDate = date.convertForCalendar()

                    val valueBD = validValue(value)

                    val transaction = Transaction(valueBD, category, type, calendarDate)

                    delegate(transaction)

                })
                .setNegativeButton("Cancel", null)
                .show()
    }

    abstract protected fun getTitleByTransactionType(type: TransactionTypeEnum): Int

    private fun validValue(value: String): BigDecimal {
        return try {
            BigDecimal(value)
        } catch (exeception: NumberFormatException) {
            Toast.makeText(context, "Value conversion failed.", Toast.LENGTH_LONG)

            BigDecimal.ZERO
        }
    }

    private fun configCategory(type: TransactionTypeEnum) {
        var categories = getCategoryByTransactionType(type)

        fieldCategory.adapter = ArrayAdapter.createFromResource(context, categories, android.R.layout.simple_spinner_dropdown_item)
    }

    protected fun getCategoryByTransactionType(type: TransactionTypeEnum): Int {
        return when (type) {
            TransactionTypeEnum.REVENUE -> R.array.categories_of_revenue
            TransactionTypeEnum.EXPENDITURE -> R.array.categories_of_expense
        }
    }

    private fun configDateField() {
        val date = Calendar.getInstance()

        fieldDate.setText(Calendar.getInstance().formatDate())
        fieldDate.setOnClickListener {
            DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        date.set(ano, mes, dia)
                        fieldDate.setText(date.formatDate())
                    }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun createLayout(): View {
        return LayoutInflater.from(context).inflate(R.layout.form_transaction, viewGroup, false)
    }
}