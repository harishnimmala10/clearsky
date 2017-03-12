package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.egen.api.entity.Weather;

public interface WeatherRepository extends Repository<Weather, String> {

	public List<Weather> findAll();

	public Optional<Weather> findOne(String id);

	@Query("SELECT DISTINCT u.city FROM Weather u ORDER BY u.city")
	public List<String> findAllCities();
	
	public Optional<Weather> findTopByCityOrderByTimestampDesc(@Param("pCity") String City);
	
	public Optional<Weather> findByCity(String city);
	
	public Optional<List<Weather>> findByCityAndTimestampStartingWith(@Param("pCity") String City,@Param("pDate") String Timestamp);

	public Weather save(Weather Weather); //update and insert

	public void delete(Weather Weather);
}