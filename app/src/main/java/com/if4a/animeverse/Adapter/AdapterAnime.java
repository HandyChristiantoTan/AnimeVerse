package com.if4a.animeverse.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if4a.animeverse.API.APIRequestData;
import com.if4a.animeverse.API.RetroServer;
import com.if4a.animeverse.Activity.DetailActivity;
import com.if4a.animeverse.Activity.MainActivity;
import com.if4a.animeverse.Activity.UbahActivity;
import com.if4a.animeverse.Model.ModelAnime;
import com.if4a.animeverse.Model.ModelResponse;
import com.if4a.animeverse.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAnime extends RecyclerView.Adapter<AdapterAnime.VHAnime> {
    private Context ctx;
    private List<ModelAnime> listAnime;

    public AdapterAnime(Context ctx, List<ModelAnime> listAnime){
        this.ctx = ctx;
        this.listAnime = listAnime;
    }

    @NonNull
    @Override
    public VHAnime onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_anime,
                parent, false);
        return new VHAnime(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHAnime holder, int position) {
        ModelAnime MA= listAnime.get(position);

        holder.tvID.setText(MA.getId());
        holder.tvNama.setText(MA.getNama());
        holder.tvEpisode.setText(MA.getEpisode());
        holder.tvTahun.setText(MA.getTahun());
        holder.tvStudio.setText(MA.getStudio());
        holder.tvGenre.setText(MA.getGenre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = MA.getNama();
                String episode = MA.getEpisode();
                String tahun = MA.getTahun();
                String studio = MA.getStudio();
                String genre = MA.getGenre();

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("varNama", nama);
                intent.putExtra("varEpisode", episode);
                intent.putExtra("varTahun", tahun);
                intent.putExtra("varStudio", studio);
                intent.putExtra("varGenre", genre);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAnime.size();
    }

    public class VHAnime extends RecyclerView.ViewHolder {
        TextView tvID, tvNama, tvEpisode, tvTahun, tvStudio, tvGenre;

        public VHAnime(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvEpisode = itemView.findViewById(R.id.tv_episode);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvStudio = itemView.findViewById(R.id.tv_studio);
            tvGenre = itemView.findViewById(R.id.tv_genre);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Operasi apa yang akan dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusAnime(tvID.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvID.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xEpisode", tvEpisode.getText().toString());
                            pindah.putExtra("xTahun", tvTahun.getText().toString());
                            pindah.putExtra("xStudio", tvStudio.getText().toString());
                            pindah.putExtra("xGenre", tvGenre.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }


                private void hapusAnime(String idAnime) {
                    APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<ModelResponse> proses = ARD.ardDelete(idAnime);

                    proses.enqueue(new Callback<ModelResponse>() {
                        @Override
                        public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                            String kode = response.body().getKode();
                            String pesan = response.body().getPesan();

                            Toast.makeText(ctx, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                            ((MainActivity) ctx).retrieveAnime();
                        }

                        @Override
                        public void onFailure(Call<ModelResponse> call, Throwable t) {

                        }
                    });
                }
            }
        }


