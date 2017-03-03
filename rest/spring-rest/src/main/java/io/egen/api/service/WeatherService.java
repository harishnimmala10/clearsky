package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<String> findAllCities();

	public Weather findLatest(String city);
	
	public String findLatestProperty(String city,String property);

	public Weather create(Weather user);

	public String findDailyAverage(String city,String date);
	
}