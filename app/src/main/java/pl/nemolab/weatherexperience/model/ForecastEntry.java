package pl.nemolab.weatherexperience.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "forecasts")
public class ForecastEntry {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, columnName = "query_id")
    private QueryEntry query;
    @DatabaseField(columnName = "measured_at")
    private long measuredAt;
    @DatabaseField(columnName = "temp_day")
    private float day;
    @DatabaseField(columnName = "temp_min")
    private float min;
    @DatabaseField(columnName = "temp_max")
    private float max;
    @DatabaseField(columnName = "temp_night")
    private float night;
    @DatabaseField(columnName = "temp_eve")
    private float eve;
    @DatabaseField(columnName = "temp_morn")
    private float morn;
    @DatabaseField
    private float pressure;
    @DatabaseField
    private int humidity;
    @DatabaseField(columnName = "weather_main")
    private String main;
    @DatabaseField(columnName = "weather_description")
    private String description;
    @DatabaseField(columnName = "weather_icon")
    private String icon;
    @DatabaseField
    private float speed;

    public ForecastEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QueryEntry getQuery() {
        return query;
    }

    public void setQuery(QueryEntry query) {
        this.query = query;
    }

    public long getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(long measuredAt) {
        this.measuredAt = measuredAt;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
