package pe.edu.upc.rapidbar.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.braintreepayments.cardform.view.CardEditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

public class ConfirmOrderDialog extends AppCompatDialogFragment {

    TextView userIdConfirmOrder;
    TextView totalAmountConfirmOrder;
    View viewGeneral;
    ConfirmOrderDialog.confirmDialogListener listener;
    float total;
    String strDate;
    String state;
    UserLogin userLogin;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ConfirmOrderDialog.confirmDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.confirm_order,null);
        viewGeneral = view;

        total = Order.totalAmountFromCart();
        strDate = getDate();
        state = "false";
        userLogin = SharedPreferencesAccess.LoadUserLogin(getContext());

        builder.setView(view)
                .setTitle("Credit card")
                .setNegativeButton("Descartar orden", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.descartarButton();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmOrder();
                        listener.acceptButton();
                    }
                });

        userIdConfirmOrder = view.findViewById(R.id.userIdConfirmOrder );
        totalAmountConfirmOrder = view.findViewById(R.id.totalAmountConfirmOrder);
        userIdConfirmOrder.setText(userLogin.getUserName());
        totalAmountConfirmOrder.setText(String.valueOf(total));
        return builder.create();
    }

    public void confirmOrder(){
        //armo el json
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("totalAmount", String.valueOf(total));
            jsonObject.put("date",  strDate);
            jsonObject.put("state", state);
            jsonObject.put("userId", userLogin.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //lo envio al backend
        AndroidNetworking.post(RapidBarApiService.ORDERS_URL)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag(getString(R.string.app_name))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //envio los orderByProduct
                        try {
                            String orderId = response.getString("id");
                            sendOrdersByProduct(orderId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

    private void sendOrdersByProduct(String orderId){
        List<Drink> products = Order.drinksCart;
        for (int i =0; i< products.size(); i++){
            Drink product = products.get(i);
            //armo el json
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId", product.getId());
                jsonObject.put("orderId",  orderId);
                jsonObject.put("quantity", Order.getQuantityOfProductsById(product.getId()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //lo envio al backend
            AndroidNetworking.post(RapidBarApiService.PRODUCTS_BY_ORDER_URL)
                    .addJSONObjectBody(jsonObject) // posting json
                    .setPriority(Priority.HIGH)
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
        Order.clearProductsFromCart();
    }

    public interface confirmDialogListener{
        void acceptButton();
        void descartarButton();
    }

    private String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(c.getTime());
    }
}
