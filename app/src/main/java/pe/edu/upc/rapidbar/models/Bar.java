package pe.edu.upc.rapidbar.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Bar {
    private String id;
    private String name;
    private String description;
    private String idTypeBar;
    private String typeBarName;
    private String latitude;
    private String longitude;
    private String rate;
    private String address;
    private String pictureId;

    public Bar() {
    }

    public Bar(String id, String name, String description, String idTypeBar, String typeBarName, String latitude, String longitude, String rate, String address, String pictureId) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setIdTypeBar(idTypeBar);
        this.setTypeBarName(typeBarName);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setRate(rate);
        this.setAddress(address);
        this.setPictureId(pictureId);
    }


    public String getId() {
        return id;
    }

    public Bar setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bar setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Bar setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIdTypeBar() {
        return idTypeBar;
    }

    public Bar setIdTypeBar(String idTypeBar) {
        this.idTypeBar = idTypeBar;
        return this;
    }

    public String getTypeBarName() {
        return typeBarName;
    }

    public Bar setTypeBarName(String typeBarName) {
        this.typeBarName = typeBarName;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public Bar setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public Bar setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public Bar setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Bar setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPictureId() {
        return pictureId;
    }

    public Bar setPictureId(String pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);
        bundle.putString("description",description);
        bundle.putString("idTypeBar",idTypeBar);
        bundle.putString("typeBarName",typeBarName);
        bundle.putString("latitude",latitude);
        bundle.putString("longitude",longitude);
        bundle.putString("rate",rate);
        bundle.putString("address",address);
        bundle.putString("pictureId",pictureId);
        return bundle;
    }

    public static Bar from(Bundle bundle)
    {
        Bar bar = new Bar();
        return bar.setId(bundle.getString("id"))
                .setName(bundle.getString("name"))
                .setDescription(bundle.getString("description"))
                .setIdTypeBar(bundle.getString("idTypeBar"))
                .setTypeBarName(bundle.getString("typeBarName"))
                .setLatitude(bundle.getString("latitude"))
                .setLongitude(bundle.getString("longitude"))
                .setRate(bundle.getString("rate"))
                .setAddress(bundle.getString("address"))
                .setPictureId(bundle.getString("pictureId"));
    }
    public static Bar from(JSONObject jsonSource)
    {
        Bar bar = new Bar();
        try
        {
            bar.setId(jsonSource.getString("id"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("description"))
                    .setIdTypeBar(jsonSource.getString("idTypeBar"))
                    .setTypeBarName(jsonSource.getString("typeBarName"))
                    .setLatitude(jsonSource.getString("latitude"))
                    .setLongitude(jsonSource.getString("longitude"))
                    .setRate(jsonSource.getString("rate"))
                    .setAddress(jsonSource.getString("address"))
                    .setPictureId(jsonSource.getString("pictureId"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        return bar;
    }
    public static List<Bar> from(JSONArray jsonSources)
    {
        List<Bar> bars = new ArrayList<>();
        for(int i = 0; i<jsonSources.length(); i++)
        {
            try
            {
                bars.add(Bar.from(jsonSources.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return bars;
    }
}
