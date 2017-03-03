package io.egen.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	private WeatherRepository repository;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findAllCities() {
		return repository.findAllCities();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findLatest(String city) {
		return repository.findLatest(city)
				.orElseThrow(() -> new NotFoundException("City  " + city + " does not exist"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public String findLatestProperty(String city,String property) {
		return repository.findLatestProperty(city,property)
				.orElseThrow(() -> new NotFoundException("City  " + city + " does not exist"));
	}

	@Override
	@Transactional
	public Weather create(Weather weather) {
		return repository.create(weather);
	}

	@Override
	public String findDailyAverage(String city, String date) {
		
		return repository.findDailyAverage(city,date)
				.orElseThrow(() -> new NotFoundException("City  " + city + " does not exist"));
	}

	
}