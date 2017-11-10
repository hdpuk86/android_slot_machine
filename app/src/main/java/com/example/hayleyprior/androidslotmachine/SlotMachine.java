package com.example.hayleyprior.androidslotmachine;

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

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public int getGameBank() {
        return gameBank;
    }

    public int getPlayerFunds() {
        return playerFunds;
    }

    public boolean isQuit() {
        return quit;
    }

    public int countSlots(){
        return this.slots.size();
    }

    public void generateWheels(){
        for(int i = 0; i < getNumberOfWheels(); i ++){
            this.slots.add(wheelType);
        }
    }

}
