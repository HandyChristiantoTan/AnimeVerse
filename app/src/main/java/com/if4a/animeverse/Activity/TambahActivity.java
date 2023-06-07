package com.if4a.animeverse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if4a.animeverse.API.APIRequestData;
import com.if4a.animeverse.API.RetroServer;
import com.if4a.animeverse.Model.ModelResponse;
import com.if4a.animeverse.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etEpisode, etTahun, etStudio, etGenre;
    private Button btnTambah, btnKembali;
    private String nama, episode, tahun, studio, genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etEpisode = findViewById(R.id.et_episode);
        etTahun = findViewById(R.id.et_tahun);
        etStudio = findViewById(R.id.et_studio);
        etGenre = findViewById(R.id.et_genre);
        btnTambah = findViewById(R.id.btn_tambah);
        btnKembali = findViewById(R.id.btn_kembali);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nama = etNama.getText().toString();
                episode = etEpisode.getText().toString();
                tahun = etTahun.getText().toString();
                studio = etStudio.getText().toString();
                genre = etGenre.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Anime Tidak Boleh Kosong");
                }
                else if (episode.trim().isEmpty()){
                    etEpisode.setError("Episode Tidak Boleh Kosong");
                }
                else if (tahun.trim().isEmpty()) {
                    etTahun.setError("Tahun Produksi Tidak Boleh Kosong");
                }
                else if (studio.trim().isEmpty()){
                    etStudio.setError("Studio Produksi Tidak Boleh Kosong");
                }
                else if (genre.trim().isEmpty()){
                    etGenre.setError("Genre Tidak Boleh Kosong");
                }
                else {
                    tambahAnime();
                }
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahActivity.this, MainActivity.class));
            }
        });

    }



    private void tambahAnime(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, episode, tahun, studio, genre);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: " + kode + ", Pesan: " + pesan,
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server :"
                        + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}