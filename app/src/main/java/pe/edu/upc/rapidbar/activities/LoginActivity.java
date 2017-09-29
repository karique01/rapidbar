package pe.edu.upc.rapidbar.activities;

import android.content.Intent;
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
import pe.edu.upc.rapidbar.network.RapidBarApiService;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView userNameAutoCompleteTextView;
    EditText passwordEditText;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
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
                                    String id = response.getString("id");
                                    String name = response.getString("name");
                                    String userName = response.getString("userName");
                                    finish();
                                    Intent intent =new Intent(view.getContext(),MainActivity.class);
                                    startActivity(intent);
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
    }
}

























