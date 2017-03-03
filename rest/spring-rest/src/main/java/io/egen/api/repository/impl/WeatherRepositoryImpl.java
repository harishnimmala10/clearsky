package io.egen.api.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> findAllCities() {
		TypedQuery<String> query = em.createNamedQuery("Weather.findAllCities", String.class);
		return query.getResultList();
	}

	@Override
	public Optional<String> findLatestProperty(String city,String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findLatest", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> weathers = query.getResultList();
		if (!weathers.isEmpty()) {
			Weather weather = weathers.get(0);
			
			return Optional.of(weather.getProperty(property));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Weather> findLatest(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findLatest", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> weathers = query.getResultList();
		if (!weathers.isEmpty()) {
			return Optional.of(weathers.get(0));
		} else {
			return Optional.empty();
		}
		
	}

	@Override
	public Weather create(Weather weather) {
		em.persist(weather);
		return weather;
	}

	@Override
	public Optional<String> findDailyAverage(String city, String date) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findDailyAverage", Weather.class);
		query.setParameter("pCity", city);
		query.setParameter("pDate", date);
		List<Weather> weathers = query.getResultList();
		
		if (!weathers.isEmpty()) {
			double pressure=0,temperature=0,humidity=0,speed=0,degree=0;
			for(Weather w : weathers) {
				pressure+= Double.parseDouble(w.getPressure());
				temperature+= Double.parseDouble(w.getTemperature());
				humidity+= Double.parseDouble(w.getHumidity());
				speed+= Double.parseDouble(w.getWind().getSpeed());
				degree+= Double.parseDouble(w.getWind().getDegree());
			}
			int size=weathers.size();
			pressure/=size;temperature/=size;humidity/=size;speed/=size;degree/=size;
			return Optional.of("Pressure : " + pressure + " Temperature : " + temperature +
					"Humidity : " + humidity + " Speed : " + speed + " Degree : " +degree);
 		} else {
			return Optional.empty();
		}
	}

	
}