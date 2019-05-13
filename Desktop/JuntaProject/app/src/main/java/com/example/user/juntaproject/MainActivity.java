package com.example.user.juntaproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private BackPressCloseHandler backPressCloseHandler;

    int hrv_data = 0;
    int di_data = 0;
    int noise_data = 0;
    int rand;
    int music_res;
    String warning;

    Context context = this;
    ImageButton stressBtn;
    TextView textView;
    ImageView uncomf;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        textView = (TextView)findViewById(R.id.text_view);
        uncomf = (ImageView)findViewById(R.id.imageView);
        stressBtn = (ImageButton)findViewById(R.id.imgBtn);
        stressBtn.setOnClickListener(this);

        rand = (int)(Math.random()*12)+1;
        if (rand == 1){
            music_res = R.raw.m1;
        }
        else if (rand == 2){
            music_res = R.raw.m2;
        }
        else if (rand == 3){
            music_res = R.raw.m3;
        }
        else if (rand == 4){
            music_res = R.raw.m4;
        }
        else if (rand == 5){
            music_res = R.raw.m5;
        }
        else if (rand == 6){
            music_res = R.raw.m6;
        }
        else if (rand == 7){
            music_res = R.raw.m7;
        }
        else if (rand == 8){
            music_res = R.raw.m8;
        }
        else if (rand == 9){
            music_res = R.raw.m9;
        }
        else if (rand == 10){
            music_res = R.raw.m10;
        }
        else if (rand == 11){
            music_res = R.raw.m11;
        }
        else {
            music_res = R.raw.m12;
        }
        mp = MediaPlayer.create(context, music_res);

        //mp = MediaPlayer.create(context, );



        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
                if (key.equals("Pulse")){
//                    pulse_data = Integer.valueOf((String) value);
                    warning = (String)value;
                    //String[] stress_data = warning.split("/");
                    try{
                        di_data = Integer.parseInt(warning.split("/")[0]);
                        noise_data = Integer.parseInt(warning.split("/")[1]);
                        hrv_data = Integer.parseInt(warning.split("/")[2]);
                        //Log.d("Test","--------------------------------111111111111111111111");
                    }catch (NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    Log.d(TAG, "PulseData" + warning);
                }
            }
        }
        if (hrv_data > 0){
            String all_data = "Your STRESS : "+ String.valueOf(hrv_data)  + "\nDiscomfort Index : " + String.valueOf(di_data) + "\nNoise : "+String.valueOf(noise_data) + "\n";
            textView.setText(all_data);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(36);
            //Typeface type = Typeface.createFromAsset(getAssets(),"georgia.ttf");
            //textView.setTypeface(type);
            GlideDrawableImageViewTarget gifimg = new GlideDrawableImageViewTarget(uncomf);
            Glide.with(this).load(R.drawable.emergency).into(gifimg);
            stressBtn.setVisibility(View.INVISIBLE);
            mp.start();
        }
        // [END handle_data_extras]
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        mp.stop();
        mp.release();
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StressMeasure.class);
        startActivity(intent);
        //finish();
    }
}
