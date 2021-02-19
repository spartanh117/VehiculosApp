package com.example.vehiculosapp.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiculosapp.R;
import com.example.vehiculosapp.modelo.Vehiculo;

import java.util.List;

public class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Vehiculo> vehiculoModelList;

    public RecycleViewAdapter(List<Vehiculo> vehiculoModelList) {
        this.vehiculoModelList = vehiculoModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehiculo_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String modelo = vehiculoModelList.get(position).getModelo();
        String matricula = vehiculoModelList.get(position).getMatricula();
        String marca = vehiculoModelList.get(position).getMarca();
        holder.modelo.setText(modelo);
        holder.matricula.setText(matricula);
        holder.marca.setText(marca);
    }

    @Override
    public int getItemCount() {
        return vehiculoModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView marca;
        private TextView modelo;
        private TextView matricula;

        public ViewHolder(View v) {
            super(v);
            marca = (TextView) v.findViewById(R.id.txtViewMarca);
            modelo = (TextView) v.findViewById(R.id.txtViewModelo);
            matricula = (TextView) v.findViewById(R.id.txtViewMatricula);
        }
    }

}