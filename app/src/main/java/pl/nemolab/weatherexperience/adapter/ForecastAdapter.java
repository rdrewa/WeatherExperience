package pl.nemolab.weatherexperience.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.nemolab.weatherexperience.OpenWeatherMapApi;
import pl.nemolab.weatherexperience.R;
import pl.nemolab.weatherexperience.model.ForecastEntry;
import pl.nemolab.weatherexperience.util.ReadableDate;

public class ForecastAdapter extends ArrayAdapter<ForecastEntry> {

    private static final int LAYOUT = R.layout.item_forecast;
    private final LayoutInflater inflater;

    public ForecastAdapter(Context context, List<ForecastEntry> forecasts) {
        super(context, LAYOUT, forecasts);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView != null) {
            holder = (Holder) convertView.getTag();
        } else {
            convertView = inflater.inflate(LAYOUT, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        ForecastEntry forecast = getItem(position);
        holder.txtDatetime.setText(ReadableDate.formatDate(forecast.getMeasuredAt()));
        holder.txtDescription.setText(forecast.getDescription());
        holder.txtMin.setText(toCelsius(forecast.getMin()));
        holder.txtMax.setText(toCelsius(forecast.getMax()));
        holder.txtDay.setText(toCelsius(forecast.getDay()));
        holder.txtNight.setText(toCelsius(forecast.getNight()));
        holder.txtPressure.setText(String.valueOf(forecast.getPressure()));
        holder.txtHumidity.setText(String.valueOf(forecast.getHumidity()));
        String iconUrl = OpenWeatherMapApi.ICON_URL + forecast.getIcon() + ".png";
        Picasso.with(getContext()).load(iconUrl).into(holder.imgIcon);
        return convertView;
    }

    private String toCelsius(double temp) {
        return String.format("%.1f", (temp - 272.15));
    }

    static class Holder {
        @InjectView(R.id.imgIcon)
        ImageView imgIcon;
        @InjectView(R.id.txtDatetime)
        TextView txtDatetime;
        @InjectView(R.id.txtDescription)
        TextView txtDescription;
        @InjectView(R.id.txtMin)
        TextView txtMin;
        @InjectView(R.id.txtMax)
        TextView txtMax;
        @InjectView(R.id.txtDay)
        TextView txtDay;
        @InjectView(R.id.txtNight)
        TextView txtNight;
        @InjectView(R.id.txtPressure)
        TextView txtPressure;

        @InjectView(R.id.txtHumidity)
        TextView txtHumidity;

        Holder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
