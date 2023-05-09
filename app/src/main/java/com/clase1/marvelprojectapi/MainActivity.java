package com.clase1.marvelprojectapi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clase1.marvelprojectapi.model.MarvelService;
import com.clase1.marvelprojectapi.model.Result;
import com.clase1.marvelprojectapi.model.Root;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MarvelService service = null;
    final String PUBLIC_API_KEY = "502d6509cdb6d800b2374d6f4fb7698d";
    final String TS = "1";
    final String HASH = "b77a90841ab448f028fe4a42926c7197";

    RecyclerView recyclerView;
    private listAdapter listaAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        listaAdapt = new listAdapter(this);
        recyclerView.setAdapter(listaAdapt);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        service = new MarvelService(PUBLIC_API_KEY, TS, HASH);

        this.getInfo();

    }
    public void getInfo() {
        service.requestSuperHeroData((isNetworkError, statusCode, root) -> {
            if (!isNetworkError) {
                if (statusCode == 200) {
                    //Log.d("Super Hero", String.valueOf(root.getData().getResults().isEmpty()));
                    showSuperHeroInfo(root);
                } else {
                    Log.d("Super Hero", "Service error");
                }
            } else {
                Log.d("Super Hero", "Network error");
            }
        });
    }

    public  void showSuperHeroInfo(Root root) {
        runOnUiThread(() ->{
            ArrayList<Result> listPokemon = (ArrayList<Result>) root.getData().getResults();
            listaAdapt.adicionarLista(listPokemon);
        });
    }

}