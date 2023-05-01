package com.clase1.marvelprojectapi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.clase1.marvelprojectapi.model.Result;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private ArrayList<Result> datos;
    Context context;

    public listAdapter(Context context){
        this.context = context;
        datos = new ArrayList<>();
    }


    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder( @androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder( @androidx.annotation.NonNull ViewHolder viewHolder, int i) {
        Result pokemon = datos.get(i);

        viewHolder.name.setText(pokemon.getName());
        String urlImage = pokemon.getThumbnail().getPath()+ "."+pokemon.getThumbnail().getExtension();
        Glide.with(context).load(urlImage).into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void adicionarLista(ArrayList<Result> listPokemon) {
        datos.addAll(listPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView photo;
        private TextView name;

        public ViewHolder(View itemView){
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
        }
    }

}
