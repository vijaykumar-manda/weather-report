import React, { useState, useEffect } from "react";
import axios from "axios";
import WeatherData from "./components/WeatherData";
import "./App.css";
import { LOCAL_HOST_URL, WEATHER_API_KEY, WEATHER_API_URL } from "./API/Api";
import Forecast from "./components/forecast";
import Skeleton from "react-loading-skeleton";
const App = () => {
  const [inputValue, setInputValue] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [listVisible, setListvisible] = useState(false);
  const [weatherData, setWeatherData] = useState(null);
  const [forecastData, setForecastData] = useState(null);
  const [loading, setLoading] = useState(false);

  const delay = 600;

  useEffect(() => {
    const timer = setTimeout(() => {
      if (inputValue.trim() !== "") {
        fetchSuggestions(inputValue);
      }
    }, delay);
    return () => {
      clearTimeout(timer);
    };
  }, [inputValue]);

  const fetchSuggestions = async (value) => {
    try {
      const response = await axios.get(`/api/suggestions/${value}`);
      setSuggestions(response.data);
	  console.log(response.data);
    } catch (error) {
      console.error("Error fetching suggestions:", error);
    }
  };
  const searchCityData = (data) => {
    setInputValue(data.city);
    setListvisible(false);
    getWeatherData(data);
    setLoading(true);
  };
  const getWeatherData = async (value) => {
    console.log(value);
    try {
      const currentWeatherFetch = await axios.get(
        `${WEATHER_API_URL}/weather?lat=${value.latitude}&lon=${value.longitude}&appid=${WEATHER_API_KEY}&units=metric`
      );
      const ForecastWeatherFetch = await axios.get(
        `${WEATHER_API_URL}/forecast?lat=${value.latitude}&lon=${value.longitude}&appid=${WEATHER_API_KEY}&units=metric`
      );

      setWeatherData({ city: value.city, ...currentWeatherFetch.data });
      setForecastData({ city: value.city, ...ForecastWeatherFetch.data });
      console.log(weatherData);
      setLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <div className="App">
        <div className="center-container">
          <div className="input-container">
            <input
              type="text"
              value={inputValue}
              placeholder="Enter a location..."
              onChange={(e) => {
                setInputValue(e.target.value);
                setListvisible(true);
              }}
            />

            {listVisible && (
              <div className="suggestions">
                {suggestions.map((suggestion, index) => (
                  <div
                    className="suggestion"
                    key={index}
                    onClick={() => {
                      searchCityData(suggestion);
                    }}
                  >
                    {suggestion.city}
                  </div>
                ))}
              </div>
            )}
          </div>
          {weatherData && <WeatherData data={weatherData} />}
          {forecastData && <Forecast data={forecastData} />}
        </div>
      </div>
    </>
  );
};

export default App;
