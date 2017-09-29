package pe.edu.upc.rapidbar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.adapters.DrinksAdapter;
import pe.edu.upc.rapidbar.adapters.OrdersDetailAdapter;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.OrderDetail;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

public class OrderDetailActivity extends AppCompatActivity {

    RecyclerView orderDetailsRecyclerView;
    OrdersDetailAdapter orderDetailsAdapter;
    RecyclerView.LayoutManager orderDetailLayoutManager;
    List<OrderDetail> orderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Order order = Order.from(getIntent().getExtras());


        orderDetailsRecyclerView = (RecyclerView) findViewById(R.id.orderDetailRecyclerView);
        orderDetails = new ArrayList<>();
        updateData(order.getId());
        orderDetailsAdapter = new OrdersDetailAdapter(orderDetails);
        orderDetailLayoutManager = new LinearLayoutManager(this);

        orderDetailsRecyclerView.setAdapter(orderDetailsAdapter);
        orderDetailsRecyclerView.setLayoutManager(orderDetailLayoutManager);
    }

    private void updateData(String idOrden) {
        //TODO: Update Sources from backend
        AndroidNetworking.get(RapidBarApiService.ORDERS_DETAILS_URL(idOrden))
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            orderDetails = OrderDetail.from(response);
                            orderDetailsAdapter.setOrderDetails(orderDetails);
                            orderDetailsAdapter.notifyDataSetChanged();
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

























































