package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView playerFunds;
    SlotMachine slotMachine;
    Wheel wheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerFunds = findViewById(R.id.playerFundsText);
        wheel = new Wheel();
        slotMachine = new SlotMachine(wheel, 3);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer money = extras.getInt("playerFunds");

        slotMachine.setPlayerFunds(money);
        Integer playerMoney = slotMachine.checkPlayerFunds();

        playerFunds.setText(playerMoney.toString());
    }

    public void onSpinButtonClicked(View button){

    }
}
