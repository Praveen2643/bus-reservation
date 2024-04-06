package com.qspiders.busreservation.busrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qspiders.busreservation.dto.Seats;

public interface SeatsRepo extends JpaRepository<Seats, Integer> {
	@Query(nativeQuery = true,value = "SELECT * FROM seats s WHERE s.bus_id=? AND s.seat_no=?")
	Optional<Seats> findSeats(@Param("busId") int busId, @Param("seatNo") int seatNo);
}
