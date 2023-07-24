package com.switchill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int button1check;
    private int button2check;
    private int button3check;
    private MediaPlayer mediaPlayer;
    private String KEY = "312";
    private String input = "";
    private int deneme = 0;
    private int kazanma = 0;
    private ImageView imageView3; // Fotoğrafı gösteren ImageView
    private ImageView imageView4; // Değişim sonrası fotoğrafı gösteren ImageView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        SwitchCompat switchButton1 = findViewById(R.id.switchButton1);
        SwitchCompat switchButton2 = findViewById(R.id.switchButton2);
        SwitchCompat switchButton3 = findViewById(R.id.switchButton3);

        switchButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (switchButton2.isChecked() ^ switchButton3.isChecked()) {
                        button1check = 2;
                    } else if (switchButton2.isChecked() && switchButton3.isChecked()) {
                        button1check = 3;
                    } else {
                        button1check = 1;
                    }
                    checkSwitches();
                } else {
                    button1check = 0;
                    checkSwitches();
                }
            }
        });

        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (switchButton1.isChecked() ^ switchButton3.isChecked()) {
                        button2check = 2;
                    } else if (switchButton1.isChecked() && switchButton3.isChecked()) {
                        button2check = 3;
                    } else {
                        button2check = 1;
                    }
                    checkSwitches();
                } else {
                    button2check = 0;
                    checkSwitches();
                }
            }
        });

        switchButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (switchButton2.isChecked() ^ switchButton1.isChecked()) {
                        button3check = 2;
                    } else if (switchButton2.isChecked() && switchButton1.isChecked()) {
                        button3check = 3;
                    } else {
                        button3check = 1;
                    }
                    checkSwitches();
                } else {
                    button3check = 0;
                    checkSwitches();
                }
            }
        });
    }

    private void checkSwitches() {
        SwitchCompat switchButton1 = findViewById(R.id.switchButton1);
        SwitchCompat switchButton2 = findViewById(R.id.switchButton2);
        SwitchCompat switchButton3 = findViewById(R.id.switchButton3);
        if (switchButton1.isChecked() && switchButton2.isChecked() && switchButton3.isChecked()) {
            input = String.valueOf(button1check) + String.valueOf(button2check) + String.valueOf(button3check);
            deneme++;

            TextView myTextView = findViewById(R.id.textView5);
            myTextView.setText(Integer.toString(deneme));

            if (KEY.equals(input)) {
                playSong();
                switchButton1.setChecked(false);
                switchButton2.setChecked(false);
                switchButton3.setChecked(false);
            } else {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    // MediaPlayer is already playing, do nothing or handle as desired
                } else {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.wrong);
                    mediaPlayer.start();
                }
                switchButton1.setChecked(false);
                switchButton2.setChecked(false);
                switchButton3.setChecked(false);


                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.enough);

                Handler handler = new Handler();

                if(deneme==10){
                    imageView.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setVisibility(View.GONE);
                        }
                    }, 1400); // 1 saniye (1000 milisaniye) bekle
                }


            }
        }
    }

    private void playSong() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        Collections.shuffle(numbers);
        int digit1 = numbers.get(0);
        int digit2 = numbers.get(1);
        int digit3 = numbers.get(2);
        KEY = String.valueOf(digit1) + String.valueOf(digit2) + String.valueOf(digit3);
        kazanma++;
        TextView myTextView2 = findViewById(R.id.textView6);
        myTextView2.setText(Integer.toString(kazanma));

        imageView3.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.GONE);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.toggle_sound);

        mediaPlayer.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.VISIBLE);
            }
        }, 1400); // 1 saniye (1000 milisaniye) bekle
    }
}
