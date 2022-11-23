package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CollectActivity extends AppCompatActivity {

    Integer startMoney;
    Integer endMoney;
    TextView collectMessage;
    TextView collectValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        collectMessage = findViewById(R.id.collectMessageText);
        collectValue = findViewById(R.id.collectValueText);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        startMoney = extras.getInt("startMoney");
        endMoney = extras.getInt("endMoney");

        collectValue.setText("£" + endMoney.toString());

        if(startMoney > endMoney){
            collectMessage.setText("Better Luck Next Time!");
        } else {
            collectMessage.setText("Congratulations!!");
        }
    }

    public void onPlayAgainClicked(View button){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

}
