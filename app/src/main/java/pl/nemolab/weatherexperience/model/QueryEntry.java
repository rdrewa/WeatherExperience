package pl.nemolab.weatherexperience.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "queries")
public class QueryEntry {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "created_at")
    private long createdAt;
    @DatabaseField(columnName = "city_input")
    private String cityInput;
    @DatabaseField(columnName = "city_id")
    private String cityId;
    @DatabaseField(columnName = "city_name")
    private String cityName;
    @DatabaseField
    private double lon;
    @DatabaseField
    private double lat;
    @ForeignCollectionField
    private ForeignCollection<ForecastEntry> forecasts;

    public QueryEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCityInput() {
        return cityInput;
    }

    public void setCityInput(String cityInput) {
        this.cityInput = cityInput;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public ForeignCollection<ForecastEntry> getForecasts() {
        return forecasts;
    }

    public void setForecasts(ForeignCollection<ForecastEntry> forecasts) {
        this.forecasts = forecasts;
    }
}
