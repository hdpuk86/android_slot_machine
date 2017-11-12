package com.example.hayleyprior.androidslotmachine;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class SlotMachine {

    private int numberOfWheels;
    private ArrayList<Wheel> slots;
    private int gameBank;
    private int playerFunds;
    private boolean quit;
    private Wheel wheelType;

    public SlotMachine(Wheel wheelType, int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
        this.wheelType = wheelType;
        this.slots = new ArrayList<>();
        this.gameBank = 0;
        this.playerFunds = 0;
        this.quit = false;
        generateWheels();
    }

    public ArrayList<Wheel> getSlots() {
        return slots;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public int checkGameBank() {
        return gameBank;
    }

    public int checkPlayerFunds() {
        return playerFunds;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setPlayerFunds(int amount) {
        this.playerFunds = amount;
    }

    public void addToGameBank(int amount) {
        this.gameBank += amount;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public int countSlots(){
        return this.slots.size();
    }

    public void generateWheels(){
        for(int i = 0; i < getNumberOfWheels(); i ++){
            this.slots.add(wheelType);
        }
    }

    public void addPlayerFunds(int amount){
        this.playerFunds += amount;
    }

    public boolean checkWin(ArrayList<Symbols> line){
        int counter = 0;
        for(Symbols symbol : line){
            if(symbol.equals(line.get(0))){
                counter++;
            }
        }
        if(counter == line.size()){
            return true;
        }
        return false;
    }

    public int getWinValue(ArrayList<Symbols> line){
        return line.get(0).getValue();
    }

    public ArrayList<Symbols> spin(){
        addPlayerFunds(-1);
        addToGameBank(1);
        ArrayList<Symbols> line = new ArrayList<>();
        for(Wheel wheel : slots){
            line.add(wheel.spin());
        }
        return line;
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
