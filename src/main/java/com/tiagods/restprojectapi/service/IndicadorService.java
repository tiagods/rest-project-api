package com.tiagods.restprojectapi.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class IndicadorService implements HealthIndicator {
	@Override
	public Health health() {
		return Health.up().build();
	}

}
