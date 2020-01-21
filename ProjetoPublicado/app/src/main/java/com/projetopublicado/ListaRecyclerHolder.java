package com.projetopublicado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public final class ListaRecyclerHolder extends RecyclerView.Adapter<ListaRecyclerHolder.ExampleViewHolder> {
    private List<Match> arrayMatch;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView time_1;
        TextView time_2;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            time_1 = itemView.findViewById(R.id.time_1);
            time_2 = itemView.findViewById(R.id.time_2);
        }
    }
    public ListaRecyclerHolder(ArrayList<Match> matchlist){
        arrayMatch = matchlist;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_escolha_jogo, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Match currentMatch = arrayMatch.get(position);
        holder.time_1.setText(currentMatch.getHome_name());
        holder.time_2.setText(currentMatch.getAway_name());
    }

    @Override
    public int getItemCount() {
        return arrayMatch.size();
    }
}
