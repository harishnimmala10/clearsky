package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public List<String> findAllCities();

	public Optional<Weather> findLatest(String city);

	public Optional<String> findLatestProperty(String city,String property);

	public Weather create(Weather user);

	public Optional<String> findDailyAverage(String city, String date);
	
}