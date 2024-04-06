package com.qspiders.busreservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dao.ExpenseDao;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Expenses;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class ExpenseServiceImp implements ExpensesService {
	@Autowired
	BusDao busDao;
	@Autowired
	ExpenseDao expenseDao;

	@Override
	public ResponseStructure<Expenses> updateExpenses(int maintananceCharges, int petrol, int refreshments, int busId) {
		Bus bus = busDao.getByBusId(busId);
		Expenses expenses = bus.getExpenses();
		expenses.setMaintenanceCharges(maintananceCharges);
		expenses.setPetrol(petrol);
		expenses.setRefreshments(refreshments);
		bus.setExpenses(expenses);
		Bus bus2 = busDao.save(bus);
		ResponseStructure<Expenses> responseStructure = new ResponseStructure<>();
		responseStructure.setData(bus2);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("expenses updated");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

	@Override
	public ResponseStructure<?> totalIncome() {
		List<Expenses> listOfExpenses = expenseDao.getAllBusExpenses();
		int profit = 0;
		for (Expenses expense : listOfExpenses) {
			profit += expense.getTotalIncome() - expense.getMaintenanceCharges() - expense.getPetrol()
					- expense.getRefreshments();
		}
		ResponseStructure<Expenses> responseStructure = new ResponseStructure<>();
		responseStructure.setData("total profit");
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("total profit from all buses is"+profit);
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

}
