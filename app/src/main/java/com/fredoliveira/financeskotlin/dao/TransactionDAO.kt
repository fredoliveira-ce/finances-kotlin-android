package com.fredoliveira.financeskotlin.dao

import com.fredoliveira.financeskotlin.model.Transaction

/**
 * Created by fred.oliveira on 25/05/2018.
 */
class TransactionDAO {

    val transactions: List<Transaction> = Companion.transactions

    companion object {
        private val transactions: MutableList<Transaction> = mutableListOf()
    }


    fun add(transaction: Transaction) {
        Companion.transactions.add(transaction)
    }

    fun update(transaction: Transaction, position: Int) {
        Companion.transactions[position] = transaction
    }

    fun remove(position: Int) {
        Companion.transactions.removeAt(position)
    }

}