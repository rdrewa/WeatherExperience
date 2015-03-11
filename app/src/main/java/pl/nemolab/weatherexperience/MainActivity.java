package pl.nemolab.weatherexperience;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.nemolab.weatherexperience.data.Weather;
import pl.nemolab.weatherexperience.data.WeatherResponse;
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

    @InjectView(R.id.listLocations)
    ListView listLocations;

    @InjectView(R.id.listForecasts)
    ListView listForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.btnSearch)
    public void executeSearch(View view) {
        txtLog.clearComposingText();
        String query = edtLocation.getText().toString();
        WeatherApp.apiWeather.getWeather(query, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                if (weatherResponse != null) {
                    ArrayList<Weather> weathers = weatherResponse.getWeather();
                    if (weathers != null && !weathers.isEmpty()) {
                        Weather weather = weathers.get(0);
                        txtLog.setText(weather.getMain() + " / " + weather.getDescription());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
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
