package com.example.damtr2g8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.damtr2g8.databinding.ActivityStatsBinding;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatsActivity extends AppCompatActivity {
    private ActivityStatsBinding binding;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private List<byte[]> imageList = new ArrayList<>();
    private JuegoAPI apiService;
    private JSONArray classesArray;
    public static final String URL = "http://mathbattle.dam.inspedralbes.cat:3751/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        if (intent != null) {
            String jsonString = intent.getStringExtra("classes");
            if (jsonString != null) {
                try {
                    classesArray = new JSONArray(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



//        for (int i = 0; i < classesArray.length(); i++) {
//            try {
//                JSONObject classeJson = classesArray.getJSONObject(i);
//                int idClasse = classeJson.getInt("idClasse");
//                obtenerImagenesDeClase(idClasse);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        try {
            int idClasse = classesArray.getJSONObject(0).getInt("idClasse");
            apiService = retrofit.create(JuegoAPI.class);
            Call<ResponseBody> call = apiService.getImages(idClasse);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        try {
                            byte[] imageBytes = new byte[0];
                            imageBytes = response.body().bytes();
                            Log.d("taj", imageBytes.toString());
                            imageList.add(imageBytes);

                            recyclerView = findViewById(R.id.recyclerStats);
                            adapter = new ImageAdapter(imageList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(StatsActivity.this));
                            recyclerView.setAdapter(adapter);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        /*for (int i = 0; i < imageList.size(); i++) {
                            Log.d("TAJ", imageList.get(i).getUrl());
                        }*/
                    }else{
                        Toast.makeText(StatsActivity.this, "Error al obtener las imagenes", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("TAJ", t.getMessage());
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

/*    private void obtenerImagenesDeClase(int idClasse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(JuegoAPI.class);

        Call <ImageResponse> call = apiService.getImages(idClasse);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    ImageResponse image = response.body();
                    imageList.add(image);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.VeureClasses:
                Intent intent = new Intent(StatsActivity.this, ClassesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menustats, menu);
        return true;
    }
}