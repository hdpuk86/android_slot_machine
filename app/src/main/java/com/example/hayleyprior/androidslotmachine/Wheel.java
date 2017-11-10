package com.example.hayleyprior.androidslotmachine;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class Wheel {

    private ArrayList<Symbols> allSymbols;
    private Boolean holdAvailable;
    private Boolean nudgeAvailable;
//    private int currentIndex;
    private Symbols currentSymbol;

    public Wheel(){
        this.allSymbols = new ArrayList<>();
        this.holdAvailable = false;
        this.nudgeAvailable = false;
//        this.currentIndex = getSymbolIndex(this.getCurrentSymbol());
        generateSymbols();
        this.currentSymbol = getRandomSymbol();
    }

    public Boolean getHoldAvailable() {
        return holdAvailable;
    }

    public Boolean getNudgeAvailable() {
        return nudgeAvailable;
    }
//
//    public int getCurrentIndex() {
//        return currentIndex;
//    }

    public void setHoldAvailable(Boolean holdAvailable) {
        this.holdAvailable = holdAvailable;
    }

    public void setNudgeAvailable(Boolean nudgeAvailable) {
        this.nudgeAvailable = nudgeAvailable;
    }

//    public void setCurrentIndex(int currentIndex) {
//        this.currentIndex = currentIndex;
//    }

    public void setCurrentSymbol(Symbols currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public Symbols getCurrentSymbol() {
        return currentSymbol;
    }

    public int getSymbolIndex(Symbols symbol){
        return this.allSymbols.indexOf(symbol);
    }

    public void generateSymbols(){
        for(Symbols symbol : Symbols.values()){
            this.allSymbols.add(symbol);
        }
    }

    public int countSymbols(){
        return this.allSymbols.size();
    }

    public int randomInt(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public Symbols getSymbolAtIndex(int index){
        return this.allSymbols.get(index);
    }

    public Symbols getRandomSymbol(){
        int randomIndex = randomInt(countSymbols());
        return getSymbolAtIndex(randomIndex);
    }

    public Symbols nudge(){
        if(getSymbolIndex(currentSymbol) == 0){
            setCurrentSymbol(getSymbolAtIndex(countSymbols() - 1));
        } else {
            int newIndex = getSymbolIndex(this.currentSymbol) - 1;
            setCurrentSymbol(getSymbolAtIndex(newIndex));
        }
        setNudgeAvailable(false);
        return getCurrentSymbol();
    }

    public void randomAssignNudgeAvailable(){
        int randomNumber = randomInt(20);
        if(randomNumber == 10){
            setNudgeAvailable(true);
        } else {
            setNudgeAvailable(false);
        }
    }

    public void randomAssignHoldAvailable(){
        int randomNumber = randomInt(20);
        if(randomNumber == 10){
            setHoldAvailable(true);
        } else {
            setHoldAvailable(false);
        }
    }

}
