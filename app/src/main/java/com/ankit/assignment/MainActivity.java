package com.ankit.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    ArrayList<DisplayCakes> cakes = new ArrayList<>();
    public static ArrayList<DisplayCakes> cartlist_cakes = new ArrayList<>();
    public static LayerDrawable icon;
    RecyclerView recyclerView;
    CakeListAdapter itemsAdapter;
    CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Cake List");

        itemsAdapter = new CakeListAdapter(cakes);
        recyclerView = findViewById(R.id.rview);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemsAdapter);
        getCakeList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem itemCart = menu.findItem(R.id.action_cart);
        icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(this, icon, cartlist_cakes.size() + "");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartlistActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void getCakeList(){
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(RetrofitRestRepository.getRetrofit().getCakesLists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCakesListResponse,this::handleErrorCakesListResponse));
    }

    protected void handleCakesListResponse(CakesListResponse cakesListResponse){
        if (cakesListResponse.getStatus().equals("success")) {
            cakes.clear();
            for(int i = 0; i<cakesListResponse.getData().size(); i++){
                for(int j=0; j<cakesListResponse.getData().get(i).getW_l_p().size(); j++) {
                    DisplayCakes cake = new DisplayCakes();
                    cake.setId(cakesListResponse.getData().get(i).getId());
                    cake.setCake_name(cakesListResponse.getData().get(i).getCake_name());
                    cake.setWeight(cakesListResponse.getData().get(i).getW_l_p().get(j).getWeight());
                    cake.setPrice(cakesListResponse.getData().get(i).getW_l_p().get(j).getPrice());
                    String image = cakesListResponse.getData().get(i).getW_l_p().get(0).getPictures();
                    cake.setPictures(image.substring(image.lastIndexOf(":")+2, image.indexOf("}")-1));
                    cakes.add(cake);
                }
            }
            recyclerView.getAdapter().notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_LONG).show();
        }
    }

    protected void handleErrorCakesListResponse(Throwable error){
        Log.e("asdf lljj55", error.getLocalizedMessage());
        Toast.makeText(this, "Some error found", Toast.LENGTH_LONG).show();

    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

}
