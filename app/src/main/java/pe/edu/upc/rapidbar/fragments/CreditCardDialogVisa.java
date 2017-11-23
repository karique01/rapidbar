package pe.edu.upc.rapidbar.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.braintreepayments.cardform.view.CardEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.CreditCard;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


public class CreditCardDialogVisa extends AppCompatDialogFragment {
    EditText monthExpirationEditText;
    EditText yearExpirationEditText;
    CardEditText cardForm;
    View viewGeneral;
    cardDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_credit_card_visa,null);
        viewGeneral = view;

        builder.setView(view)
                .setTitle("Credit card")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addCreditCard();
                        listener.acceptButton();
                    }
                });

        monthExpirationEditText = view.findViewById(R.id.monthExpirationEditText );
        yearExpirationEditText = view.findViewById(R.id.yearExpirationEditText);
        cardForm = view.findViewById(R.id.cardEditText);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public interface cardDialogListener{
        void acceptButton();
    }
    private void addCreditCard(){
        String creditCardNumber = cardForm.getText().toString();
        String datecrude = monthExpirationEditText.getText().toString()+"/01/"+yearExpirationEditText.getText().toString();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = new Date();
        try {
            startDate = df.parse(datecrude);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //agrego creditcard al backend
        final JSONObject jsonObject = new JSONObject();
        UserLogin userLogin = SharedPreferencesAccess.LoadUserLogin(viewGeneral.getContext());
        if (userLogin == null) return;

        try {
            jsonObject.put("number", creditCardNumber);
            jsonObject.put("expirationDate",  df.format(startDate));
            jsonObject.put("idCustomer", userLogin.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(RapidBarApiService.CREDIT_CARDS_URL)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag(getString(R.string.app_name))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
}
