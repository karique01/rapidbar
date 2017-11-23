package pe.edu.upc.rapidbar.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.adapters.BuyProductAdapter;
import pe.edu.upc.rapidbar.adapters.DrinksAdapter;
import pe.edu.upc.rapidbar.fragments.ConfirmOrderDialog;
import pe.edu.upc.rapidbar.fragments.CreditCardDialogVisa;
import pe.edu.upc.rapidbar.models.Bar;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

public class BarActivity extends AppCompatActivity implements ConfirmOrderDialog.confirmDialogListener {

    RecyclerView drinksRecyclerView;
    BuyProductAdapter drinksAdapter;
    RecyclerView.LayoutManager drinksLayoutManager;
    ANImageView barBackgroundANImageView;
    ConfirmOrderDialog confirmOrderDialog;
    List<Drink> drinks;
    Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barBackgroundANImageView = findViewById(R.id.barBackgroundANImageView);
        setBarDetails();

        drinksRecyclerView = (RecyclerView) findViewById(R.id.drinksRecyclerView);
        drinks = new ArrayList<>();
        updateData();
        drinksAdapter = new BuyProductAdapter(drinks);
        drinksLayoutManager = new LinearLayoutManager(getApplicationContext());

        drinksRecyclerView.setAdapter(drinksAdapter);
        drinksRecyclerView.setLayoutManager(drinksLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog del carrito
                openDialog();
            }
        });
    }

    public void openDialog(){
        confirmOrderDialog = new ConfirmOrderDialog();
        //para el callback
        confirmOrderDialog.show(getSupportFragmentManager(), "Confirm");
    }

    private void setBarDetails(){
        bar = Bar.from(getIntent().getExtras());
        setTitle(bar.getName());
        barBackgroundANImageView.setDefaultImageResId(R.drawable.background_bar);
        barBackgroundANImageView.setErrorImageResId(R.drawable.background_bar);
        barBackgroundANImageView.setImageUrl(bar.getPictureId());
    }
    private void updateData() {
        AndroidNetworking.get(RapidBarApiService.BAR_PRODUCTS_BY_ID(bar.getId()))
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

    @Override
    public void acceptButton() {
        //se agrego el pedido
        Toast.makeText(getApplicationContext(),"Se hizo el pedido",Toast.LENGTH_LONG).show();
    }

    @Override
    public void descartarButton() {
        Order.clearProductsFromCart();
        Toast.makeText(getApplicationContext(),"Se eliminaron los elementos del carrito",Toast.LENGTH_LONG).show();
    }
}
