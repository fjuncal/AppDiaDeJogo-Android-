package com.projetopublicado;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscolhaTime extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    BottomNavigationView bottomNavigationView;
    SwipeRefreshLayout mySwipeRefreshLayout;

    ListView lstVwTimes;
    final String TAG = "MAIN";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_jogo);
//        recyclerView = findViewById(R.id.my_recycler_view);

        lstVwTimes = findViewById(R.id.lstVwTimes);


        mySwipeRefreshLayout = findViewById(R.id.mySwipeRefreshLayout);
        mySwipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_dark,android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_dark );
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mySwipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mySwipeRefreshLayout.setRefreshing(false);

                    }
                }, 2200);
            }
        });

        final Call<ApiResult> tarefas = TimeAPI.getInstance().listarProjetos();

        tarefas.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ArrayAdapter<Match> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_list_item_1,response.body().data.match);
                lstVwTimes.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();



            }
        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//
//                switch (menuItem.getItemId()) {
//                    case R.id.navigation_refresh: {
//
//
//                        return true;
//
//                    }
//                    case R.id.navigation_profile: {
//                        break;
//                    }
//
//                }
//
//                return false;
//            }
//
//        });
    }




}
