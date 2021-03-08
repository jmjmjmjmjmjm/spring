package com.gg.phoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private PhoneAdapter phoneAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_phone);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        FloatingActionButton btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlusActivity.class));
            }
        });


        PhoneService phoneService = PhoneService.retrofit.create(PhoneService.class);
        Call<CMRespDto<List<Phone>>> call = phoneService.findAll();

        call.enqueue(new Callback<CMRespDto<List<Phone>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Phone>>> call, Response<CMRespDto<List<Phone>>> response) {
                CMRespDto<List<Phone>> cmRespDto = response.body();
                List<Phone> phones = cmRespDto.getData(); // 받아온데이터
                // 어댑터에게 넘기기
                phoneAdapter = new PhoneAdapter((ArrayList<Phone>) phones);
                recyclerView.setAdapter(phoneAdapter);

                Log.d(TAG, "onResponse: callback data: " + phones +phoneAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Phone>>> call, Throwable t) {

                Log.d(TAG, "onFailure: findAll() 실패" + t.getMessage());
            }
        });
    }


}