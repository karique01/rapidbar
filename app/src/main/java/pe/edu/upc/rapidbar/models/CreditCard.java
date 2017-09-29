package pe.edu.upc.rapidbar.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreditCard {
    private String id;
    private String number;
    private String expirationDate;

    public CreditCard() {
    }

    public CreditCard(String id, String number, String expirationDate) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public CreditCard setId(String id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public CreditCard setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    public String getExpirationDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(date);
        df = new SimpleDateFormat("MM");
        String month = df.format(date);
        return year+"/"+month;
    }

    public CreditCard setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("number",number);
        bundle.putString("expirationDate",expirationDate);
        return bundle;
    }

    public static CreditCard from(Bundle bundle)
    {
        CreditCard creditCard = new CreditCard();
        return creditCard.setId(bundle.getString("id"))
                .setNumber(bundle.getString("number"))
                .setExpirationDate(bundle.getString("expirationDate"));
    }
    public static CreditCard from(JSONObject jsonSource)
    {
        CreditCard creditCard = new CreditCard();
        try
        {
            creditCard.setId(jsonSource.getString("id"))
                    .setNumber(jsonSource.getString("number"))
                    .setExpirationDate(jsonSource.getString("expirationDate"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        return creditCard;
    }
    public static List<CreditCard> from(JSONArray jsonSources)
    {
        List<CreditCard> creditCards = new ArrayList<>();
        for(int i = 0; i<jsonSources.length(); i++)
        {
            try
            {
                creditCards.add(CreditCard.from(jsonSources.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return creditCards;
    }
}
