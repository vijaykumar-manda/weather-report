package com.weather.model;

import java.util.List;

public class GeoDBResponse {
    private List<CityData> data;

	public List<CityData> getData() {
		return data;
	}

	public void setData(List<CityData> data) {
		this.data = data;
	}

    // Getter and setter
}