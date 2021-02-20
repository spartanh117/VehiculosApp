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
        String vehiculo = vehiculoModelList.get(position).toString();

        holder.vehiculo.setText(vehiculo);
    }

    @Override
    public int getItemCount() {
        return vehiculoModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView vehiculo;

        public ViewHolder(View v) {
            super(v);
            vehiculo = (TextView) v.findViewById(R.id.textVehiculo);

        }
    }

}