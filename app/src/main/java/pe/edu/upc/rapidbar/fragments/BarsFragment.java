package pe.edu.upc.rapidbar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.adapters.BarsAdapter;
import pe.edu.upc.rapidbar.adapters.DrinksAdapter;
import pe.edu.upc.rapidbar.models.Bar;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


public class BarsFragment extends Fragment {
    RecyclerView barsRecyclerView;
    BarsAdapter barsAdapter;
    RecyclerView.LayoutManager barsLayoutManager;
    List<Bar> bars;

    public BarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bars, container, false);

        //RecyclerView Setup
        barsRecyclerView = (RecyclerView) view.findViewById(R.id.barsRecyclerView);
        bars = new ArrayList<>();
        updateData();
        barsAdapter = new BarsAdapter(bars);
        barsLayoutManager = new LinearLayoutManager(view.getContext());

        barsRecyclerView.setAdapter(barsAdapter);
        barsRecyclerView.setLayoutManager(barsLayoutManager);

        return view;
    }

    private void updateData() {
        //TODO: Update Sources from backend
        AndroidNetworking.get(RapidBarApiService.BARS_URL)
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            bars = Bar.from(response);
                            barsAdapter.setBars(bars);
                            barsAdapter.notifyDataSetChanged();
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
