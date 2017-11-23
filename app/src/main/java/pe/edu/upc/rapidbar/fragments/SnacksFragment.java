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
import pe.edu.upc.rapidbar.adapters.DrinksAdapter;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


/**
 * A simple {@link Fragment} subclass.
 */
public class SnacksFragment extends Fragment {
    RecyclerView snacksRecyclerView;
    DrinksAdapter snacksAdapter;
    RecyclerView.LayoutManager snacksLayoutManager;
    List<Drink> snacks;

    public SnacksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        //RecyclerView Setup
        snacksRecyclerView = (RecyclerView) view.findViewById(R.id.snacksRecyclerView);
        snacks = new ArrayList<>();
        updateData();
        snacksAdapter = new DrinksAdapter(snacks);
        snacksLayoutManager = new LinearLayoutManager(view.getContext());

        snacksRecyclerView.setAdapter(snacksAdapter);
        snacksRecyclerView.setLayoutManager(snacksLayoutManager);

        return view;
    }

    private void updateData() {
        //TODO: Update Sources from backend
        AndroidNetworking.get(RapidBarApiService.PRODUCTS_SNACKS_URL)
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            snacks = Drink.from(response);
                            snacksAdapter.setDrinks(snacks);
                            snacksAdapter.notifyDataSetChanged();
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
