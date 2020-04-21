package com.example.money1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessegesAdapter extends RecyclerView.Adapter<MessegesAdapter.MesegesViewBulder> {

    private List<Messeige> messeiges;

    public List<Messeige> getMesseiges() {
        return messeiges;
    }

    public void setMesseiges(List<Messeige> messeiges) {
        this.messeiges = messeiges;
        notifyDataSetChanged();
    }

    public MessegesAdapter() {
        messeiges = new ArrayList<>();

    }

    @NonNull
    @Override
    public MesegesViewBulder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layot_item_messege, viewGroup, false);
        return new MesegesViewBulder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesegesViewBulder mesegesViewBulder, int i) {
        mesegesViewBulder.textViewAthor.setText(messeiges.get(i).getAthor());
        mesegesViewBulder.textViewTextOfMassege.setText(messeiges.get(i).getTextOfMessage());
        mesegesViewBulder.textViewSum.setText(messeiges.get(i).getSum());



    }

    @Override
    public int getItemCount() {
        return messeiges.size();
    }

    class MesegesViewBulder extends RecyclerView.ViewHolder {

        private TextView textViewAthor;
        private TextView textViewTextOfMassege;
        private TextView textViewSum;

        public MesegesViewBulder(@NonNull View itemView) {

            super(itemView);
            textViewAthor = itemView.findViewById(R.id.textViewAthor);
            textViewTextOfMassege = itemView.findViewById(R.id.textViewTextOfMassege);
            textViewSum =  itemView.findViewById(R.id.textViewSum);
        }



    }
}
