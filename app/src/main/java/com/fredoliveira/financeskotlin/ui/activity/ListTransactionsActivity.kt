package com.fredoliveira.financeskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import com.fredoliveira.financeskotlin.R
import com.fredoliveira.financeskotlin.dao.TransactionDAO
import com.fredoliveira.financeskotlin.model.Transaction
import com.fredoliveira.financeskotlin.model.enum.TransactionTypeEnum
import com.fredoliveira.financeskotlin.ui.SummaryView
import com.fredoliveira.financeskotlin.ui.adapter.ListTransactionsAdapter
import com.fredoliveira.financeskotlin.ui.dialog.AddTransactionDialog
import com.fredoliveira.financeskotlin.ui.dialog.EditTransactionDialog
import kotlinx.android.synthetic.main.activity_list_transactions.*

/**
 * Created by fred.oliveira on 16/05/2018.
 */
class ListTransactionsActivity : AppCompatActivity() {

    private val transactionDAO = TransactionDAO()
    private val transactions = transactionDAO.transactions
    private val viewActivity by lazy { window.decorView }
    private val viewGroupActivity by lazy { viewActivity as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)

        configSummary()
        updateAll()
        configFabButton()
        configList()
    }

    private fun configFabButton() {
        list_transaction_add_revenue.setOnClickListener {
            callDialogAddTransaction(TransactionTypeEnum.REVENUE)
        }

        list_transaction_add_expenditure.setOnClickListener {
            callDialogAddTransaction(TransactionTypeEnum.EXPENDITURE)
        }
    }

    private fun callDialogAddTransaction(transactionType: TransactionTypeEnum) {
        AddTransactionDialog(viewGroupActivity, this)
                .configDialog(transactionType, { transaction ->
                    addTransaction(transaction)

                    list_transaction_add_menu.close(true)
                })
    }

    private fun addTransaction(transaction: Transaction) {
        transactionDAO.add(transaction)

        updateAll()
    }

    private fun updateAll() {
        updateSummary()

        updateAdapter()
    }

    private fun configSummary(): SummaryView {
        val summaryView = SummaryView(this, viewActivity, transactions)

        summaryView.update()

        return summaryView
    }

    private fun updateSummary() {
        configSummary()
    }

    private fun updateAdapter() {
        lista_transacoes_listview.adapter = ListTransactionsAdapter(transactions, this)
    }

    private fun configList() {
        lista_transacoes_listview.setOnItemClickListener { parent, view, position, id ->
            val transactionClick = transactions[position]

            EditTransactionDialog(viewGroupActivity, this)
                    .configDialog(transactionClick, { transactionUpdated ->
                        transactionDAO.update(transactionUpdated, position)

                        updateAll()
                    })
        }

        lista_transacoes_listview.setOnCreateContextMenuListener { menu, view, menuInfo ->
            menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuId = item?.itemId
        when(menuId) {
            1 -> removeItem(item)
        }

        return super.onContextItemSelected(item)
    }

    private fun removeItem(item: MenuItem) {
        val adapterMenu = item.menuInfo as AdapterView.AdapterContextMenuInfo

        transactionDAO.remove(adapterMenu.position)

        updateAll()
    }

}