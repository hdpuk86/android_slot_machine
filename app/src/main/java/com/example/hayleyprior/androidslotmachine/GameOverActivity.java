package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void onAddMoneyClicked(View button){
        Intent i = new Intent(this, MoneyActivity.class);
            startActivity(i);
    }

    public void onQuitClicked(View button){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
