package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private TextView playerFunds;
    private SlotMachine slotMachine;
    private Wheel wheel;
    private ImageView symbol1;
    private ImageView symbol2;
    private ImageView symbol3;
    private Integer playerMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerFunds = findViewById(R.id.playerFundsText);
        wheel = new Wheel();
        slotMachine = new SlotMachine(wheel, 3);
        symbol1 = findViewById(R.id.imageSymbol1);
        symbol2 = findViewById(R.id.imageSymbol2);
        symbol3 = findViewById(R.id.imageSymbol3);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer money = extras.getInt("playerFunds");

        slotMachine.setPlayerFunds(money);
        Integer playerMoney = slotMachine.checkPlayerFunds();

        playerFunds.setText(playerMoney.toString());
    }

    public void changeImagesOnSpin(){
        ArrayList<Symbols> newLine = slotMachine.spin();
        ArrayList<String> lineImages = slotMachine.getLineImages(newLine);
        String image1 = lineImages.get(0);
        String image2 = lineImages.get(1);
        String image3 = lineImages.get(2);

        int idImage1 = getResources().getIdentifier(image1, "drawable", getPackageName());
        int idImage2 = getResources().getIdentifier(image2, "drawable", getPackageName());
        int idImage3 = getResources().getIdentifier(image3, "drawable", getPackageName());

        symbol1.setImageResource(idImage1);
        symbol2.setImageResource(idImage2);
        symbol3.setImageResource(idImage3);
    }

    public void updatePlayerMoney(){
        Integer newPlayerFunds = slotMachine.checkPlayerFunds();
        playerFunds.setText(newPlayerFunds.toString());
    }

    public void onSpinButtonClicked(View button){
        changeImagesOnSpin();
        updatePlayerMoney();
    }
}
