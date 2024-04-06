package com.qspiders.busreservation.service;

import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface ExpensesService {
	public ResponseStructure<?> updateExpenses(int maintananceCharges, int petrol, int refreshments, int busId);

	public ResponseStructure<?> totalIncome();
}
