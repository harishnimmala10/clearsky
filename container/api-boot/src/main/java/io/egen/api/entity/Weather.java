package io.egen.api.entity;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Weather {

	@Id
	private String id;
	private String city;
	private String description;
	private String humidity;
	private String pressure;
	private String timestamp;
	private String temperature;
	@OneToOne(cascade = {CascadeType.ALL})
	private Wind wind;
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public Weather() {
		this.id = UUID.randomUUID().toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Optional<String> getProperty(String property) {
		// TODO Auto-generated method stub
		System.out.println(property);
		System.out.println(this);
		if(property.equals("temperature")) {
			return Optional.of(this.temperature);
		}
		else if(property.equals("city")) {
			return Optional.of(this.city);
		}
		else if(property.equals("description")) {
			return Optional.of(this.description);
		}
		else if(property.equals("humidity")) {
			return Optional.of(this.humidity);
		}
		else if(property.equals("timestamp")) {
			return Optional.of(this.timestamp);
		}
		else if(property.equals("pressure")) {
			return Optional.of(this.pressure);
		}
		else if(property.equals("wind")) {
			return Optional.of(this.wind.toString());
		}
		return Optional.empty();
	}
	@Override
	public String toString() {
		return "Weather [city=" + city + ", description=" + description + ", humidity=" + humidity + ", pressure="
				+ pressure + ", timestamp=" + timestamp + ", temperature=" + temperature + ", wind=" + wind + "]";
	}
	
}
