package com.example.vehiculosapp.recycleview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiculosapp.BD.VehiculoSQLiteHelper;
import com.example.vehiculosapp.R;
import com.example.vehiculosapp.modelo.Vehiculo;
import com.example.vehiculosapp.utilities.Utilities;

import java.util.List;

public class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Vehiculo> vehiculoModelList;
    private Context context;

    public RecycleViewAdapter(List<Vehiculo> vehiculoModelList) {
        this.vehiculoModelList = vehiculoModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_update_delete, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String modelo = vehiculoModelList.get(position).getModelo();
        String matricula = vehiculoModelList.get(position).getMatricula();
        String marca = vehiculoModelList.get(position).getMarca();
        Integer id = vehiculoModelList.get(position).getId();

        holder.modelo.setText(modelo);
        holder.marca.setText(marca);
        holder.matricula.setText(matricula);

        VehiculoSQLiteHelper conexion = new VehiculoSQLiteHelper(context, "BDVehiculo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();

                db.delete(Utilities.TABLA_VEHICULO,"matricula = '"+holder.id+"'",null);
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(Utilities.CAMPO_MATRICULA,holder.matricula.getText().toString());
                values.put(Utilities.CAMPO_MARCA,holder.marca.getText().toString());
                values.put(Utilities.CAMPO_MODELO,holder.modelo.getText().toString());

                db.update(Utilities.TABLA_VEHICULO,values,"= '"+holder.id+"'",null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehiculoModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText marca;
        private EditText modelo;
        private EditText matricula;
        private Button btnUpdate;
        private Button btnDelete;
        private Integer id;

        private VehiculoSQLiteHelper conexion;

        public ViewHolder(View v) {
            super(v);
            marca = (EditText) v.findViewById(R.id.edtiTextMarcaUD);
            modelo = (EditText) v.findViewById(R.id.edtiTextModeloUD);
            matricula = (EditText) v.findViewById(R.id.edtiTextMatriculaUD);
            btnUpdate = (Button) v.findViewById(R.id.btnUpdate);
            btnDelete = (Button) v.findViewById(R.id.btnDelete);

        }
    }
}