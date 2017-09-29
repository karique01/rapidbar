package pe.edu.upc.rapidbar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.adapters.DrinksAdapter;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


public class DrinksFragment extends Fragment {
    RecyclerView drinksRecyclerView;
    DrinksAdapter drinksAdapter;
    RecyclerView.LayoutManager drinksLayoutManager;
    List<Drink> drinks;

    public DrinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);

        //RecyclerView Setup
        drinksRecyclerView = (RecyclerView) view.findViewById(R.id.drinksRecyclerView);
        drinks = new ArrayList<>();
        updateData();
        drinksAdapter = new DrinksAdapter(drinks);
        drinksLayoutManager = new LinearLayoutManager(view.getContext());

        drinksRecyclerView.setAdapter(drinksAdapter);
        drinksRecyclerView.setLayoutManager(drinksLayoutManager);

        return view;
    }

    private void updateData() {
        //TODO: Update Sources from backend
        AndroidNetworking.get(RapidBarApiService.PRODUCTS_URL)
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            drinks = Drink.from(response);
                            drinksAdapter.setDrinks(drinks);
                            drinksAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
