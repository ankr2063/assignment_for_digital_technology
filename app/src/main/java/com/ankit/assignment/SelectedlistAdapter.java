package com.ankit.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SelectedlistAdapter extends RecyclerView.Adapter<SelectedlistAdapter.MyViewHolder> {

    private ArrayList<DisplayCakes> cakes;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name_TV, price, weight;
        ImageView img, move_up, move_down;
        Button delete;

        public MyViewHolder(View view) {
            super(view);
            this.name_TV = view.findViewById(R.id.name);
            this.price = view.findViewById(R.id.price);
            this.weight = view.findViewById(R.id.weight);
            this.img = view.findViewById(R.id.img);
            this.delete = view.findViewById(R.id.delete_btn);
            this.move_down = view.findViewById(R.id.move_down);
            this.move_up = view.findViewById(R.id.move_up);
        }

    }

    public SelectedlistAdapter(ArrayList<DisplayCakes> cakes) {
        this.cakes = cakes;
    }

    @Override
    public SelectedlistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_selectedlist, parent, false);
        mcontext = parent.getContext();
        return new SelectedlistAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectedlistAdapter.MyViewHolder holder, int position) {
        DisplayCakes cake = cakes.get(position);
        holder.name_TV.setText(cake.getCake_name());
        holder.price.setText("Rs. "+cake.getPrice());
        holder.weight.setText(cake.getWeight());
        Picasso.with(mcontext).load("http://kekizadmin.com/uploads/catrgories/"+cake.getPictures()).fit().centerCrop().into(holder.img);
        holder.delete.setOnClickListener(v -> {
            cakes.remove(cake);
            CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
        });
        if(position == 0){
            holder.move_up.setVisibility(View.GONE);
            holder.move_down.setVisibility(View.VISIBLE);
            holder.move_down.setOnClickListener(v -> {
                cakes.set(position, cakes.get(position+1));
                cakes.set(position+1, cake);
                CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
            });
        }else if(position == cakes.size()-1){
            holder.move_up.setVisibility(View.VISIBLE);
            holder.move_down.setVisibility(View.GONE);
            holder.move_up.setOnClickListener(v -> {
                cakes.set(position, cakes.get(position-1));
                cakes.set(position-1, cake);
                CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
            });
        }else {
            holder.move_up.setVisibility(View.VISIBLE);
            holder.move_down.setVisibility(View.VISIBLE);
            holder.move_up.setOnClickListener(v -> {
                cakes.set(position, cakes.get(position-1));
                cakes.set(position-1, cake);
                CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
            });
            holder.move_down.setOnClickListener(v -> {
                cakes.set(position, cakes.get(position+1));
                cakes.set(position+1, cake);
                CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }
}