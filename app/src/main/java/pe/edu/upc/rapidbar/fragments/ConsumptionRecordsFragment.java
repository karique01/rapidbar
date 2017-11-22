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
import pe.edu.upc.rapidbar.adapters.CreditCardsAdapter;
import pe.edu.upc.rapidbar.adapters.OrdersAdapter;
import pe.edu.upc.rapidbar.models.CreditCard;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


public class ConsumptionRecordsFragment extends Fragment {
    RecyclerView ordersRecyclerView;
    OrdersAdapter ordersAdapter;
    RecyclerView.LayoutManager ordersLayoutManager;
    List<Order> orders;
    View viewGeneral;

    public ConsumptionRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consumption_records, container, false);
        viewGeneral = view;
        //RecyclerView Setup
        ordersRecyclerView = (RecyclerView) view.findViewById(R.id.consumptionRecyclerView);
        orders = new ArrayList<>();
        updateData();
        ordersAdapter = new OrdersAdapter(orders);
        ordersLayoutManager = new LinearLayoutManager(view.getContext());

        ordersRecyclerView.setAdapter(ordersAdapter);
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);

        return view;
    }

    private void updateData() {
        //TODO: Update Sources from backend
        UserLogin userLogin = SharedPreferencesAccess.LoadUserLogin(viewGeneral.getContext());
        if (userLogin == null) return;

        AndroidNetworking.get(RapidBarApiService.ORDERS_URL+userLogin.getId())
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            orders = Order.from(response);
                            ordersAdapter.setOrders(orders);
                            ordersAdapter.notifyDataSetChanged();
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
