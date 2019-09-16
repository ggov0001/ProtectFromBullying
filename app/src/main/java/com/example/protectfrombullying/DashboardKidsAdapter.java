package com.example.protectfrombullying;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DashboardKidsAdapter extends RecyclerView.Adapter<DashboardKidsAdapter.KidsViewHolder> {

    private Context context;
    public List<Kids> kidsList;

    public DashboardKidsAdapter(Context context, List<Kids> kidsList) {
        this.context = context;
        this.kidsList = kidsList;
    }

    @NonNull
    @Override
    public KidsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.kids_recyclerview, null);

       KidsViewHolder kidsViewHolder = new KidsViewHolder(view, new KidsViewHolder.MyClickListener() {
           @Override
           public void onKidsButton(int position) {
               Intent intent = new Intent(context.getApplicationContext(), CyberBullyTextsActivity.class);
               intent.putExtra("kidId", kidsList.get(position).getKidId());
               intent.putExtra("kidName", kidsList.get(position).getKidName());
               context.startActivity(intent);
           }
       });

        return kidsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KidsViewHolder kidsViewHolder, int i) {
        final Kids kids = kidsList.get(i);
        kidsViewHolder.kidsname.setText(kids.getKidName());
    }

    @Override
    public int getItemCount() {
        return kidsList.size();
    }

    public static class KidsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MyClickListener listener;
        TextView kidsname;
        Button kidsButton;

        public KidsViewHolder(@NonNull View itemView, MyClickListener listener) {
            super(itemView);
            kidsname = (TextView) itemView.findViewById(R.id.textView_kidname);
            kidsButton = (Button) itemView.findViewById(R.id.button_kids);

            this.listener = listener;
            kidsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_kids:
                    listener.onKidsButton(this.getLayoutPosition());
                    break;

                default:
                    break;
            }
        }

        public interface MyClickListener {
            void onKidsButton(int position);
        }
    }
}
