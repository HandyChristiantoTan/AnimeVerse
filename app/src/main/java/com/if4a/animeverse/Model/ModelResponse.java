package com.if4a.animeverse.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelAnime> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelAnime> getData() {
        return data;
    }
}
