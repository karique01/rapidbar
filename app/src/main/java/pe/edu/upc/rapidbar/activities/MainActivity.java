package pe.edu.upc.rapidbar.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.fragments.BarsFragment;
import pe.edu.upc.rapidbar.fragments.ConsumptionRecordsFragment;
import pe.edu.upc.rapidbar.fragments.CreditCardsFragment;
import pe.edu.upc.rapidbar.fragments.DrinksFragment;
import pe.edu.upc.rapidbar.fragments.FavoritesFragment;
import pe.edu.upc.rapidbar.fragments.SnacksFragment;
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.SharedPreferencesAccess;
import pe.edu.upc.rapidbar.models.UserLogin;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mContext = getApplicationContext();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigateAccordingTo(R.id.nav_bars);
        cambiarDatosAccesoHeader();
    }

    private void cambiarDatosAccesoHeader(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        UserLogin userLogin = SharedPreferencesAccess.LoadUserLogin(mContext);
        if (userLogin == null) return;
        ((TextView) headerView.findViewById(R.id.nameHeader)).setText(userLogin.getName());
        ((TextView) headerView.findViewById(R.id.textView)).setText(userLogin.getUserName());
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Order.clearProductsFromCart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean navigate = navigateAccordingTo(id);
        if (id == R.id.nav_log_out){
            SharedPreferencesAccess.DeleteUserLogin(mContext);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return navigate;
    }

    private Fragment getFragmentFor (int id)
    {
        if (id == R.id.nav_bars) {
            return new BarsFragment();
        } else if (id == R.id.nav_drinks) {
            return new DrinksFragment();
        } else if (id == R.id.nav_credit_cards) {
            return new CreditCardsFragment();
        } else if (id == R.id.nav_consumption_records) {
            return new ConsumptionRecordsFragment();
        }/* else if (id == R.id.nav_favorites) {
            return new FavoritesFragment();
        }*/ else if (id == R.id.nav_snacks) {
            return new SnacksFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id)
    {
        try
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame,getFragmentFor(id))
                    .commit();
            return true;
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
