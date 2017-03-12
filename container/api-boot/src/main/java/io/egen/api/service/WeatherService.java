package io.egen.api.service;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();

	public Weather findOne(String id);

	public List<String> findAllCities();
	
	public Weather findLatest(String city);
	
	public String findLatestProperty(String city,String property);

	public String findDailyAverage(String city,String date);
	
	public String findHourlyAverage(String city,String date,String time);
	
	public Weather create(Weather Weather);

	public Weather update(String id, Weather Weather);

	public void delete(String id);
}