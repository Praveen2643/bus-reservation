package com.qspiders.busreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.Expenses;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.responsestructure.ResponseStructure;
import com.qspiders.busreservation.service.BusServiceImp;
import com.qspiders.busreservation.service.CouponServiceImp;
import com.qspiders.busreservation.service.ExpenseServiceImp;
import com.qspiders.busreservation.service.RoutesServiceImp;

@RequestMapping("/admin")
@RestController
public class BusController {

	@Autowired
	BusServiceImp busServiceImp;

	@Autowired
	RoutesServiceImp routesServiceImp;

	@Autowired
	CouponServiceImp couponServiceImp;

	@Autowired
	ExpenseServiceImp expenseServiceImp;

	@PostMapping("/saveBus")
	public ResponseEntity<?> save(@RequestBody Bus bus) {
		ResponseStructure<Bus> responseStructure = busServiceImp.save(bus);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get{busId}")
	public ResponseEntity<?> get(@PathVariable int busId) {
		ResponseStructure<Bus> responseStructure = busServiceImp.getByBusId(busId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);

	}

	@PostMapping("/saveRoute")
	public ResponseEntity<?> saveRoute(@RequestBody Routes routes) {
		ResponseStructure<Routes> responseStructure = routesServiceImp.saveRoute(routes);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PostMapping("/addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		ResponseStructure<Coupon> responseStructure = couponServiceImp.addCoupon(coupon);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateCoupon{couponId}")
	public ResponseEntity<?> updateCoupon(@PathVariable int couponId) {
		ResponseStructure<Coupon> responseStructure = couponServiceImp.updateCoupon(couponId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/removeCoupon{couponId}")
	public ResponseEntity<?> removeCoupon(@PathVariable int couponId) {
		ResponseStructure<?> responseStructure = couponServiceImp.removeCoupon(couponId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getCoupon{couponId}")
	public ResponseEntity<?> getCoupon(@PathVariable int couponId) {
		ResponseStructure<?> responseStructure = couponServiceImp.getCoupon(couponId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateExpenses")
	public ResponseEntity<?> updateExpenses(@RequestParam int maintenanceCharges, int petrol, int refreshements,
			int busId) {
		ResponseStructure<Expenses> responseStructure = expenseServiceImp.updateExpenses(maintenanceCharges, petrol,
				refreshements, busId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@GetMapping("/totalIncome")

	public ResponseEntity<?> totalIncome() {
		ResponseStructure<?> responseStructure = expenseServiceImp.totalIncome();
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

}
