package pe.edu.upc.rapidbar.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.adapters.BarsAdapter;
import pe.edu.upc.rapidbar.adapters.CreditCardsAdapter;
import pe.edu.upc.rapidbar.models.Bar;
import pe.edu.upc.rapidbar.models.CreditCard;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

import static pe.edu.upc.rapidbar.R.id.barsRecyclerView;


public class CreditCardsFragment extends Fragment implements CreditCardDialogVisa.cardDialogListener {
    RecyclerView creditCardsRecyclerView;
    CreditCardsAdapter creditCardsAdapter;
    RecyclerView.LayoutManager creditCardsLayoutManager;
    List<CreditCard> creditCards;
    CreditCardDialogVisa creditCardDialogVisa;
    View viewGeneral;


    public CreditCardsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_cards, container, false);
        viewGeneral = view;

        //RecyclerView Setup
        creditCardsRecyclerView = (RecyclerView) view.findViewById(R.id.creditCardRecyclerView);
        creditCards = new ArrayList<>();
        updateData();
        creditCardsAdapter = new CreditCardsAdapter(creditCards);
        creditCardsLayoutManager = new LinearLayoutManager(view.getContext());

        creditCardsRecyclerView.setAdapter(creditCardsAdapter);
        creditCardsRecyclerView.setLayoutManager(creditCardsLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void openDialog(){
        creditCardDialogVisa = new CreditCardDialogVisa();
        //para el callback
        creditCardDialogVisa.setTargetFragment(this,1);
        creditCardDialogVisa.show(getFragmentManager(), "Credit card");
    }
    private void updateData() {
        //TODO: Update Sources from backend
        UserLogin userLogin = SharedPreferencesAccess.LoadUserLogin(viewGeneral.getContext());
        if (userLogin == null) return;

        AndroidNetworking.get(RapidBarApiService.CREDIT_CARDS_URL+userLogin.getId())
                .setPriority(Priority.HIGH.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            creditCards = CreditCard.from(response);
                            creditCardsAdapter.setCreditCards(creditCards);
                            creditCardsAdapter.notifyDataSetChanged();
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
        updateData();
    }
}


















































