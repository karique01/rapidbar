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
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.network.RapidBarApiService;


public class registerDialog extends AppCompatDialogFragment {
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    View viewGeneral;
    registerDialogListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_register,null);
        viewGeneral = view;

        builder.setView(view)
                .setTitle("Register")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.descartarButton();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        registerUser();
                    }
                });

        nameEditText = view.findViewById(R.id.nameEditText );
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        return builder.create();
    }

    private void registerUser(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", nameEditText.getText().toString());
            jsonObject.put("userName", emailEditText.getText().toString());
            jsonObject.put("password",  passwordEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(RapidBarApiService.REGISTER_URL)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag(getString(R.string.app_name))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        listener.acceptButton();
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (registerDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public interface registerDialogListener{
        void acceptButton();
        void descartarButton();
    }
}
