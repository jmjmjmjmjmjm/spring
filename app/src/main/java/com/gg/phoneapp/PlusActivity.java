package com.gg.phoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlusActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "plus";
    private EditText name;
    private EditText tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plus);

        findViewById(R.id.close).setOnClickListener(this);
        name = findViewById(R.id.edit_name);
        tel = findViewById(R.id.edit_tel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                this.finish();
                break;
            default:
                Log.d(TAG, "onFailure: 클릭성공" );
                Phone phone = new Phone();

                phone.setName(name.getText().toString());
                phone.setTel(tel.getText().toString());


                PhoneService phoneService = PhoneService.retrofit.create(PhoneService.class);
                Call<CMRespDto<List<Phone>>> call = phoneService.insert(phone);
                call.enqueue(new Callback<CMRespDto<List<Phone>>>() {
                    @Override
                    public void onResponse(Call<CMRespDto<List<Phone>>> call, Response<CMRespDto<List<Phone>>> response) {
                        Log.d(TAG, "onFailure: insert() 성공" + phone);
                    }

                    @Override
                    public void onFailure(Call<CMRespDto<List<Phone>>> call, Throwable t) {
                        Log.d(TAG, "onFailure: insert() 실패" + t.getMessage());
                    }
                });

                Log.d(TAG, name.getText().toString());
        }
    }
}