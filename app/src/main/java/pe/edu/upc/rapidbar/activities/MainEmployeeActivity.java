package pe.edu.upc.rapidbar.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.fragments.BarmanFragment;
import pe.edu.upc.rapidbar.fragments.ChefFragment;
import pe.edu.upc.rapidbar.fragments.StockFragment;
import pe.edu.upc.rapidbar.fragments.WaiterFragment;

public class MainEmployeeActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return navigateAccordingTo(item.getItemId());

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    Fragment changeFragmenById(int id){

        switch (id) {

             case R.id.navigation_barman:

                return new BarmanFragment();
            case R.id.navigation_chef:

                return  new ChefFragment();
            case R.id.navigation_waiter:

                return new WaiterFragment();

            case R.id.navigation_stock:

                return new StockFragment();
        }
        return null;

    }

    boolean navigateAccordingTo(int id){

        try{
            getSupportFragmentManager()
            .beginTransaction()
                    .replace(R.id.content,changeFragmenById(id))
                    .commit();
            return true;

        }catch(Exception e){
            return false;

        }


    }

}
