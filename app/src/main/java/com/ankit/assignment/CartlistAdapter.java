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

public class CartlistAdapter extends RecyclerView.Adapter<CartlistAdapter.MyViewHolder> {

    private ArrayList<DisplayCakes> cakes;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name_TV, price, weight;
        ImageView img;
        Button delete, move;

        public MyViewHolder(View view) {
            super(view);
            this.name_TV = view.findViewById(R.id.name);
            this.price = view.findViewById(R.id.price);
            this.weight = view.findViewById(R.id.weight);
            this.img = view.findViewById(R.id.img);
            this.delete = view.findViewById(R.id.delete_btn);
            this.move = view.findViewById(R.id.move_btn);
        }

    }

    public CartlistAdapter(ArrayList<DisplayCakes> cakes) {
        this.cakes = cakes;
    }

    @Override
    public CartlistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cartlist, parent, false);
        mcontext = parent.getContext();
        return new CartlistAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CartlistAdapter.MyViewHolder holder, int position) {
        DisplayCakes cake = cakes.get(position);
        holder.name_TV.setText(cake.getCake_name());
        holder.price.setText("Rs. "+cake.getPrice());
        holder.weight.setText(cake.getWeight());
        Picasso.with(mcontext).load("http://kekizadmin.com/uploads/catrgories/"+cake.getPictures()).fit().centerCrop().into(holder.img);
        holder.move.setOnClickListener(v -> {
            MainActivity.cartlist_cakes.remove(cake);
            CartlistActivity.selectedlist_cakes.add(cake);
            CartlistActivity.cartlist_rview.getAdapter().notifyDataSetChanged();
            CartlistActivity.selectedlist_rview.getAdapter().notifyDataSetChanged();
            MainActivity.setBadgeCount(mcontext, MainActivity.icon, MainActivity.cartlist_cakes.size() + "");
        });

        holder.delete.setOnClickListener(v -> {
            MainActivity.cartlist_cakes.remove(cake);
            CartlistActivity.cartlist_rview.getAdapter().notifyDataSetChanged();
            MainActivity.setBadgeCount(mcontext, MainActivity.icon, MainActivity.cartlist_cakes.size() + "");
        });
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }
}