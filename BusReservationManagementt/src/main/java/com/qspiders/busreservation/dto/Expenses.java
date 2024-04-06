package com.qspiders.busreservation.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Expenses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int busId;
	private int busNo;
	private int petrol;
	private int refreshments;
	private int maintenanceCharges;
	private int totalIncome;
}
