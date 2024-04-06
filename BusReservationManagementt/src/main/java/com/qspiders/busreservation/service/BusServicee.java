package com.qspiders.busreservation.service;

import java.util.List;

import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface BusServicee {
public ResponseStructure<Bus>save(Bus bus);
public ResponseStructure<?>getByBusId(int busId);
public ResponseStructure<?>getByTimeArrivalDepature(String arrival, String depature, String date,String time);

}
