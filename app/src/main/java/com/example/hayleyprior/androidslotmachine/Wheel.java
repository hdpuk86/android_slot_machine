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
    private int currentIndex;
    private Symbols currentSymbol;

    public Wheel(){
        this.allSymbols = new ArrayList<>();
        this.holdAvailable = false;
        this.nudgeAvailable = false;
//        this.currentIndex = ;
//        this.currentSymbol = ;
        generateSymbols();
    }

    public Boolean getHoldAvailable() {
        return holdAvailable;
    }

    public Boolean getNudgeAvailable() {
        return nudgeAvailable;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Symbols getCurrentSymbol() {
        return currentSymbol;
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

}
