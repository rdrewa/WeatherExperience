package pl.nemolab.weatherexperience;

import android.app.Application;

import retrofit.RestAdapter;

/**
 * Created by senator on 2015-03-07.
 */
public class WeatherApp extends Application {

    public static final String API_URL = "http://api.openweathermap.org/data/2.5";

    public static RestAdapter restAdapter;

    public static OpenWeatherMapApi.Weather apiWeather;
    public static OpenWeatherMapApi.Forecast apiForecast;

    @Override
    public void onCreate() {
        super.onCreate();

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .build();
        apiWeather = restAdapter.create(OpenWeatherMapApi.Weather.class);
        apiForecast = restAdapter.create(OpenWeatherMapApi.Forecast.class);
    }
}
