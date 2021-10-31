package com.example.advanceapitutorialjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("https://cricket.sportmonks.com/api/v2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       OurRetrofitClint retrofitClint=retrofit.create(OurRetrofitClint.class);
       Call<OurMainDataClass>call=retrofitClint.getData("XdZ1bwHkjs8z4zPyTk727rNrCjpi5O1pV8vYzsBFSRadyyaoATEyeC7cpfvn");
       call.enqueue(new Callback<OurMainDataClass>() {
           @Override
           public void onResponse(Call<OurMainDataClass> call, Response<OurMainDataClass> response) {
               if (response.isSuccessful()){
                   List<ObjectDataClass>list=response.body().getData();
                   for (ObjectDataClass objectDataClass:list){
                       Log.d("id", String.valueOf(objectDataClass.getId()));
                       Log.d("resource", String.valueOf(objectDataClass.getResource()));
                       Log.d("name", String.valueOf(objectDataClass.getName()));
                       Log.d("updated_at", String.valueOf(objectDataClass.getUpdated_at()));
                   }

               }else {
                   Log.d("response","Fail");
               }
           }

           @Override
           public void onFailure(Call<OurMainDataClass> call, Throwable t) {
               Log.d("response","Fail");
           }
       });
    }
}