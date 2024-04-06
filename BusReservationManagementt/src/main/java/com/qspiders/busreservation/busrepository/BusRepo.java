package com.qspiders.busreservation.busrepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;

import jakarta.transaction.Transactional;

public interface BusRepo extends JpaRepository<Bus, Integer> {
	Optional<Bus> findByBusId(int busId);

	Optional<Bus> findByTime(String time);

	@Query(nativeQuery = true, value = "SELECT * FROM bus b WHERE b.arrival=? AND b.depature =? AND b.date=? AND b.time=?")
	Optional<List<Bus>> findByArrivalDeparture(@Param("arrival") String arrival, @Param("depature") String departure,
			@Param("date") String date, @Param("time") String time);

    //named query
	@Query(name = "Bus.isTicketAvailable")
	Optional<List<Bus>> findByIsBusAvailable(@Param("arrival") String arrival, @Param("depature") String departure,
		@Param("date") String date, @Param("time") String time);
	
	
}

//select e1_0.employee_id,e1_0.emp_email,e1_0.emp_name,e1_0.emp_password,e1_0.emp_phone from employee e1_0 where e1_0.emp_phone=?
//@Query(value = "SELECT p FROM Task p WHERE p.id=:q")
//Page<Task> findByKeyword(@Param("q") Long q, Pageable pageable);