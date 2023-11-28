package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;

public class WeatherContext {
    private WeatherStrategy weatherStrategy;

    public void setWeatherStrategy(WeatherStrategy weatherStrategy){
        this.weatherStrategy = weatherStrategy;
    }

    public void applyWeather(Tile cell) {
        if (weatherStrategy != null) {
            WeatherStrategy.applyWeatherEffect(cell);
        }
    }
}
