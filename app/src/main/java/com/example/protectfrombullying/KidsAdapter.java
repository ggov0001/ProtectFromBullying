package com.example.protectfrombullying;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidsViewHolder> {

    private Context context;
    public List<Kids> kidsList;

    public KidsAdapter(Context context, List<Kids> kidsList) {
        this.context = context;
        this.kidsList = kidsList;
    }

    @NonNull
    @Override
    public KidsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kids_recyclerview, null);
        return new KidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsViewHolder kidsViewHolder, int i) {
        Kids kids = kidsList.get(i);
        kidsViewHolder.kidsname.setText(kids.getKidName());

    }

    @Override
    public int getItemCount() {
        return kidsList.size();
    }

    class KidsViewHolder extends RecyclerView.ViewHolder{

        TextView kidsname;

        public KidsViewHolder(@NonNull View itemView) {
            super(itemView);

            kidsname = (TextView) itemView.findViewById(R.id.textView_kidname);
        }
    }
}
