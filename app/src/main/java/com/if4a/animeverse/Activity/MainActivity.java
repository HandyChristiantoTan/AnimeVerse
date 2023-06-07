package com.if4a.animeverse.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.if4a.animeverse.API.APIRequestData;
import com.if4a.animeverse.API.RetroServer;
import com.if4a.animeverse.Adapter.AdapterAnime;
import com.if4a.animeverse.Model.ModelAnime;
import com.if4a.animeverse.Model.ModelResponse;
import com.if4a.animeverse.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAnime;
    private Button btnTambah;
    private ProgressBar pbAnime;
    private RecyclerView.Adapter adAnime;
    private RecyclerView.LayoutManager lmAnime;
    private List<ModelAnime> listAnime = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAnime = findViewById(R.id.rv_anime);
        btnTambah = findViewById(R.id.btn_tambah);
        pbAnime = findViewById(R.id.pb_anime);

        lmAnime = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAnime.setLayoutManager(lmAnime);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        retrieveAnime();
    }
    public void retrieveAnime(){
        pbAnime.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listAnime = response.body().getData();

                adAnime = new AdapterAnime(MainActivity.this, listAnime);
                rvAnime.setAdapter(adAnime);
                adAnime.notifyDataSetChanged();

                pbAnime.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbAnime.setVisibility(View.GONE);
            }
        });
    }

}