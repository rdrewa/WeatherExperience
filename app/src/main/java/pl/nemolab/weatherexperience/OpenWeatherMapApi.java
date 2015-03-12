package pl.nemolab.weatherexperience;

import pl.nemolab.weatherexperience.data.ForecastResponse;
import pl.nemolab.weatherexperience.data.WeatherResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by senator on 2015-03-07.
 */
public class OpenWeatherMapApi {
    public static final String TAG = "WEATHER-API";

    public interface Weather {
        @GET("/weather")
        void getWeather(
                @Query("q") String location,
                Callback<WeatherResponse> cb
        );
    }

    public interface Forecast {
        @GET("/forecast/daily")
        void getWeekForecast(@Query("q") String location, Callback<ForecastResponse> cb);
    }
}
