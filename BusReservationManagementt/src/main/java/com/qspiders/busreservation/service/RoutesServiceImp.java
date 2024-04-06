package com.qspiders.busreservation.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.responsestructure.ResponseStructure;
@Service
public class RoutesServiceImp implements RoutesService {
	@Autowired
	BusDao busDao;

	@Override
	public ResponseStructure<Routes> saveRoute(Routes routes) {
		int busId = routes.getBusId();
		Bus bus = busDao.getByBusId(busId);
		bus.setRoutes(routes);
		Bus bus1 = busDao.save(bus);
		ResponseStructure<Routes> responseStructure = new ResponseStructure<>();
		responseStructure.setData(bus1.getRoutes());
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("routes of a bus is saved successfully");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

}
