package com.db.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int user_overall_score_state = 0;
    private int user_turn_score = 0;
    private int computer_overall_score = 0;
    private int computer_turn_score = 0;

    private ImageView iv;
    private TextView tv;
    private Button rollbtn,holdbtn,resetbtn;

    private boolean USER_PLAYING = true;
    private String TAG = "SCARNES DICE GAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.textView);
        rollbtn = (Button) findViewById(R.id.roll_button);
        holdbtn = (Button) findViewById(R.id.hold_button);
        resetbtn = (Button) findViewById(R.id.reset_button);

        Log.d(TAG,"on create method");

    }

    public void showScore(int a,int b)
    {
        Log.d(TAG,"on show Score method");
        tv.setText("Your score :" + a + "computer score :" + b );
    }
    public void onRollClick(View v){
        Log.d(TAG,"on roll click method");
        user_turn_score = rollDice();
        if(user_turn_score == 1) {
            showScore(user_overall_score_state,computer_overall_score);
            Toast.makeText(this,"OOPS...You lose chance",Toast.LENGTH_SHORT).show();
            computerTurn();

        }
        else if(user_overall_score_state + user_turn_score >= 100)
        {
            showScore(user_overall_score_state + user_turn_score,computer_overall_score);
            Toast.makeText(this,"You are Winner!!!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            user_overall_score_state += user_turn_score;
            showScore(user_overall_score_state,computer_overall_score);
        }
    }
    public void computerTurn()
    {
        Log.d(TAG,"on computer turn method");
        computer_turn_score = rollDice();
        if(computer_turn_score == 1) {
            showScore(user_overall_score_state,computer_overall_score);
            Toast.makeText(this,"OOPS...Computer lose chance",Toast.LENGTH_SHORT).show();

        }
        else if(computer_overall_score + computer_turn_score >= 100)
        {
            showScore(user_overall_score_state,computer_overall_score + computer_turn_score);
            Toast.makeText(this,"Computer is Winner!!!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            computer_overall_score =+ computer_turn_score;
            showScore(user_overall_score_state,computer_overall_score);
        }
    }
    public void onHoldClick(View v){
        Log.d(TAG,"on hold click method");

    }
    public void onResetClick(View v){
        Log.d(TAG,"on reset click method");
        user_overall_score_state = 0;
        user_turn_score = 0;
        computer_overall_score = 0;
        computer_turn_score = 0;
        tv.setText("Your score :" + user_overall_score_state + "computer score :" + computer_overall_score );
        rollbtn.setEnabled(true);
        holdbtn.setEnabled(false);
        USER_PLAYING = true;
        Toast.makeText(this,"Game start.Your turn",Toast.LENGTH_SHORT).show();
    }

    public int rollDice() {
        Log.d(TAG,"on roll dice method");
        int no = new Random().nextInt(6) + 1;
        switch (no) {
            case 1:
                iv.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv.setImageResource(R.drawable.dice6);
                break;
        }
        return no;
    }
}
