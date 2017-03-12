package io.egen.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
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
	public Weather findLatest(String city) {
		return repository.findTopByCityOrderByTimestampDesc(city)
				.orElseThrow(() -> new NotFoundException("City  " + city + " does not exist"));
	}

	@Override
	@Transactional(readOnly = true)
	public String findLatestProperty(String city, String property) {
		Weather w = repository.findTopByCityOrderByTimestampDesc(city)
				.orElseThrow(() -> new NotFoundException("City  " + city + " does not exist"));
		return w.getProperty(property).orElseThrow(() -> new NotFoundException("Invalid property"));
	}

	@Override
	@Transactional
	public Weather create(Weather weather) {
		return repository.save(weather);
	}

	@Override
	public List<Weather> findAll() {
		return repository.findAll();
	}

	@Override
	public Weather findOne(String id) {
		return repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Weather data with id " + id + " does not exist"));
	}

	@Override
	public Weather update(String id, Weather Weather) {
		repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Weather data with id " + id + " does not exist"));
		return repository.save(Weather);
	}

	@Override
	public void delete(String id) {
		Weather existing = repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Weather data with id " + id + " does not exist"));
		repository.delete(existing);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findAllCities() {
		return repository.findAllCities();
	}

	@Override
	public String findDailyAverage(String city, String date) {
		List<Weather> weathers = repository.findByCityAndTimestampStartingWith(city, date)
				.orElseThrow(() -> new NotFoundException("No records found for the given input"));
		
		double pressure = 0, temperature = 0, humidity = 0, speed = 0, degree = 0;
		if (!weathers.isEmpty()) {

			for (Weather w : weathers) {
				pressure += Double.parseDouble(w.getPressure());
				temperature += Double.parseDouble(w.getTemperature());
				humidity += Double.parseDouble(w.getHumidity());
				speed += Double.parseDouble(w.getWind().getSpeed());
				degree += Double.parseDouble(w.getWind().getDegree());
			}
			int size = weathers.size();
			pressure /= size;
			temperature /= size;
			humidity /= size;
			speed /= size;
			degree /= size;

		}
		return ("Pressure : " + pressure + "\nTemperature : " + temperature + "\nHumidity : " + humidity + "\nSpeed : "
				+ speed + "\nDegree : " + degree);
	}

	@Override
	public String findHourlyAverage(String city, String date, String time) {
		return findDailyAverage(city, date + "T" + time);
	}

}