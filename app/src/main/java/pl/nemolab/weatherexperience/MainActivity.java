package pl.nemolab.weatherexperience;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.nemolab.weatherexperience.data.City;
import pl.nemolab.weatherexperience.data.Coord;
import pl.nemolab.weatherexperience.data.Forecast;
import pl.nemolab.weatherexperience.data.ForecastResponse;
import pl.nemolab.weatherexperience.data.Temp;
import pl.nemolab.weatherexperience.data.Weather;
import pl.nemolab.weatherexperience.data.WeatherResponse;
import pl.nemolab.weatherexperience.model.ForecastEntry;
import pl.nemolab.weatherexperience.model.QueryEntry;
import pl.nemolab.weatherexperience.orm.DatabaseHelper;
import pl.nemolab.weatherexperience.util.ReadableDate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.edtLocation)
    EditText edtLocation;

    @InjectView(R.id.btnSearch)
    Button btnSearch;

    @InjectView(R.id.txtLog)
    TextView txtLog;

    @InjectView(R.id.listForecasts)
    ListView listForecasts;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        databaseHelper = new DatabaseHelper(this);
    }


    @OnClick(R.id.btnSearch)
    public void executeSearch(View view) {
        txtLog.clearComposingText();
        final String query = edtLocation.getText().toString();
        WeatherApp.apiForecast.getWeekForecast(query, new Callback<ForecastResponse>() {
            @Override
            public void success(ForecastResponse forecastResponse, Response response) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                int queryEntryId = 0;
                if (forecastResponse != null) {
                    if (!forecastResponse.getCode().equals("200")) {
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.wrong_request), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        txtLog.setText("");
                        edtLocation.setText("");
                        return;
                    }
                    Dao<QueryEntry, Integer> queryDao = databaseHelper.getQueryDao();
                    Dao<ForecastEntry, Integer> forecastDao = databaseHelper.getForecastDao();
                    QueryEntry queryEntry = new QueryEntry();
                    City city = forecastResponse.getCity();
                    Coord coord = city.getCoord();
                    queryEntry.setCityId(city.getId());
                    queryEntry.setCityInput(query);
                    queryEntry.setCityName(city.getName());
                    queryEntry.setLat(coord.getLat());
                    queryEntry.setLon(coord.getLon());
                    queryEntry.setCreatedAt(System.currentTimeMillis());
                    try {
                        queryEntryId = queryDao.create(queryEntry);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ForecastEntry forecastEntry;
                    for (Forecast forecast : forecastResponse.getList()) {
                        forecastEntry = new ForecastEntry();
                        forecastEntry.setQuery(queryEntry);
                        forecastEntry.setMeasuredAt(forecast.getDatetime());
                        Temp temp = forecast.getTemp();
                        forecastEntry.setDay(temp.getDay());
                        forecastEntry.setMin(temp.getMin());
                        forecastEntry.setMax(temp.getMax());
                        forecastEntry.setNight(temp.getNight());
                        forecastEntry.setEve(temp.getEve());
                        forecastEntry.setMorn(temp.getMorn());
                        forecastEntry.setPressure(forecast.getPressure());
                        forecastEntry.setHumidity(forecast.getHumidity());
                        Weather weather = forecast.getWeather().get(0);
                        forecastEntry.setMain(weather.getMain());
                        forecastEntry.setDescription(weather.getDescription());
                        forecastEntry.setIcon(weather.getIcon());
                        forecastEntry.setSpeed(forecast.getSpeed());
                        try {
                            forecastDao.create(forecastEntry);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    if (queryEntryId > 0) {
                        try {
                            QueryEntry storedQuery = queryDao.queryForId(queryEntryId);
                            txtLog.setText("");
                            StringBuilder sb;
                            for (ForecastEntry storedForecast : storedQuery.getForecasts()) {
                                sb = new StringBuilder();
                                sb.append("[" + storedForecast.getIcon() + "]: ");
                                sb.append(storedForecast.getDescription() + " ");
                                sb.append("(" + storedForecast.getMain() + ")");
                                sb.append("\n");
                                txtLog.append(sb.toString());
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                String message = error.getMessage();
                String url = error.getUrl();
                String log = "url: " + url + "\nmessage: " + message;
                txtLog.setText(log);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
