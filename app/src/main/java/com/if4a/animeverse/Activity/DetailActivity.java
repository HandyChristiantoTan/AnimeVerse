package com.if4a.animeverse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.if4a.animeverse.R;


public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvEpisode, tvTahun, tvStudio, tvGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvEpisode = findViewById(R.id.tv_episode);
        tvTahun = findViewById(R.id.tv_tahun);
        tvStudio = findViewById(R.id.tv_studio);
        tvGenre = findViewById(R.id.tv_genre);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varNama");
        String episode = intent.getStringExtra("varEpisode");
        String tahun = intent.getStringExtra("varTahun");
        String studio = intent.getStringExtra("varStudio");
        String genre = intent.getStringExtra("varGenre");

        tvNama.setText(nama);
        tvEpisode.setText(episode);
        tvTahun.setText(tahun);
        tvStudio.setText(studio);
        tvGenre.setText(genre);

    }
}