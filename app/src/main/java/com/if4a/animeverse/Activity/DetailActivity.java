package com.if4a.animeverse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.if4a.animeverse.API.APIRequestData;
import com.if4a.animeverse.API.RetroServer;
import com.if4a.animeverse.Model.ModelResponse;
import com.if4a.animeverse.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private TextView tvID, tvNama2, tvEpisode2, tvTahun2, tvStudio2, tvGenre2;
    private Button btnUbah, btnHapus, btnKembali;
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvID = findViewById(R.id.tv_id);
        tvNama2 = findViewById(R.id.tv_nama2);
        tvEpisode2 = findViewById(R.id.tv_episode2);
        tvTahun2 = findViewById(R.id.tv_tahun2);
        tvStudio2 = findViewById(R.id.tv_studio2);
        tvGenre2 = findViewById(R.id.tv_genre2);

        btnKembali = findViewById(R.id.btn_kembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varNama");
        String episode = intent.getStringExtra("varEpisode");
        String tahun = intent.getStringExtra("varTahun");
        String studio = intent.getStringExtra("varStudio");
        String genre = intent.getStringExtra("varGenre");

        tvNama2.setText(nama);
        tvEpisode2.setText(episode);
        tvTahun2.setText(tahun);
        tvStudio2.setText(studio);
        tvGenre2.setText(genre);


    }
}