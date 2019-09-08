package com.example.protectfrombullying;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.kids_recyclerview, null);

        KidsViewHolder kidsViewHolder = new KidsViewHolder(view, new KidsViewHolder.MyClickListener() {
            @Override
            public void onKidsButton(int position) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                View viewForDialogBox = inflater.inflate(R.layout.kids_dialoginfo, null);

                Button okDialogBoxButton = (Button) viewForDialogBox.findViewById(R.id.button_okqrcodedialog);
                ImageView qrCodeDialog = (ImageView) viewForDialogBox.findViewById(R.id.imageview_qrcodedialog);
                TextView kidNameDialog = (TextView) viewForDialogBox.findViewById(R.id.textView_kidnamedialog);

                String contentsOfQRCode = kidsList.get(position).getKidName() + "/" + kidsList.get(position).getKidId();
                new QRCodeImageDownloader(qrCodeDialog).execute("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + contentsOfQRCode);
                kidNameDialog.setText(kidsList.get(position).getKidName());

                alert.setView(viewForDialogBox);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                okDialogBoxButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        return kidsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final KidsViewHolder kidsViewHolder, int i) {
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
