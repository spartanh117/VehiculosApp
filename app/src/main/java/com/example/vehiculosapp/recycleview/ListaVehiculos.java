package com.example.vehiculosapp.recycleview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiculosapp.BD.VehiculoSQLiteHelper;
import com.example.vehiculosapp.R;
import com.example.vehiculosapp.modelo.Vehiculo;
import com.example.vehiculosapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaVehiculos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaVehiculos extends Fragment {
    RecyclerView reyclerViewUser;
    RecycleViewAdapter mAdapter;
    List<Vehiculo> veiculoModelList;

    public ListaVehiculos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListaVehiculos newInstance(String param1, String param2) {
        ListaVehiculos fragment = new ListaVehiculos();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_vehiculos, container, false);

        veiculoModelList = new ArrayList<Vehiculo>();

        VehiculoSQLiteHelper conexion = new VehiculoSQLiteHelper(getContext(), "BDVehiculo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilities.TABLA_VEHICULO,null);

        while (cursor.moveToNext()){
            Vehiculo v = new Vehiculo(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            veiculoModelList.add(v);
        }

        reyclerViewUser = (RecyclerView) view.findViewById(R.id.reyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        reyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(getContext()));

        // specify an adapter with the list to show
        mAdapter = new RecycleViewAdapter(veiculoModelList);
        reyclerViewUser.setAdapter(mAdapter);

        return view;
    }

}