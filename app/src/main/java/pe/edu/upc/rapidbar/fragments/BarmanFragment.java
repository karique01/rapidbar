package pe.edu.upc.rapidbar.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import pe.edu.upc.rapidbar.adapters.OrderBarmanAdapter;
import pe.edu.upc.rapidbar.adapters.OrderChefAdapter;
import pe.edu.upc.rapidbar.helpers.BarmanRecyclerTouchHelper;
import pe.edu.upc.rapidbar.helpers.ChefRecyclerTouchHelper;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarmanFragment extends Fragment  implements BarmanRecyclerTouchHelper.BarmanRecyclerTouchHelperListener {

    RecyclerView ordersBarmanRecyclerView;
    OrderBarmanAdapter ordersBarmanAdapter;
    RecyclerView.LayoutManager ordersBarmanLayoutManager;
    List<Order>orders;


    public BarmanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        updateData();
        View view = inflater.inflate(R.layout.fragment_barman, container, false);

        //RecyclerView Setup
        ordersBarmanRecyclerView = (RecyclerView) view.findViewById(R.id.barmanRecyclerView);
        orders = new ArrayList<>();

        ordersBarmanAdapter = new OrderBarmanAdapter(orders);

        ordersBarmanLayoutManager = new LinearLayoutManager(view.getContext());
        ordersBarmanRecyclerView.setLayoutManager(ordersBarmanLayoutManager);

        ordersBarmanRecyclerView.setAdapter(ordersBarmanAdapter);

        ordersBarmanRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ordersBarmanRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new BarmanRecyclerTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(ordersBarmanRecyclerView);

        return view;

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof OrderBarmanAdapter.OrderBarmanViewHolder) {
            // get the removed item name to display it in snack bar
            String name = orders.get(viewHolder.getAdapterPosition()).getId();

            // backup of removed item for undo purpose
            final Order deletedItem = orders.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            ordersBarmanAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(this.getView(), "Orden N: " + name + " completada!", Snackbar.LENGTH_LONG);
            snackbar.setAction("DESHACER", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    ordersBarmanAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }

    private void updateData() {
        AndroidNetworking.get(RapidBarApiService.ORDERS_URL)
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            orders = Order.from(response);
                            ordersBarmanAdapter.setOrders(orders);
                            ordersBarmanAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}




