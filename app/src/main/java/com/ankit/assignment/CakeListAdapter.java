package com.ankit.assignment;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CakeListAdapter extends RecyclerView.Adapter<CakeListAdapter.MyViewHolder> {

    private ArrayList<DisplayCakes> cakes;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name_TV, price, weight;
        ImageView img;
        Button add_to_cart_btn;

        public MyViewHolder(View view) {
            super(view);
            this.name_TV = view.findViewById(R.id.name);
            this.price = view.findViewById(R.id.price);
            this.weight = view.findViewById(R.id.weight);
            this.img = view.findViewById(R.id.img);
            this.add_to_cart_btn = view.findViewById(R.id.add_to_cart_btn);
        }

    }

    public CakeListAdapter(ArrayList<DisplayCakes> cakes) {
        this.cakes = cakes;
    }

    @Override
    public CakeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cakeslist, parent, false);
        mcontext = parent.getContext();
        return new CakeListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CakeListAdapter.MyViewHolder holder, int position) {
        DisplayCakes cake = cakes.get(position);
        holder.name_TV.setText(cake.getCake_name());
        holder.price.setText("Rs. "+cake.getPrice());
        holder.weight.setText(cake.getWeight());
        Picasso.with(mcontext).load("http://kekizadmin.com/uploads/catrgories/"+cake.getPictures()).fit().centerCrop().into(holder.img);
        holder.add_to_cart_btn.setOnClickListener(v -> {
            MainActivity.cartlist_cakes.add(cake);
            MainActivity.setBadgeCount(mcontext, MainActivity.icon, MainActivity.cartlist_cakes.size() + "");
        });
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }
}
