package com.qspiders.busreservation.busrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qspiders.busreservation.dto.Expenses;

public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {
Expenses findByBusId(int busId);
}
