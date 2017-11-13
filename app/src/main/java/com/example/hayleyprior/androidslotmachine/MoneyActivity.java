package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoneyActivity extends AppCompatActivity {

    int currentFunds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if(i.hasExtra("currentFunds")){
            currentFunds = extras.getInt("currentFunds");
        } else {
            currentFunds = 0;
        }

    }

    public void onFiveButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 5 + currentFunds);
        startActivity(i);
        }

    public void onTenButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 10 + currentFunds);
        startActivity(i);
    }

    public void onTwentyButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 20 + currentFunds);
        startActivity(i);
    }

    public void onFiftyButtonClicked(View button){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 50 + currentFunds);
        startActivity(i);
    }


}
