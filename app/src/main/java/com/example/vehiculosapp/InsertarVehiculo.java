package com.example.vehiculosapp;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.vehiculosapp.BD.VehiculoSQLiteHelper;

import  com.example.vehiculosapp.utilities.Utilities;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarVehiculo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarVehiculo extends Fragment {
    private EditText marca;
    private EditText modelo;
    private EditText matricula;
    private Button btnInsertar;


    public InsertarVehiculo() {
        // Required empty public constructor
    }

    public static InsertarVehiculo newInstance(String param1, String param2) {
        InsertarVehiculo fragment = new InsertarVehiculo();
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
        View view = inflater.inflate(R.layout.fragment_insertar_vehiculo, container, false);
        setUpView(view);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarVehiculo();
            }
        });
        return view;
    }

    private void insertarVehiculo() {
        try {
            VehiculoSQLiteHelper conexion = new VehiculoSQLiteHelper(getContext(),"BDVehiculo",null,1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilities.CAMPO_MATRICULA,matricula.getText().toString());
            values.put(Utilities.CAMPO_MARCA,marca.getText().toString());
            values.put(Utilities.CAMPO_MODELO,modelo.getText().toString());

            db.insert(Utilities.TABLA_VEHICULO,null,values);
            db.close();

            Toast.makeText(getContext(),"se insertado corectamente",Toast.LENGTH_LONG).show();
        }
        catch (SQLException e){
            Toast.makeText(getContext(),"a ocurrido un error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void setUpView(View view) {
        marca = (EditText)view.findViewById(R.id.edtiTextMarca);
        modelo = (EditText)view.findViewById(R.id.edtiTextModelo);
        matricula = (EditText)view.findViewById(R.id.edtiTextMatricula);
        btnInsertar = (Button)view.findViewById(R.id.btnInsertar);
    }
}