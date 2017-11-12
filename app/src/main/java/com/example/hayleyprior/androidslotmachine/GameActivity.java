package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button nudge1;
    private Button nudge2;
    private Button nudge3;
    private Button hold1;
    private Button hold2;
    private Button hold3;


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
        nudge1 = findViewById(R.id.slot1Nudgebutton);
        nudge2 = findViewById(R.id.slot2Nudgebutton);
        nudge3 = findViewById(R.id.slot3Nudgebutton);
        hold1 = findViewById(R.id.slot1HoldButton);
        hold2 = findViewById(R.id.slot2HoldButton);
        hold3 = findViewById(R.id.slot3HoldButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer money = extras.getInt("playerFunds");

        slotMachine.setPlayerFunds(money);
        Integer playerMoney = slotMachine.checkPlayerFunds();

        playerFunds.setText(playerMoney.toString());
    }

//    public void showHoldIfAvailable(){
//        ArrayList<Wheel> wheels = slotMachine.getSlots();
//        Wheel wheel1 = wheels.get(0);
//        Wheel wheel2 = wheels.get(1);
//        Wheel wheel3 = wheels.get(2);
//
//            if(wheel1.getHoldAvailable()){
//               hold1.setVisibility(View.VISIBLE);
//           } else { hold1.setVisibility(View.INVISIBLE);
//            }
//            if(wheel2.getHoldAvailable()){
//                hold2.setVisibility(View.VISIBLE);
//            } else { hold2.setVisibility(View.INVISIBLE);
//            }
//            if(wheel3.getHoldAvailable()){
//                hold3.setVisibility(View.VISIBLE);
//            } else { hold3.setVisibility(View.INVISIBLE);
//            }
//    }

    public ArrayList<Symbols> changeImagesOnSpin(){
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

        return newLine;
    }

    public void updatePlayerMoney(){
        Integer newPlayerFunds = slotMachine.checkPlayerFunds();
        playerFunds.setText(newPlayerFunds.toString());
    }

    public void onSpinButtonClicked(View button){
        ArrayList<Symbols> newLine = changeImagesOnSpin();

        if(slotMachine.checkWin(newLine)){
            slotMachine.addPlayerFunds(newLine.get(0).getValue());
        }

        updatePlayerMoney();
//        showHoldIfAvailable();
    }
}
