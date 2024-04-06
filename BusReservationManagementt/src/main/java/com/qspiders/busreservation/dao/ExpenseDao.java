package com.qspiders.busreservation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.ExpensesRepo;
import com.qspiders.busreservation.dto.Expenses;

@Repository
public class ExpenseDao {
	@Autowired
	ExpensesRepo expensesRepo;

	public Expenses getExpense(int busId) {
		Expenses expenses = expensesRepo.findByBusId(busId);
		return expenses;

	}

	public List<Expenses> getAllBusExpenses() {
		List<Expenses> listOfExpenses = expensesRepo.findAll();
		return listOfExpenses;

	}

}
