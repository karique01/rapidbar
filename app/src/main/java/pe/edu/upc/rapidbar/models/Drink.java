package pe.edu.upc.rapidbar.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Drink {
    private String id;
    private String productName;
    private String description;
    private String price;
    private String pictureId;
    private String rate;
    private String productTypeId;
    private String barId;
    private String barName;

    public Drink() {
    }

    public Drink(String id, String productName, String description, String price, String pictureId, String rate, String productTypeId, String barId, String barName) {
        this.setId(id);
        this.setProductName(productName);
        this.setDescription(description);
        this.setPrice(price);
        this.setPictureId(pictureId);
        this.setRate(rate);
        this.setProductTypeId(productTypeId);
        this.setBarId(barId);
        this.setBarName(barName);
    }


    public String getId() {
        return id;
    }

    Drink setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    Drink setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    Drink setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    Drink setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getPictureId() {
        return pictureId;
    }

    Drink setPictureId(String pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public String getRate() {
        return rate;
    }

    Drink setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    Drink setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
        return this;
    }

    public String getBarId() {
        return barId;
    }

    Drink setBarId(String barId) {
        this.barId = barId;
        return this;
    }

    public String getBarName() {
        return barName;
    }

    Drink setBarName(String barName) {
        this.barName = barName;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("productName",productName);
        bundle.putString("description",description);
        bundle.putString("price",price);
        bundle.putString("pictureId",pictureId);
        bundle.putString("rate",rate);
        bundle.putString("productTypeId",productTypeId);
        bundle.putString("barId",barId);
        bundle.putString("barName",barName);
        return bundle;
    }

    public static Drink from(Bundle bundle)
    {
        Drink drink = new Drink();
        return drink.setId(bundle.getString("id"))
                .setProductName(bundle.getString("productName"))
                .setDescription(bundle.getString("description"))
                .setPrice(bundle.getString("price"))
                .setPictureId(bundle.getString("pictureId"))
                .setRate(bundle.getString("rate"))
                .setProductTypeId(bundle.getString("productTypeId"))
                .setBarId(bundle.getString("barId"))
                .setBarName(bundle.getString("barName"));
    }
    public static Drink from(JSONObject jsonSource)
    {
        Drink drink = new Drink();
        try
        {
            drink.setId(jsonSource.getString("id"))
                    .setProductName(jsonSource.getString("productName"))
                    .setDescription(jsonSource.getString("description"))
                    .setPrice(jsonSource.getString("price"))
                    .setPictureId(jsonSource.getString("pictureId"))
                    .setRate(jsonSource.getString("rate"))
                    .setProductTypeId(jsonSource.getString("productTypeId"))
                    .setBarId(jsonSource.getString("barId"))
                    .setBarName(jsonSource.getString("barName"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        return drink;
    }
    public static List<Drink> from(JSONArray jsonSources)
    {
        List<Drink> sources = new ArrayList<>();
        for(int i = 0; i<jsonSources.length(); i++)
        {
            try
            {
                sources.add(Drink.from(jsonSources.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return sources;
    }
}
