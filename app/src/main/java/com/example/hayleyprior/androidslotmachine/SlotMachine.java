package com.example.hayleyprior.androidslotmachine;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class SlotMachine {

    private int numberOfWheels;
    private ArrayList<Wheel> slots;
    private int playerFunds;

    public SlotMachine(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
        this.slots = new ArrayList<>();
        this.playerFunds = 0;
        generateWheels();
    }

    private void generateWheels(){
        for(int i = 0; i <numberOfWheels; i++){
            Wheel wheel = new Wheel();
            this.slots.add(wheel);
        }
    }

    public ArrayList<Wheel> getSlots() {
        return slots;
    }

    public int checkPlayerFunds() {
        return playerFunds;
    }

    public void setPlayerFunds(int amount) {
        this.playerFunds = amount;
    }

    public void addPlayerFunds(int amount){
        this.playerFunds += amount;
    }

    public int countSlots(){
        return this.slots.size();
    }

    public int getWinValue(ArrayList<Symbols> line){
        return line.get(0).getValue();
    }

    public boolean checkWin(ArrayList<Symbols> line){
        int counter = 0;
        for(Symbols symbol : line){
            if(symbol.equals(line.get(0))){
                counter++;
            }
        }
        return counter == line.size();
    }

    public ArrayList<Symbols> spin(){
        addPlayerFunds(-1);
        ArrayList<Symbols> line = new ArrayList<>();
        for(Wheel wheel : slots){
            line.add(wheel.spin());
        }
        return line;
    }

    public ArrayList<Symbols> getCurrentSymbols(){
        ArrayList<Symbols> currentSymbols = new ArrayList<>();
            for(Wheel wheel : this.slots){
                Symbols symbol = wheel.getCurrentSymbol();
                currentSymbols.add(symbol);
            }
        return currentSymbols;
    }

    public ArrayList<Symbols> getTopLineSymbols(){
        ArrayList<Symbols> topSymbols = new ArrayList<>();
        for(Wheel wheel : getSlots()){
            Symbols top = wheel.getTopSymbol();
            topSymbols.add(top);
        }
        return topSymbols;
    }

    public ArrayList<Symbols> getBottomLineSymbols(){
        ArrayList<Symbols> bottomSymbols = new ArrayList<>();
        for(Wheel wheel : getSlots()){
            Symbols bottom = wheel.getBottomSymbol();
            bottomSymbols.add(bottom);
        }
        return bottomSymbols;
    }

    public String getSymbolImage(Symbols symbol){
        return symbol.getImageName();
    }

    public ArrayList<String> getLineImages(ArrayList<Symbols> line) {
        ArrayList<String> images = new ArrayList<>();
        for (Symbols symbol : line) {
            String image = getSymbolImage(symbol);
            images.add(image);
        }
        return images;
    }
}
