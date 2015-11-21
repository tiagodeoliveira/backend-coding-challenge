package io.tiagodeoliveira.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by tiago on 21/11/15.
 */
@Entity
class Expense {
    @Id
    @GeneratedValue
    Long id
    Date date
    Float amount
    Float vat
    String reason
}

interface ExpenseRepository extends CrudRepository<Expense, Long>, PagingAndSortingRepository<Expense, Long> {
}
