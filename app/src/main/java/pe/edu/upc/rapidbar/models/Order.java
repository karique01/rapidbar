package pe.edu.upc.rapidbar.models;


import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private String totalAmount;
    private String date;
    private String barName;

    public Order() {
    }

    public Order(String id, String totalAmount, String date, String barName) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.date = date;
        this.barName = barName;
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public Order setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Order setDate(String date) {
        this.date = date;
        return this;
    }

    public String getBarName() {
        return barName;
    }

    public Order setBarName(String barName) {
        this.barName = barName;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("totalAmount",totalAmount);
        bundle.putString("date",date);
        bundle.putString("barName",barName);
        return bundle;
    }

    public static Order from(Bundle bundle)
    {
        Order order = new Order();
        return order.setId(bundle.getString("id"))
                .setTotalAmount(bundle.getString("totalAmount"))
                .setDate(bundle.getString("date"))
                .setBarName(bundle.getString("barName"));
    }
    public static Order from(JSONObject jsonSource)
    {
        Order order = new Order();
        try
        {
            order.setId(jsonSource.getString("id"))
                    .setTotalAmount(jsonSource.getString("totalAmount"))
                    .setDate(jsonSource.getString("date"))
                    .setBarName(jsonSource.getString("barName"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        return order;
    }
    public static List<Order> from(JSONArray jsonSources)
    {
        List<Order> orders = new ArrayList<>();
        for(int i = 0; i<jsonSources.length(); i++)
        {
            try
            {
                orders.add(Order.from(jsonSources.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return orders;
    }

    public String getDateFormated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dateObject = null;
        try {
            dateObject = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(dateObject);
        df = new SimpleDateFormat("MM");
        String month = df.format(dateObject);
        df = new SimpleDateFormat("dd");
        String day = df.format(dateObject);
        return year+"/"+month+"/"+day;
    }
}










































