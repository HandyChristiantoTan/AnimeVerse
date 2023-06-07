package com.if4a.animeverse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.if4a.animeverse.R;


public class DetailActivity extends AppCompatActivity {
    private TextView tvNama2, tvEpisode2, tvTahun2, tvStudio2, tvGenre2;
    private Button btnUbah, btnHapus, btnKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama2 = findViewById(R.id.tv_nama2);
        tvEpisode2 = findViewById(R.id.tv_episode2);
        tvTahun2 = findViewById(R.id.tv_tahun2);
        tvStudio2 = findViewById(R.id.tv_studio2);
        tvGenre2 = findViewById(R.id.tv_genre2);

        btnUbah = findViewById(R.id.btn_ubah);
        btnHapus = findViewById(R.id.btn_hapus);
        btnKembali = findViewById(R.id.btn_kembali);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, UbahActivity.class));
            }
        });

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