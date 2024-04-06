package com.qspiders.busreservation.service;

import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface RoutesService {
	public ResponseStructure<?>saveRoute(Routes routes);
}
