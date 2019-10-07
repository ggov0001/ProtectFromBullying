package com.example.protectfrombullying;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsAdapter.RecordingsViewHolder>{

    private Context context;
    private List<String> recordingNames;
    private List<String> recordingUri;
    private String kidName;



    public RecordingsAdapter(Context context, List<String> recordingNames, List<String> recordingUri, String kidName) {
        this.context = context;
        this.recordingNames = recordingNames;
        this.recordingUri = recordingUri;
        this.kidName = kidName;
    }

    @NonNull
    @Override
    public RecordingsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.recordings_recyclerview, null);

         final RecordingsViewHolder recordingsViewHolder = new RecordingsViewHolder(view, new RecordingsViewHolder.MyClickListener() {
            @Override
            public void onRecordingsButton(final int position) {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                final ImageView imageView = (ImageView) view.findViewById(R.id.imageView_recordings);
                try {
                    mediaPlayer.setDataSource(String.valueOf(recordingUri.get(position)));
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                            imageView.setImageResource(R.drawable.pauserecording);
                        }
                    });

                   mediaPlayer.prepare();
                   mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                       @Override
                       public void onCompletion(MediaPlayer mp) {

                           imageView.setImageResource(R.drawable.playrecording);
                       }
                   });
//                   mediaPlayer.start();
                    Toast.makeText(context, "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }
        });

        return recordingsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecordingsAdapter.RecordingsViewHolder recordingsViewHolder, int i) {
        String displayDatAndTimeAlone[] = recordingNames.get(i).split("-", 2);
        String recordingNameDisplayed = kidName + " - " + displayDatAndTimeAlone[1];
        recordingsViewHolder.recordingsName.setText(recordingNameDisplayed);

    }

    @Override
    public int getItemCount() {
        return recordingNames.size();
    }

    public static class RecordingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MyClickListener listener;
        TextView recordingsName;
        Button recordingsButton;
        ImageView recordingImage;

        public RecordingsViewHolder(@NonNull View itemView, MyClickListener listener) {
            super(itemView);
            recordingsName = (TextView) itemView.findViewById(R.id.textView_recordingName);
            recordingsButton = (Button) itemView.findViewById(R.id.button_recordings);
            recordingImage = (ImageView) itemView.findViewById(R.id.imageView_recordings);

            this.listener = listener;
            recordingsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_recordings:
                    listener.onRecordingsButton(this.getLayoutPosition());
                    break;

                default:
                    break;
            }
        }

        public interface MyClickListener {
            void onRecordingsButton(int position);
        }
    }
}
