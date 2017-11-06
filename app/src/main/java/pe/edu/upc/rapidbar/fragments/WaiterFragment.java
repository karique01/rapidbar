package pe.edu.upc.rapidbar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.upc.rapidbar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaiterFragment extends Fragment {


    public WaiterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiter, container, false);
    }

}
