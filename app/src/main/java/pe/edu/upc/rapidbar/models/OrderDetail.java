package pe.edu.upc.rapidbar.models;


import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {
    private String quantity;
    private String id;
    private String productName;
    private String description;
    private String price;
    private String pictureId;
    private String rate;
    private String productTypeId;
    private String barId;
    private String barName;

    public OrderDetail() {
    }

    public OrderDetail(String quantity, String id, String productName, String description, String price, String pictureId, String rate, String productTypeId, String barId, String barName) {
        this.quantity = quantity;
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.pictureId = pictureId;
        this.rate = rate;
        this.productTypeId = productTypeId;
        this.barId = barId;
        this.barName = barName;
    }

    public String getQuantity() {
        return quantity;
    }

    public OrderDetail setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
    
    public String getId() {
        return id;
    }

    OrderDetail setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    OrderDetail setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    OrderDetail setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    OrderDetail setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getPictureId() {
        return pictureId;
    }

    OrderDetail setPictureId(String pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public String getRate() {
        return rate;
    }

    OrderDetail setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    OrderDetail setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
        return this;
    }

    public String getBarId() {
        return barId;
    }

    OrderDetail setBarId(String barId) {
        this.barId = barId;
        return this;
    }

    public String getBarName() {
        return barName;
    }

    OrderDetail setBarName(String barName) {
        this.barName = barName;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("quantity",quantity);
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

    public static OrderDetail from(Bundle bundle)
    {
        OrderDetail OrderDetail = new OrderDetail();
        return OrderDetail.setId(bundle.getString("id"))
                .setQuantity(bundle.getString("quantity"))
                .setProductName(bundle.getString("productName"))
                .setDescription(bundle.getString("description"))
                .setPrice(bundle.getString("price"))
                .setPictureId(bundle.getString("pictureId"))
                .setRate(bundle.getString("rate"))
                .setProductTypeId(bundle.getString("productTypeId"))
                .setBarId(bundle.getString("barId"))
                .setBarName(bundle.getString("barName"));
    }
    public static OrderDetail from(JSONObject jsonSource)
    {
        OrderDetail OrderDetail = new OrderDetail();
        try
        {
            OrderDetail.setId(jsonSource.getString("id"))
                    .setQuantity(jsonSource.getString("quantity"))
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
        return OrderDetail;
    }
    public static List<OrderDetail> from(JSONArray jsonSources)
    {
        List<OrderDetail> sources = new ArrayList<>();
        for(int i = 0; i<jsonSources.length(); i++)
        {
            try
            {
                sources.add(OrderDetail.from(jsonSources.getJSONObject(i)));
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
