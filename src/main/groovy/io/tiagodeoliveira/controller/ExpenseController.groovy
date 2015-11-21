package io.tiagodeoliveira.controller

import groovy.transform.CompileStatic
import groovy.util.logging.Log
import io.tiagodeoliveira.repository.Expense
import io.tiagodeoliveira.repository.ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by tiago on 21/11/15.
 */
@Log
@RestController
@RequestMapping('/expenses')
class ExpenseController {

    @Autowired ExpenseRepository expenseRepository

    @RequestMapping(value = ["","/"], method = RequestMethod.POST)
    def addExpense(@RequestBody Expense expense) {
        log.info("Processing expense ${expense.dump()}")

        this.expenseRepository.save(expense)

        return ""
    }

    @RequestMapping(value = ["","/"], method = RequestMethod.GET)
    def listExpenses() {
        log.info('Listing all expenses')

        def expenses = []
        this.expenseRepository.findAll().each {
            expenses += it
        }
        return expenses
    }
}
