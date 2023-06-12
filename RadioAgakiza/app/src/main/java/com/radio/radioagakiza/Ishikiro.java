package com.radio.radioagakiza;

import static android.content.Context.POWER_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;


public class Ishikiro extends Fragment implements Actionplaying{


    Button play;
    boolean prepared=false;
    boolean started=false;
    String stream="https://s1.voscast.com:10377/AGAKIZA";
    MediaPlayer mediaPlayer;
    ProgressDialog progressDialog;
    AudioManager audioManager;

    boolean isplay=false;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    ArrayList<Umwidondoro> arrayList=new ArrayList<>();
    NotificationManager notificationManager;
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ishikiro, container, false);

        //wakelock
        powerManager = (PowerManager) getActivity().getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(powerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();

        //the code for arraylist
        populateUmwidondoro();

        createchannel();


        play=view.findViewById(R.id.play);

        //the code to check if there is internet connection
        if(internetIsConnected()||isNetworkConnected() ){
            progressDialog=new ProgressDialog(getContext());

            play.setEnabled(false);
            progressDialog.setMessage("Tenyenya Gato ...");
            progressDialog.show();

            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            new PlayerTask().execute(stream);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(started){
                        started=false;

                        mediaPlayer.setVolume(0,0);

                        play.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);

                    }
                    else {


                        mediaPlayer.setVolume(1,1);
                        started=true;
                        play.setBackgroundResource(R.drawable.pause);
                    }
                    playclicked();
                    Log.e("tag",isplay+"");
                    CreateNotification.createNotification(requireContext(),arrayList.get(0),R.drawable.logoradioagakiza,1,arrayList.size()-1);

                }
            });
        }
        return view;

    }
    private void createchannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CreateNotification.channel_id,"Agakiza",NotificationManager.IMPORTANCE_LOW);
            notificationManager=getActivity().getSystemService(NotificationManager.class);
            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channel);
            }
        }

    }
    private void populateUmwidondoro() {

        Umwidondoro umwidondoro=new Umwidondoro("Radio Agakiza",R.drawable.logoradioagakiza,"Kw'isoko y'ukuri");
        arrayList.add(umwidondoro);
    }

    @Override
    public void playclicked() {
        if(!isplay){
            isplay=true;
            play.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);

        }
        else {
            isplay=false;
            play.setBackgroundResource(R.drawable.pause);
        }
    }
    class PlayerTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared=true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            super.onPostExecute(aBoolean);
            mediaPlayer.start();
            play.setEnabled(true);
            if(progressDialog.isShowing()){
                progressDialog.cancel();
            }
            started=true;

        }
    }

    @Override
    public void onPause() {

        super.onPause();

        powerManager = (PowerManager) getActivity().getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(powerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(started){
            mediaPlayer.start();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(started){
            mediaPlayer.release();
        }
        wakeLock.release();
    }

    @Override
    public void onStart() {
        wakeLock.acquire();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(networkChangeListener,filter);
        super.onStart();



    }

    @Override
    public void onStop() {
        wakeLock.acquire();
        getActivity().unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }


}