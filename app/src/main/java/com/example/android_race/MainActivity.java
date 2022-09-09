package com.example.android_race;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBarOne, seekBarTwo, seekBarThree;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    TextView score;
    int tickCheckBox = 0;
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectId();
        seekBarOne.setEnabled(false);
        seekBarTwo.setEnabled(false);
        seekBarThree.setEnabled(false);
        setSeekBarStart();

        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int number = 10;
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                if(seekBarOne.getProgress() + one >= seekBarOne.getMax()-10) {
                    seekBarOne.setProgress(90);
                    EnableCheck();
                    this.cancel();
                    Toast.makeText(MainActivity.this, "One Winner", Toast.LENGTH_SHORT).show();
                    play.setVisibility(View.VISIBLE);
                    if(tickCheckBox == 1) {
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() + 10);
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() - 10);
                    }
                } else if(seekBarTwo.getProgress() + two >= seekBarTwo.getMax()-10) {
                    seekBarTwo.setProgress(90);
                    EnableCheck();
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Two Winner", Toast.LENGTH_SHORT).show();
                    play.setVisibility(View.VISIBLE);
                    if(tickCheckBox == 2) {
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() + 10);
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() - 10);
                    }
                } else if(seekBarThree.getProgress() + three >= seekBarThree.getMax()-10) {
                    seekBarThree.setProgress(90);
                    EnableCheck();
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Three Winner", Toast.LENGTH_SHORT).show();
                    play.setVisibility(View.VISIBLE);
                    if(tickCheckBox == 3) {
                        Toast.makeText(MainActivity.this, "Bạn đoán đúng rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() + 10);
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                        setScore(getIntScore() - 10);
                    }
                }
                seekBarOne.setProgress(seekBarOne.getProgress() + one);
                seekBarTwo.setProgress(seekBarTwo.getProgress() + two);
                seekBarThree.setProgress(seekBarThree.getProgress() + three);

            }

            @Override
            public void onFinish() {

            }
        };

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxOne.isChecked() || checkBoxTwo.isChecked() || checkBoxThree.isChecked()) {
                    play.setVisibility(View.INVISIBLE);
                    setSeekBarStart();
                    countDownTimer.start();
                    DisableCheck();
                } else {
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn đặt cược", Toast.LENGTH_SHORT).show();
                }

            }
        });


        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    checkBoxTwo.setChecked(false);
                    checkBoxThree.setChecked(false);
                    tickCheckBox = 1;
                }
            }
        });

        checkBoxTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    checkBoxOne.setChecked(false);
                    checkBoxThree.setChecked(false);
                    tickCheckBox = 2;
                }
            }
        });

        checkBoxThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    checkBoxTwo.setChecked(false);
                    checkBoxOne.setChecked(false);
                    tickCheckBox = 3;
                }
            }
        });
    }

    public void connectId() {
        seekBarOne = findViewById(R.id.seekbarOne);
        seekBarThree = findViewById(R.id.seekbarThree);
        seekBarTwo = findViewById(R.id.seekbarTwo);
        checkBoxOne = findViewById(R.id.checkboxOne);
        checkBoxTwo = findViewById(R.id.checkboxTwo);
        checkBoxThree = findViewById(R.id.checkboxThree);
        play = findViewById(R.id.play);
        score = findViewById(R.id.score);

    }

    public void setScore(int intScore) {
        score.setText("" + intScore);
    }


    public int getIntScore() {
        return Integer.valueOf(score.getText().toString().trim());
    }

    public void EnableCheck() {
        checkBoxOne.setEnabled(true);
        checkBoxTwo.setEnabled(true);
        checkBoxThree.setEnabled(true);
    }

    public void DisableCheck() {
        checkBoxThree.setEnabled(false);
        checkBoxTwo.setEnabled(false);
        checkBoxOne.setEnabled(false);
    }

    public void setSeekBarStart() {
        seekBarOne.setProgress(5);
        seekBarThree.setProgress(5);
        seekBarTwo.setProgress(5);
    }

}