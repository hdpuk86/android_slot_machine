package com.example.hayleyprior.androidslotmachine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private TextView playerFunds;
    private SlotMachine slotMachine;
    private Wheel wheel1;
    private Wheel wheel2;
    private Wheel wheel3;
    private ImageView symbol1;
    private ImageView symbol2;
    private ImageView symbol3;
    private ImageView wheel1Top;
    private ImageView wheel2Top;
    private ImageView wheel3Top;
    private ImageView wheel1Bottom;
    private ImageView wheel2Bottom;
    private ImageView wheel3Bottom;
    private ImageView winnerImage;
    private ImageButton spinButton;
    private ToggleButton nudge1;
    private ToggleButton nudge2;
    private ToggleButton nudge3;
    private ToggleButton hold1;
    private ToggleButton hold2;
    private ToggleButton hold3;
    private Integer startMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerFunds = findViewById(R.id.playerFundsText);
        slotMachine = new SlotMachine(3);
        symbol1 = findViewById(R.id.imageSymbol1);
        symbol2 = findViewById(R.id.imageSymbol2);
        symbol3 = findViewById(R.id.imageSymbol3);
        wheel1Top = findViewById(R.id.wheel1TopImage);
        wheel2Top = findViewById(R.id.wheel2TopImage);
        wheel3Top = findViewById(R.id.wheel3TopImage);
        wheel1Bottom = findViewById(R.id.wheel1BottomImage);
        wheel2Bottom = findViewById(R.id.wheel2BottomImage);
        wheel3Bottom = findViewById(R.id.wheel3BottomImage);
        winnerImage = findViewById(R.id.winnerImage);
        spinButton = findViewById(R.id.spinButton);
        nudge1 = findViewById(R.id.nudgeButton1);
        nudge2 = findViewById(R.id.nudgeButton2);
        nudge3 = findViewById(R.id.nudgeButton3);
        hold1 = findViewById(R.id.holdButton1);
        hold2 = findViewById(R.id.holdButton2);
        hold3 = findViewById(R.id.holdButton3);
        ArrayList<Wheel> slots = slotMachine.getSlots();
        wheel1 = slots.get(0);
        wheel2 = slots.get(1);
        wheel3 = slots.get(2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        startMoney = extras.getInt("playerFunds");

        slotMachine.setPlayerFunds(startMoney);
        Integer playerMoney = slotMachine.checkPlayerFunds();

        playerFunds.setText(playerMoney.toString());
    }

    //GENERAL GAME FUNCTIONS

    public void updatePlayerMoney(){
        Integer newPlayerFunds = slotMachine.checkPlayerFunds();
        playerFunds.setText(newPlayerFunds.toString());
    }

    public boolean checkPlayerBust(){
        int money = slotMachine.checkPlayerFunds();
        if(money <= 0){
            return true;
        }
        return false;
    }

    public void checkWin(){
        ArrayList<Symbols> newLine = spin();
        final int value = slotMachine.getWinValue(newLine);
        if (slotMachine.checkWin(newLine)) {
            win(value);
        }
    }

    public void gameOver(){
        Intent i = new Intent(this, GameOverActivity.class);
        startActivity(i);
    }

    public void win(final int value){
        winnerImage.setVisibility(View.VISIBLE);
        spinButton.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                winnerImage.setVisibility(View.INVISIBLE);
                spinButton.setVisibility(View.VISIBLE);
                slotMachine.addPlayerFunds(value);
                updatePlayerMoney();
            }
        }, 2000);
    }

    //SPIN FUNCTIONS

    public ArrayList<Symbols> spin(){
        ArrayList<Symbols> newLine = slotMachine.spin();
        updateCurrentLine(newLine);
        updateTopLine();
        updateBottomLine();
        return newLine;
    }

    public void updateCurrentLine(ArrayList<Symbols> newLine){
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

    public void updateTopLine(){
        ArrayList<Symbols> topLine = slotMachine.getTopLineSymbols();
        ArrayList<String> lineImages = slotMachine.getLineImages(topLine);
        String image1 = lineImages.get(0);
        String image2 = lineImages.get(1);
        String image3 = lineImages.get(2);
        int idImage1 = getResources().getIdentifier(image1, "drawable", getPackageName());
        int idImage2 = getResources().getIdentifier(image2, "drawable", getPackageName());
        int idImage3 = getResources().getIdentifier(image3, "drawable", getPackageName());
        wheel1Top.setImageResource(idImage1);
        wheel2Top.setImageResource(idImage2);
        wheel3Top.setImageResource(idImage3);
    }

    public void updateBottomLine(){
        ArrayList<Symbols> bottomLine = slotMachine.getBottomLineSymbols();
        ArrayList<String> lineImages = slotMachine.getLineImages(bottomLine);
        String image1 = lineImages.get(0);
        String image2 = lineImages.get(1);
        String image3 = lineImages.get(2);
        int idImage1 = getResources().getIdentifier(image1, "drawable", getPackageName());
        int idImage2 = getResources().getIdentifier(image2, "drawable", getPackageName());
        int idImage3 = getResources().getIdentifier(image3, "drawable", getPackageName());
        wheel1Bottom.setImageResource(idImage1);
        wheel2Bottom.setImageResource(idImage2);
        wheel3Bottom.setImageResource(idImage3);
    }

    public void onSpinButtonClicked(View button){
        if(!checkPlayerBust()) {
            checkWin();
            updatePlayerMoney();
            showHoldIfAvailable();
            showNudgeIfAvailable();
            resetNudgesFalse();
            resetHoldButtonsFalse();
        } else {
            gameOver();
        }
    }

    //COLLECT

    public void onCollectButtonClicked(View button){
        Integer endMoney = slotMachine.checkPlayerFunds();
        Intent i = new Intent(this, CollectActivity.class);
        i.putExtra("startMoney", this.startMoney);
        i.putExtra("endMoney", endMoney);
        startActivity(i);
    }

    //ADD MONEY

    public void onAddMoneyClicked(View button){
        Intent i = new Intent(this, MoneyActivity.class);
        i.putExtra("currentFunds", slotMachine.checkPlayerFunds());
        startActivity(i);
    }

    //HOLD FUNCTIONS

    public void showHoldWheel1(){
        if(wheel1.getHoldAvailable()){
            hold1.setBackgroundColor(Color.parseColor("#FFFFF584"));
            hold1.setTextColor(Color.parseColor("#FFFF0D00"));
            hold1.setVisibility(View.VISIBLE);
        } else {
            hold1.setVisibility(View.INVISIBLE);
        }
    }

    public void showHoldWheel2(){
        if(wheel2.getHoldAvailable()){
            hold2.setBackgroundColor(Color.parseColor("#FFFFF584"));
            hold2.setTextColor(Color.parseColor("#FFFF0D00"));
            hold2.setVisibility(View.VISIBLE);
        } else {
            hold2.setVisibility(View.INVISIBLE);
        }
    }

    public void showHoldWheel3(){
        if(wheel3.getHoldAvailable()){
            hold3.setBackgroundColor(Color.parseColor("#FFFFF584"));
            hold3.setTextColor(Color.parseColor("#FFFF0D00"));
            hold3.setVisibility(View.VISIBLE);
        } else {
            hold3.setVisibility(View.INVISIBLE);
        }
    }

    public void showHoldIfAvailable(){
        showHoldWheel1();
        showHoldWheel2();
        showHoldWheel3();
    }

    public void onHold1ButtonClicked(View button){
        boolean hold = ((ToggleButton)button).isChecked();
        if(hold) {
            wheel1.setPlayerHasHeld(true);
            hold1.setBackgroundColor(Color.parseColor("#FFFF0D00"));
            hold1.setTextColor(Color.parseColor("#FFFFF584"));
        }
    }

    public void onHold2ButtonClicked(View button){
        boolean hold = ((ToggleButton)button).isChecked();
        if(hold) {
            wheel2.setPlayerHasHeld(true);
            hold2.setBackgroundColor(Color.parseColor("#FFFF0D00"));
            hold2.setTextColor(Color.parseColor("#FFFFF584"));
        }
    }

    public void onHold3ButtonClicked(View button){
        boolean hold = ((ToggleButton)button).isChecked();
        if(hold){
            wheel3.setPlayerHasHeld(true);
            hold3.setBackgroundColor(Color.parseColor("#FFFF0D00"));
            hold3.setTextColor(Color.parseColor("#FFFFF584"));
        }
    }

    public void resetHoldButtonsFalse(){
        for(Wheel wheel : slotMachine.getSlots()){
            wheel.setPlayerHasHeld(false);
        }
    }

    //NUDGE FUNCTIONS

    public void showNudgeWheel1(){
        if(wheel1.getNudgeAvailable()){
            nudge1.setVisibility(View.VISIBLE);
        } else {
            nudge1.setVisibility(View.INVISIBLE
            );
        }
    }

    public void showNudgeWheel2(){
        if(wheel2.getNudgeAvailable()){
            nudge2.setVisibility(View.VISIBLE);
        } else {
            nudge2.setVisibility(View.INVISIBLE
            );
        }
    }

    public void showNudgeWheel3(){
        if(wheel3.getNudgeAvailable()){
            nudge3.setVisibility(View.VISIBLE);
        } else {
            nudge3.setVisibility(View.INVISIBLE
            );
        }
    }

    public void showNudgeIfAvailable(){
        showNudgeWheel1();
        showNudgeWheel2();
        showNudgeWheel3();
    }

    public void onNudge1Clicked(View button){
        showNudgeWheel1();
        wheel1.nudge();

        ArrayList<Symbols> currentLine = slotMachine.getCurrentSymbols();
        ArrayList<String> lineImages = slotMachine.getLineImages(currentLine);
        String image = lineImages.get(0);
        int imageID = getResources().getIdentifier(image, "drawable", getPackageName());
        symbol1.setImageResource(imageID);

        ArrayList<Symbols> topLine = slotMachine.getTopLineSymbols();
        ArrayList<String> topImages = slotMachine.getLineImages(topLine);
        String topImage = topImages.get(0);
        int topID = getResources().getIdentifier(topImage, "drawable", getPackageName());
        wheel1Top.setImageResource(topID);

        ArrayList<Symbols> bottomLine = slotMachine.getBottomLineSymbols();
        ArrayList<String> bottomImages = slotMachine.getLineImages(bottomLine);
        String bottomImage = bottomImages.get(0);
        int bottomID = getResources().getIdentifier(bottomImage, "drawable", getPackageName());
        wheel1Bottom.setImageResource(bottomID);
    }

    public void onNudge2Clicked(View button){
        showNudgeWheel2();
        wheel2.nudge();

        ArrayList<Symbols> currentLine = slotMachine.getCurrentSymbols();
        ArrayList<String> lineImages = slotMachine.getLineImages(currentLine);
        String image = lineImages.get(1);
        int imageID = getResources().getIdentifier(image, "drawable", getPackageName());
        symbol2.setImageResource(imageID);

        ArrayList<Symbols> topLine = slotMachine.getTopLineSymbols();
        ArrayList<String> topImages = slotMachine.getLineImages(topLine);
        String topImage = topImages.get(1);
        int topID = getResources().getIdentifier(topImage, "drawable", getPackageName());
        wheel2Top.setImageResource(topID);

        ArrayList<Symbols> bottomLine = slotMachine.getBottomLineSymbols();
        ArrayList<String> bottomImages = slotMachine.getLineImages(bottomLine);
        String bottomImage = bottomImages.get(1);
        int bottomID = getResources().getIdentifier(bottomImage, "drawable", getPackageName());
        wheel2Bottom.setImageResource(bottomID);
    }

    public void onNudge3Clicked(View button) {
        showNudgeWheel3();
        wheel3.nudge();

        ArrayList<Symbols> currentLine = slotMachine.getCurrentSymbols();
        ArrayList<String> lineImages = slotMachine.getLineImages(currentLine);
        String image = lineImages.get(2);
        int imageID = getResources().getIdentifier(image, "drawable", getPackageName());
        symbol3.setImageResource(imageID);

        ArrayList<Symbols> topLine = slotMachine.getTopLineSymbols();
        ArrayList<String> topImages = slotMachine.getLineImages(topLine);
        String topImage = topImages.get(2);
        int topID = getResources().getIdentifier(topImage, "drawable", getPackageName());
        wheel3Top.setImageResource(topID);

        ArrayList<Symbols> bottomLine = slotMachine.getBottomLineSymbols();
        ArrayList<String> bottomImages = slotMachine.getLineImages(bottomLine);
        String bottomImage = bottomImages.get(2);
        int bottomID = getResources().getIdentifier(bottomImage, "drawable", getPackageName());
        wheel3Bottom.setImageResource(bottomID);
    }

    public void resetNudgesFalse(){
        for(Wheel wheel : slotMachine.getSlots()){
            wheel.setNudgeAvailable(false);
        }
    }

}
