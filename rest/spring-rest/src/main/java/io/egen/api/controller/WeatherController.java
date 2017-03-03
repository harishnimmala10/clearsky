package io.egen.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.URI;
import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = URI.WEATHERDATA)
@Api(tags = "weather")
public class WeatherController {

	private WeatherService service;

	public WeatherController(WeatherService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Weather", notes = "Creates a weather record")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather weather) {
		return service.create(weather);
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities")
	@ApiOperation(value = "Find the cities which reported weather in the pst", notes = "Returns a list of cities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> findAllCities() {
		return service.findAllCities();
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.CITY)
	@ApiOperation(value = "Find latest weather for the given city", notes = "Returns latest weather record for the city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findLatest(@PathVariable("city") String city) {
		return service.findLatest(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.PROPERTY)
	@ApiOperation(value = "Find latest weather property for the given city", notes = "Returns latest weather property for the city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String findLatestProperty(@PathVariable("city") String city, @PathVariable("property") String property) {
		return service.findLatestProperty(city,property);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.DAILYAVERAGE)
	@ApiOperation(value = "Find daily average weather the given city", notes = "Returns daily average weather for the city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String findDailyAverage(@PathVariable("city") String city, @PathVariable("date") String date) {
		return service.findDailyAverage(city,date);
	}
	
	
}