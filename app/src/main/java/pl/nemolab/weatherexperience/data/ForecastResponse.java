package pl.nemolab.weatherexperience.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastResponse {

    @SerializedName("cod")
    private String code;
    private float message;
    private City city;
    @SerializedName("count")
    private int count;
    private ArrayList<Forecast> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Forecast> getList() {
        return list;
    }

    public void setList(ArrayList<Forecast> list) {
        this.list = list;
    }
}
