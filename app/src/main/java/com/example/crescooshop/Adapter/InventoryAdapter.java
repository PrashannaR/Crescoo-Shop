package com.example.crescooshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crescooshop.Constructor.addItemConstructor;
import com.example.crescooshop.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class InventoryAdapter extends FirebaseRecyclerAdapter<addItemConstructor, InventoryAdapter.myViewHolder> {


    public InventoryAdapter(@NonNull FirebaseRecyclerOptions<addItemConstructor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull addItemConstructor model) {

        holder.prodNameTV.setText(model.getProdName());
        holder.quantityTV.setText("Quantity: " + model.getQuantity());
        holder.cpTV.setText("Cost Price per Quantity: " + model.getCp());
        holder.spTV.setText("Selling Price per Quantity: " + model.getSp());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_list, parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView prodNameTV, quantityTV, cpTV, spTV;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            prodNameTV = itemView.findViewById(R.id.prodNameTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            cpTV = itemView.findViewById(R.id.cpTV);
            spTV = itemView.findViewById(R.id.spTV);


        }
    }

}


