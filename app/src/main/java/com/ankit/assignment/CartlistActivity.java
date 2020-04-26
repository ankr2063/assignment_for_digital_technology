package com.ankit.assignment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartlistActivity extends AppCompatActivity {

    public static ArrayList<DisplayCakes> selectedlist_cakes = new ArrayList<>();
    public static RecyclerView cartlist_rview, selectedlist_rview;
    CartlistAdapter cartitemsAdapter;
    SelectedlistAdapter selecteditemsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);

        cartitemsAdapter = new CartlistAdapter(MainActivity.cartlist_cakes);
        cartlist_rview = findViewById(R.id.cartlist_rview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartlist_rview.setLayoutManager(mLayoutManager);
        cartlist_rview.setItemAnimator(new DefaultItemAnimator());
        cartlist_rview.setAdapter(cartitemsAdapter);

        selecteditemsAdapter = new SelectedlistAdapter(selectedlist_cakes);
        selectedlist_rview = findViewById(R.id.selectedlist_rview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        selectedlist_rview.setLayoutManager(layoutManager);
        selectedlist_rview.setItemAnimator(new DefaultItemAnimator());
        selectedlist_rview.setAdapter(selecteditemsAdapter);
    }
}
