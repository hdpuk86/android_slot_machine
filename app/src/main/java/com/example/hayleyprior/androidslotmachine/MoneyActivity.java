package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
    }

    public void onFiveButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 5);
        startActivity(i);
        }

    public void onTenButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 10);
        startActivity(i);
    }

    public void onTwentyButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 20);
        startActivity(i);
    }

    public void onFiftyButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 50);
        startActivity(i);
    }


}
