package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MiniGameActivity extends AppCompatActivity {

    private MiniGame miniGame;
    private ImageButton tree1;
    private ImageButton tree2;
    private ImageButton tree3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);

        int poacher = R.drawable.poacher;
        miniGame = new MiniGame();
        ImageView result1 = findViewById(R.id.result1);
        ImageView result2 = findViewById(R.id.result2);
        ImageView result3 = findViewById(R.id.result3);
        tree1 = findViewById(R.id.tree1);
        tree2 = findViewById(R.id.tree2);
        tree3 = findViewById(R.id.tree3);

        if(miniGame.getPosition1().equals("Win")){
            result1.setImageResource(poacher);
        } else if (miniGame.getPosition2().equals("Win")){
            result2.setImageResource(poacher);
        } else {
            result3.setImageResource(poacher);
        }

    }

    public void setAllTreesInvisible(){
        tree1.setVisibility(View.INVISIBLE);
        tree2.setVisibility(View.INVISIBLE);
        tree3.setVisibility(View.INVISIBLE);
    }

    public void onTree1Clicked(View button){
        setAllTreesInvisible();
        final Intent win = new Intent(this, GameActivity.class);
        final Intent lose = new Intent(this, GameOverActivity.class);
        win.putExtra("playerFunds", 10);
        if(miniGame.getPosition1().equals("Win")){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(win);
                }
            }, 2000);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(lose);
                }
            }, 2000);
        }
    }

    public void onTree2Clicked(View button){
        setAllTreesInvisible();
        final Intent win = new Intent(this, GameActivity.class);
        final Intent lose = new Intent(this, GameOverActivity.class);
        win.putExtra("playerFunds", 10);
        if(miniGame.getPosition2().equals("Win")){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(win);
                }
            }, 2000);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(lose);
                }
            }, 2000);
        }
    }

    public void onTree3Clicked(View button){
        setAllTreesInvisible();
        final Intent win = new Intent(this, GameActivity.class);
        final Intent lose = new Intent(this, GameOverActivity.class);
        win.putExtra("playerFunds", 10);
        if(miniGame.getPosition3().equals("Win")){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(win);
                }
            }, 2000);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(lose);
                }
            }, 2000);
        }
    }

}
