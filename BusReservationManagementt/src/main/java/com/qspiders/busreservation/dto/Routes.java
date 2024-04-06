package com.qspiders.busreservation.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Routes {
	@Id
	private int routeId;
	private int busId;
	private String destination;
	private String origin;
	private String stops;
	private String distance;
	private String duration;
}
