package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidsViewHolder> {

    private Context context;
    public List<Kids> kidsList;

    //Instance created for Deleting a kid
    private KidsDatabase database;

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
            public void onKidsButton(final int position) {
                //click on a kid's card for alert
                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                View viewForDialogBox = inflater.inflate(R.layout.kids_dialoginfo, null);
                //Initialize database
                database = Room.databaseBuilder(context, KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

                //Initialize buttons, imageview and text view in the dialog
                Button okDialogBoxButton = (Button) viewForDialogBox.findViewById(R.id.button_okqrcodedialog);
                final Button deleteDialogButton = (Button) viewForDialogBox.findViewById(R.id.button_deleteqrcodedialog);
                ImageView qrCodeDialog = (ImageView) viewForDialogBox.findViewById(R.id.imageview_qrcodedialog);
                TextView kidNameDialog = (TextView) viewForDialogBox.findViewById(R.id.textView_kidnamedialog);

                // show the qr code
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

                deleteDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context)
                                //set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                //set title
                                .setTitle("Are you sure to Delete?")
                                //set message
                                .setMessage("Deleting your kid will delete all the records.")
                                //set positive button
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DeleteKidAsync deleteKidAsync = new DeleteKidAsync();
                                        deleteKidAsync.execute(position);
                                    }
                                })
                                //set negative button
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(context,"Not Deleted!",Toast.LENGTH_LONG).show();
                                    }
                                })
                                .show();

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

    //Delete the kid record
    private class DeleteKidAsync extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... position) {
            Kids kids = new Kids((kidsList.get(position[0]).getKidId()),(kidsList.get(position[0]).getKidName()));
            database.kidsDAO().delete(kids);
            return null;
        }

        @Override
        protected void onPostExecute(String value) {
            Toast.makeText(context, "Deleted!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, YourKidsActivity.class);
            context.startActivity(intent);

        }
    }

}
