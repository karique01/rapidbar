package pe.edu.upc.rapidbar.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.helpers.Constants;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;
import pe.edu.upc.rapidbar.network.RapidBarApiService;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView userNameAutoCompleteTextView;
    EditText passwordEditText;
    Button loginButton;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
        mContext = getApplicationContext();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String user = userNameAutoCompleteTextView.getText().toString();
                String password = passwordEditText.getText().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userName", user);
                    jsonObject.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AndroidNetworking.post(RapidBarApiService.LOGIN_URL)
                        .addJSONObjectBody(jsonObject) // posting json
                        .setTag(getString(R.string.app_name))
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    UserLogin userLogin = new UserLogin();
                                    userLogin.setId(response.getString("id"));
                                    userLogin.setName(response.getString("name"));
                                    userLogin.setUserName(response.getString("userName"));
                                    userLogin.setUserType(response.getString("userType"));
                                    redirectToUserInterface(userLogin);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    String error = "User inavalido";
                                    Toast.makeText(view.getContext(),error,Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                String error = "User inavalido";
                                Toast.makeText(view.getContext(),error,Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        //valido si ya hay una cuenta previamente logueada
        UserLogin userLogin = SharedPreferencesAccess.LoadUserLogin(mContext);
        if (userLogin != null){
            redirectToUserInterface(userLogin);
        }
    }

    void redirectToUserInterface(UserLogin userLogin){
        Intent intent;
        SharedPreferencesAccess.SaveUserLogin(mContext,userLogin);
        switch (userLogin.getUserType()){
            case Constants.USER_TYPE_CUSTOMER://cliente
                intent =new Intent(mContext,MainActivity.class);
                startActivity(intent);
                break;
            case Constants.USER_TYPE_EMPLOYEE://empleado
                intent =new Intent(mContext,MainEmployeeActivity.class);
                startActivity(intent);
                break;
            case Constants.USER_TYPE_MANAGER://administrador
                intent =new Intent(mContext,OrderDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        passwordEditText.setText("");
        userNameAutoCompleteTextView.setText("");
    }
}

























